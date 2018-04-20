package com.anoyi.controller.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anoyi.bean.ArticleBean;
import com.anoyi.bean.JianshuBean;
import com.anoyi.config.JianshuConfig;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class JianshuService {

    private final static String USER_AGENT = "Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1";

    private final static String PC_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36";

    private final JianshuConfig config;

    /**
     * 解析用户信息和文档信息
     */
    public JianshuBean parseUserAndBlogs() {
        JianshuBean jianshu = new JianshuBean();
        try {
            Document document = doGetHTML(userPageUri());
            parseUser(jianshu, document);
            String api = articlesJsonUri(jianshu.getAuthor().getUserId(), 1);
            JSONArray array = JSONObject.parseArray(doGetJSON(api));
            parseBlog(jianshu, array);
            JSONObject collections = JSONObject.parseObject(doGetJSON(collectionsJsonUri(1)));
            parseCollection(jianshu, collections);
            JSONObject notebooks = JSONObject.parseObject(doGetJSON(notebooksJsonUri(1)));
            parseNotebook(jianshu, notebooks);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("请求失败：{}", e.getMessage());
        }
        return jianshu;
    }

    /**
     * 解析分类
     */
    public JianshuBean parseNoteBooks(String id) {
        JianshuBean jianshu = new JianshuBean();
        try {
            Document document = doGetHTML(userPageUri());
            parseUser(jianshu, document);
            String api = notebookArticlesJsonUri(id, 1);
            JSONArray array = JSONObject.parseArray(doGetJSON(api));
            parseBlog(jianshu, array);
            JSONObject collections = JSONObject.parseObject(doGetJSON(collectionsJsonUri(1)));
            parseCollection(jianshu, collections);
            JSONObject notebooks = JSONObject.parseObject(doGetJSON(notebooksJsonUri(1)));
            parseNotebook(jianshu, notebooks);
        } catch (IOException e) {
            log.error("请求失败：{}", e.getMessage());
        }
        return jianshu;
    }

    /**
     * 解析分类
     */
    public JianshuBean parseCollections(String id) {
        JianshuBean jianshu = new JianshuBean();
        try {
            Document document = doGetHTML(userPageUri());
            parseUser(jianshu, document);
            String api = collectionArticlesJsonUri(id, 1);
            JSONArray array = JSONObject.parseArray(doGetJSON(api));
            parseBlog(jianshu, array);
            JSONObject collections = JSONObject.parseObject(doGetJSON(collectionsJsonUri(1)));
            parseCollection(jianshu, collections);
            JSONObject notebooks = JSONObject.parseObject(doGetJSON(notebooksJsonUri(1)));
            parseNotebook(jianshu, notebooks);
        } catch (IOException e) {
            log.error("请求失败：{}", e.getMessage());
        }
        return jianshu;
    }

    /**
     * 解析文章
     */
    public ArticleBean parseArticle(String articleId) {
        String api = "https://www.jianshu.com/p/" + articleId;
        ArticleBean article = new ArticleBean();
        try {
            Document document = Jsoup.connect(api).get();
            Element element = document.select("div.article").get(0);
            article.setTitle(element.select("h1.title").text());
            article.setAuthorAvatar(element.select("a.avatar img").attr("src"));
            article.setAuthorName(element.select("span.name a").text());
            article.setUpdateTime(element.select("span.publish-time").text());
            article.setWords(element.select("span.wordage").text());
            article.setContent(element.select("div.show-content").html().replaceAll("data-original-src", "src"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return article;
    }

    /**
     * 解析文章列表【全部】
     */
    public JianshuBean parseArticleList(int page) {
        JianshuBean jianshu = new JianshuBean();
        try {
            Document document = doGetHTML(userPageUri());
            parseUser(jianshu, document);
            String api = articlesJsonUri(jianshu.getAuthor().getUserId(), page);
            JSONArray array = JSONObject.parseArray(doGetJSON(api));
            parseBlog(jianshu, array);
        } catch (IOException e) {
            log.error("请求失败：{}", e.getMessage());
        }
        return jianshu;
    }

    /**
     * 解析文章列表【分类】
     */
    public JianshuBean parseArticleListInNotebook(String id, int page) {
        JianshuBean jianshu = new JianshuBean();
        try {
            String api = notebookArticlesJsonUri(id, page);
            JSONArray array = JSONObject.parseArray(doGetJSON(api));
            parseBlog(jianshu, array);
        } catch (IOException e) {
            log.error("请求失败：{}", e.getMessage());
        }
        return jianshu;
    }

    /**
     * 解析文章列表【分类】
     */
    public JianshuBean parseArticleListInCollection(String id, int page) {
        JianshuBean jianshu = new JianshuBean();
        try {
            String api = collectionArticlesJsonUri(id, page);
            JSONArray array = JSONObject.parseArray(doGetJSON(api));
            parseBlog(jianshu, array);
        } catch (IOException e) {
            log.error("请求失败：{}", e.getMessage());
        }
        return jianshu;
    }

    /**
     * 解析分类列表
     */
    public JianshuBean parseCollectionList(int page) {
        JianshuBean jianshu = new JianshuBean();
        try {
            JSONObject object = JSONObject.parseObject(doGetJSON(collectionsJsonUri(page)));
            parseCollection(jianshu, object);
        } catch (IOException e) {
            log.error("请求失败：{}", e.getMessage());
        }
        return jianshu;
    }

    /**
     * 解析文集列表
     */
    public JianshuBean parseNotebookList(int page) {
        JianshuBean jianshu = new JianshuBean();
        try {
            JSONObject object = JSONObject.parseObject(doGetJSON(notebooksJsonUri(page)));
            parseNotebook(jianshu, object);
        } catch (IOException e) {
            log.error("请求失败：{}", e.getMessage());
        }
        return jianshu;
    }

    /**
     * 解析专题
     */
    private void parseCollection(JianshuBean jianshu, JSONObject collections) {
        jianshu.setOwnCollectionPage(collections.getIntValue("page"));
        jianshu.setOwnCollectionTotalPages(collections.getIntValue("total_pages"));
        JSONArray array = collections.getJSONArray("collections");
        for (int i = 0; i < array.size(); i++) {
            JianshuBean.OwnCollection collection = new JianshuBean.OwnCollection();
            JSONObject object = array.getJSONObject(i);
            collection.setAvatar(object.getString("avatar").replace("http:",""));
            collection.setId(object.getString("id"));
            collection.setSlug(object.getString("slug"));
            collection.setTitle(object.getString("title"));
            jianshu.getOwnCollections().add(collection);
        }
    }

    /**
     * 解析分类
     */
    private void parseNotebook(JianshuBean jianshu, JSONObject notebooks) {
        jianshu.setNotebookPage(notebooks.getIntValue("page"));
        jianshu.setNotebookTotalPages(notebooks.getIntValue("total_pages"));
        JSONArray array = notebooks.getJSONArray("notebooks");
        for (int i = 0; i < array.size(); i++) {
            JianshuBean.Notebook notebook = new JianshuBean.Notebook();
            JSONObject object = array.getJSONObject(i);
            notebook.setBook(object.getBoolean("book"));
            notebook.setId(object.getString("id"));
            notebook.setName(object.getString("name"));
            jianshu.getNotebooks().add(notebook);
        }
    }

    /**
     * 解析用户信息
     */
    private void parseUser(JianshuBean jianshu, Document document) {
        JianshuBean.Author author = jianshu.getAuthor();
        String avatar = document.select("a.avatar img").attr("src");
        author.setAvatar(avatar);
        String nickname = document.select("a.name").text();
        author.setNickname(nickname);
        String description = document.select("div.js-intro").text();
        author.setDescription(description);
        String userId = document.select("div.follow-button").attr("props-data-user-id");
        author.setUserId(userId);
    }

    /**
     * 解析文章
     */
    private void parseBlog(JianshuBean jianshu, JSONArray array) {
        List<JianshuBean.Article> articles = jianshu.getArticles();
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = array.getJSONObject(i).getJSONObject("object").getJSONObject("data");
            JianshuBean.Article article = new JianshuBean.Article();
            article.setCover(object.getString("list_image_url").replace("http:",""));
            article.setDescription(object.getString("public_abbr"));
            article.setTitle(object.getString("title"));
            article.setLikeCount(object.getInteger("likes_count"));
            String time = object.getString("first_shared_at");
            article.setTime(handleTime(time));
            article.setId(object.getString("slug"));
            articles.add(article);
        }
    }

    /**
     * 简书用户地址
     */
    private String userPageUri() {
        return "https://www.jianshu.com/u/" + config.getUserId();
    }

    /**
     * 文章列表地址【全部】
     */
    private String articlesJsonUri(String userId, int page) {
        return "https://www.jianshu.com/mobile/users/" + userId + "/public_notes?page=" + page + "&count=10";
    }

    /**
     * 文章列表地址【分类】
     */
    private String notebookArticlesJsonUri(String id, int page) {
        return "https://www.jianshu.com/mobile/notebooks/" + id + "/public_notes?page=" + page + "&count=10";
    }

    /**
     * 文章列表地址【专题】
     */
    private String collectionArticlesJsonUri(String id, int page) {
        return "https://www.jianshu.com/mobile/collections/" + id + "/public_notes?page=" + page + "&count=10";
    }

    /**
     * 分类
     */
    private String notebooksJsonUri(int page) {
        return "https://www.jianshu.com/users/" + config.getUserId() + "/notebooks?slug=" + config.getUserId()
                + "&type=manager&page=" + page + "&per_page=10";
    }

    /**
     * 专题
     */
    private String collectionsJsonUri(int page) {
        return "https://www.jianshu.com/users/" + config.getUserId() + "/collections?slug=" + config.getUserId()
                + "&type=own&page=" + page + "&per_page=10";
    }

    /**
     * 解析时间  2018-01-28T08:48:26.000+08:00
     * 2018-01-28 08:48:26
     */
    private String handleTime(String time) {
        time = time.replace("T", " ");
        return time.substring(0, 19);
    }

    /**
     * GET 请求 HTML
     */
    private Document doGetHTML(String url) throws IOException {
        return Jsoup.connect(url)
                .header("User-Agent", PC_AGENT)
                .ignoreContentType(true)
                .validateTLSCertificates(false)
                .get();
    }

    /**
     * GET 请求 HTML
     */
    private String doGetJSON(String url) throws IOException {
        return Jsoup.connect(url)
                .header("User-Agent", USER_AGENT)
                .header("Accept", "application/json")
                .ignoreContentType(true)
                .validateTLSCertificates(false)
                .get().text();
    }

}
