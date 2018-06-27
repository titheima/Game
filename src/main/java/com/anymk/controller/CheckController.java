package com.anymk.controller;


import com.anymk.config.CustomAttributesConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * Created by szz on 2018/6/27 13:07.
 * Email szhz186@gmail.com
 */
@Controller
@AllArgsConstructor
public class CheckController {
    private final CustomAttributesConfig customAttributesConfig;

    /**
     * Check Me
     */
    @GetMapping(value = "/check")
        public String chat (@RequestParam(required = false) String
        secret, @RequestParam(required = false, defaultValue = "zh") String lang){
            if (!StringUtils.isEmpty(secret) && secret.equals(customAttributesConfig.getSecret())) {
                return lang.equals("en") ? "about-en" : "about";
            }
            return "about-check";
        }
    }
