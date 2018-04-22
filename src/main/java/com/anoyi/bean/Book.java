package com.anoyi.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 书籍
 */
@Data
public class Book {

    @Id
    private String id;

    // 封面
    private String cover;

    // 书名
    private String name;

    // 下载地址
    private String download;

    // 购买地址
    private String buy;

    // 语言
    private String language;

    // 难度系数
    private int degree;

    // 更新时间
    private Date updateTime;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
