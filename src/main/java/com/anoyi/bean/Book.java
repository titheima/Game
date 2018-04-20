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

}
