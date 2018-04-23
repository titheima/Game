package com.anoyi.controller;

import com.alibaba.fastjson.JSON;
import com.anoyi.bean.Book;
import com.anoyi.bean.ResponseBean;
import com.anoyi.mongo.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("/book")
    public String book(Model model) {
        List<Book> books = bookRepository.findAll(Sort.by(Sort.Direction.DESC, "updateTime"));
        model.addAttribute("books", books);
        return "book";
    }

    @GetMapping("/book_back")
    public String book_back() {
        return "book_back";
    }

    @GetMapping("/book/list")
    public String bookList(Model model) {
        List<Book> books = bookRepository.findAll(Sort.by(Sort.Direction.DESC, "updateTime"));
        model.addAttribute("books", books);
        return "book/list";
    }

    /**
     * 根据书名查询书籍
     * @param name
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/book_search")
    public String bookSearch(String name,Model model)throws Exception{
        if (StringUtils.isEmpty(name)) {
            return "redirect:/book/list";
        }
        URLEncoder.encode(name,"utf-8");
        List<Book> books=bookRepository.findByNameLike(name);
        model.addAttribute("books", books);
        return "book/list";
    }

    @RequestMapping("/book_save")
    @ResponseBody
    public ResponseBean bookSave(Book book) {
//        book.setId("1");
//        book.setCover("https://img13.360buyimg.com/n1/s200x200_jfs/t6130/167/771989293/235186/608d0264/592bf167Naf49f7f6.jpg");
//        book.setBuy("https://item.jd.com/11252778.html");
//        book.setDegree(5);
//        book.setDownload("https://share.weiyun.com/5df6LcB");
//        book.setLanguage("中文");
//        book.setName("深入理解Java虚拟机-第二版");
//        book.setUpdateTime(new Date());
        book.setUpdateTime(new Date());
        bookRepository.save(book);
        return ResponseBean.success(null);
    }

    @GetMapping("/book_findOne")
    public String bookFindOne(String id) {
        Optional<Book> book = bookRepository.findById(id);
        String jsonBook = JSON.toJSON(book).toString();
        return "book";
    }

    @GetMapping("/book_delete")
    public String bookDelete(String id) {
        Book book = new Book();
        book.setId(id);
        System.out.println("执行删除操作");
        bookRepository.delete(book);
        return "redirect:/book/list";
    }

    @GetMapping("/book_deleteAll")
    public String boodDeleteAll() {
        bookRepository.deleteAll();
        return "book";
    }

    //爬取图书
    @GetMapping("/book_crawl")
    public String bookCrawl() {
        try {
            Document document = Jsoup.connect("http://localhost/book_back").get();
            Elements elements = document.select(".card");
            List<Book> books = new ArrayList<>();
            int i = 1;
            for (Element element : elements) {
                Book book = new Book();
                String name = element.select(".dark-grey-text").text();
                String language = element.select(".badge").text();
                String cover = element.select(".mx-auto").attr("src");
                int degree = element.select(".fa-star").size();
                String download = element.select(".btn-outline-primary").attr("href");
                String buy = element.select(".btn-outline-warning").attr("href");
                book.setId(Integer.toString(i));
                book.setCover(cover);
                book.setDegree(degree);
                book.setLanguage(language);
                book.setName(name);
                book.setUpdateTime(new Date());
                book.setBuy(buy);
                book.setDownload(download);
                books.add(book);
                i++;
            }
            //存入数据库

            //存入MongoDB
            bookRepository.saveAll(books);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "book";
    }
}
