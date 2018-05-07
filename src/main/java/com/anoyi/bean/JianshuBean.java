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

    }

    @Data
    public static class Article{
        // ID
        @Id
        private String id;
        private String authorId;
        private String ownId;
        private String noteBookId;

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

        // 最后更新时间
        private String updateTime;

        // 字数
        private String words;

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

    }


}
