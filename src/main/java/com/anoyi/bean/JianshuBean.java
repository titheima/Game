package com.anoyi.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
public class JianshuBean {

    @Id
    private String id;

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
        // 用户ID
        @Id
        private String userId;

        // 昵称
        private String nickname;

        // 头像
        private String avatar;

        // 个人介绍
        private String description;



        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

    @Data
    public static class Article{
        // ID
        @Id
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Integer getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(Integer likeCount) {
            this.likeCount = likeCount;
        }
    }

    @Data
    public static class OwnCollection{
        // 封面
        private String avatar;

        // 专题ID
        @Id
        private String id;

        private String slug;

        // 文章标题
        private String title;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    @Data
    public static class Notebook{
        // 是否是书
        private boolean book;

        // 分类ID
        @Id
        private String id;

        // 分类名
        private String name;

        public boolean isBook() {
            return book;
        }

        public void setBook(boolean book) {
            this.book = book;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
