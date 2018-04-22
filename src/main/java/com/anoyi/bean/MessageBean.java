package com.anoyi.bean;

import lombok.Data;

/**
 * 留言
 * @author anoy
 */
@Data
public class MessageBean {

    private String nickname;

    private String email;

    private String content;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
}
