package com.anoyi.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by szz on 2018/5/3 0:00.
 * Email szhz186@gmail.com
 */
@Controller
@AllArgsConstructor
public class CommonController {

    @RequestMapping("/admin/dashboards")
    public String comment(Model model){
        //model.addAttribute("comments", comments);
        return "admin/dashboards";
    }
}
