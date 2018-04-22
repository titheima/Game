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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getHomeImage() {
        return homeImage;
    }

    public void setHomeImage(String homeImage) {
        this.homeImage = homeImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }
}
