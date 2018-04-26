package com.anoyi.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * 简书文章
 */
@Data
public class ArticleBean {

    // 文章ID
    @Id
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

    // 封面图
    private String cover;

    // 摘要
    private String description;

    // 时间
    private String time;

    // 喜欢数
    private Integer likeCount;

    // 用户ID
    private String userId;

    // 专题ID
    private String ownCollectionId;

    // 分类ID
    private String notebookId;

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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOwnCollectionId() {
        return ownCollectionId;
    }

    public void setOwnCollectionId(String ownCollectionId) {
        this.ownCollectionId = ownCollectionId;
    }

    public String getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(String notebookId) {
        this.notebookId = notebookId;
    }
}
