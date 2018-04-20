package com.anoyi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/app")
@Controller
public class AppController {

    /**
     * 即时通信
     */
    @GetMapping(value = "/chat")
    public String chat() {
        return "chat";
    }

    /**
     * 直播弹幕
     */
    @GetMapping(value = "/live")
    public String live() {
        return "live";
    }

}
