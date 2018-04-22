package com.anoyi.bean;

import lombok.Data;

/**
 * 简书文章
 */
@Data
public class ArticleBean {

    // 文章ID
    private String articleId;

    // 文章标题
    private String title;

    // 作者昵称
    private String authorName;

    // 作者头像
    private String authorAvatar;

    // 最后更新时间
    private String updateTime;

    // 字数
    private String words;

    // 正文内容
    private String content;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorAvatar() {
        return authorAvatar;
    }

    public void setAuthorAvatar(String authorAvatar) {
        this.authorAvatar = authorAvatar;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
