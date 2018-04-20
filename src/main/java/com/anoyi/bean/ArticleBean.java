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

}
