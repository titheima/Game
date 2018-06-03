package com.anymk.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * 书籍
 */
@Data
public class Book implements Comparable<Book>{

    @Id
    private long id;

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

    //书籍描述
    private String description;

    @Override
    public int compareTo(Book o) {
        int result=0;
        result=(int)(this.id-o.id);
        return result;
    }
}
