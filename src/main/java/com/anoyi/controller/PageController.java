package com.anoyi.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Anoy on 2017/4/21.
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

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
