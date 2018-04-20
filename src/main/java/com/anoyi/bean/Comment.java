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

}

