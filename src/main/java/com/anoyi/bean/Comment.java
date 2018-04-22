package com.anoyi.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Comment {

    @Id
    private String id;

    // 文章ID
    private String articleId;

    // 头像
    private String avatar;

    // 姓名
    private String name;

    // 邮箱
    private String email;

    // 内容
    private String content;

    // 更新时间
    private Date createTime;

    // 是否显示
    private boolean show;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}

