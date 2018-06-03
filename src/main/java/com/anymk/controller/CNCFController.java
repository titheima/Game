package com.anymk.controller;

import com.anymk.bean.CNCFProject;
import com.anymk.mongo.repository.CNCFProjectRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * CNCF
 */
@Controller
@AllArgsConstructor
@Log4j2
public class CNCFController {

    private final CNCFProjectRepository projectRepository;

    @GetMapping("/cncf")
    public String cncf(Model model) {
        List<CNCFProject> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "cncf";
    }

}
