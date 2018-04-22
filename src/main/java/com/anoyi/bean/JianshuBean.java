package com.anoyi.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class JianshuBean {

    private Author author = new Author();

    private List<Article> articles = new ArrayList<>();

    private List<OwnCollection> ownCollections = new ArrayList<>();

    private List<Notebook> notebooks = new ArrayList<>();

    private int ownCollectionPage = 0;

    private int ownCollectionTotalPages = 0;

    private int notebookPage = 0;

    private int notebookTotalPages = 0;

    @Data
    public class Author{
        // 昵称
        private String nickname;

        // 头像
        private String avatar;

        // 个人介绍
        private String description;

        // 用户ID
        private String userId;

    }

    @Data
    public static class Article{
        // ID
        private String id;

        // 封面图
        private String cover;

        // 标题
        private String title;

        // 摘要
        private String description;

        // 时间
        private String time;

        // 喜欢数
        private Integer likeCount;

    }

    @Data
    public static class OwnCollection{
        // 封面
        private String avatar;

        // 专题ID
        private String id;

        private String slug;

        // 文章标题
        private String title;
    }

    @Data
    public static class Notebook{
        // 是否是书
        private boolean book;

        // 分类ID
        private String id;

        // 分类名
        private String name;
    }


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<OwnCollection> getOwnCollections() {
        return ownCollections;
    }

    public void setOwnCollections(List<OwnCollection> ownCollections) {
        this.ownCollections = ownCollections;
    }

    public List<Notebook> getNotebooks() {
        return notebooks;
    }

    public void setNotebooks(List<Notebook> notebooks) {
        this.notebooks = notebooks;
    }

    public int getOwnCollectionPage() {
        return ownCollectionPage;
    }

    public void setOwnCollectionPage(int ownCollectionPage) {
        this.ownCollectionPage = ownCollectionPage;
    }

    public int getOwnCollectionTotalPages() {
        return ownCollectionTotalPages;
    }

    public void setOwnCollectionTotalPages(int ownCollectionTotalPages) {
        this.ownCollectionTotalPages = ownCollectionTotalPages;
    }

    public int getNotebookPage() {
        return notebookPage;
    }

    public void setNotebookPage(int notebookPage) {
        this.notebookPage = notebookPage;
    }

    public int getNotebookTotalPages() {
        return notebookTotalPages;
    }

    public void setNotebookTotalPages(int notebookTotalPages) {
        this.notebookTotalPages = notebookTotalPages;
    }
}
