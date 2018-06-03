package com.anymk.bean;

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


}
