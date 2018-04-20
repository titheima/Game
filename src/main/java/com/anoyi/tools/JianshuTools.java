package com.anoyi.tools;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 简书爬虫工具类
 */
@Log4j2
public class JianshuTools {

    private final static String ARTICLE_LIST_URL = "https://www.jianshu.com/nb/19940308";

    private static volatile List<JSONObject> cache = new ArrayList<>();

    /**
     * 获取缓存的简书文章信息
     */
    public static List<JSONObject> getCachedArticles() {
        if (cache.size() > 0) {
            // 异步更新缓存
            new Thread(JianshuTools::crawlAndCacheArticles).start();
            return cache;
        } else {
            crawlAndCacheArticles();
        }
        return cache;
    }

    /**
     * 爬取并缓存简书文章
     */
    private static void crawlAndCacheArticles() {
        try {
            Document document = Jsoup.connect(ARTICLE_LIST_URL).get();
            Elements elements = document.select("ul.note-list li");
            List<JSONObject> articles = new ArrayList<>();
            for (Element element : elements) {
                JSONObject article = new JSONObject();
                String title = element.select("a.title").text();
                String url = "https://www.jianshu.com" + element.select("a.title").attr("href");
                String time = element.select("span.time").attr("data-shared-at").replace("T", " ").replaceAll("\\+.+","");
                article.put("TITLE", title);
                article.put("TIME", time);
                article.put("URL", url);
                articles.add(article);
            }
            cache.clear();
            cache.addAll(articles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
