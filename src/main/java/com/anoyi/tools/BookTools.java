package com.anoyi.tools;

import com.alibaba.fastjson.JSONObject;
import com.anoyi.bean.Book;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by szz on 2018/4/22 23:04.
 * Email szhz186@gmail.com
 * 书籍爬虫工具类
 */
@Log4j2
public class BookTools {

    private final static String ARTICLE_LIST_URL = "http://localhost/book";

    private static volatile List<JSONObject> cache = new ArrayList<>();

    /**
     * 爬取并存入数据库和MongoDB
     */
    private static void crawlBooks() {
        try {
            Document document = Jsoup.connect("http://localhost/book").get();
            Elements elements = document.select(".card");
            List<Book> books = new ArrayList<>();
            int i=1;
            for (Element element : elements) {
                Book book = new Book();
                String name = element.select(".dark-grey-text").text();
                String language = element.select(".badge").text();
                String cover = element.select(".mx-auto").attr("src");
                int degree=element.select(".fa-star").size();
                String buy = element.select(".btn-outline-primary").attr("href");
                String download = element.select(".btn-outline-warning").attr("href");
                book.setId(Long.valueOf(i+""));
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        crawlBooks();
    }
}
