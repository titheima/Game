package com.anymk.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Anoyi on 2017/4/21.
 */
@Controller
@AllArgsConstructor
@CrossOrigin("*")
@Log4j2
public class PageController {

    @GetMapping("/")
    public String index() {
        return "home";
    }

    /**
     * 跳转到登录页
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 跳转到后台仪表盘页
     * @return
     */
    @GetMapping("/admin")
    public String dashboards() {
        return "admin/dashboards";
    }
    /**
     * 跳转到后台仪表盘页
     * @return
     */
    @GetMapping("/error")
    public String error() {
        return "error";
    }

}