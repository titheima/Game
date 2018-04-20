package com.anoyi.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Cloud Native 项目
 */
@Data
public class CNCFProject {

    @Id
    private String id;

    // 首页
    private String home;

    // 首页预览
    private String homeImage;

    // 名称
    private String name;

    // Logo
    private String Logo;

    // 描述
    private String description;

    // 相关链接
    private String links;

}
