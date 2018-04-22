package com.anoyi.controller;

import com.alibaba.fastjson.JSON;
import com.anoyi.bean.Book;
import com.anoyi.mongo.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
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
        System.out.println(books);
        return "book";
    }

    @GetMapping("/book_save")
    public String bookSave(Book book) {
        book.setId("1");
        book.setCover("https://img13.360buyimg.com/n1/s200x200_jfs/t6130/167/771989293/235186/608d0264/592bf167Naf49f7f6.jpg");
        book.setBuy("https://item.jd.com/11252778.html");
        book.setDegree(5);
        book.setDownload("https://share.weiyun.com/5df6LcB");
        book.setLanguage("中文");
        book.setName("深入理解Java虚拟机-第二版");
        book.setUpdateTime(new Date());
        bookRepository.save(book);
        return "book";
    }

    @GetMapping("/book_findOne")
    public String bookFindOne(String id) {
        Optional<Book> book = bookRepository.findById(id);
        String jsonBook = JSON.toJSON(book).toString();
        System.out.println(jsonBook);
        return "book";
    }

    @GetMapping("/book_delete")
    public String bookDelete(String id) {
        Book book = new Book();
        book.setId(id);
        bookRepository.delete(book);
        return "book";
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
            Document document = Jsoup.connect("http://localhost/book").get();
            Elements elements = document.select(".card");
            List<Book> books = new ArrayList<>();
            int i = 1;
            for (Element element : elements) {
                Book book = new Book();
                String name = element.select(".dark-grey-text").text();
                String language = element.select(".badge").text();
                String cover = element.select(".mx-auto").attr("src");
                int degree = element.select(".fa-star").size();
                String buy = element.select(".btn-outline-primary").attr("href");
                String download = element.select(".btn-outline-warning").attr("href");
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
                System.out.println(book);
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
