package com.anymk.controller;

import com.anymk.bean.CNCFProject;
import com.anymk.mongo.repository.CNCFProjectRepository;
import com.anymk.tools.IdWorker;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
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

    //cncf维护功能

    //获取雪花算法的参数
   /* @Value("${idworker.workerId}")
    private Integer workerId;
    @Value("${idworker.datacenterId}")
    private Integer datacenterId;*/

    //爬取项目
    @GetMapping("/admin2/project_crawl")
    public String projectCrawl() {
        // IdWorker idWorker = new IdWorker(workerId, datacenterId);
        IdWorker idWorker = new IdWorker(0, 1);
        try {
            Document document = Jsoup.connect("http://localhost/cncf2").get();
            Elements elements = document.select(".row");

            List<CNCFProject> projects = new ArrayList<>();

            int i = 0;
            for (Element element : elements) {
                CNCFProject project = new CNCFProject();
                //只处理奇数行,因为偶数行是links
                if (i % 2 == 0) {
                    long id = idWorker.nextId();
                    project.setId(id + "");
                    String homeImage = element.select(".img-fluid").attr("src");
                    project.setHomeImage(homeImage);
                    String home = element.select(".view a").attr("href");
                    project.setHome(home);

                    String logo = element.select(".col-lg-7").select("img").attr("src");
                    project.setLogo(logo);

                    String name = element.select("span").text();
                    project.setName(name);

                    String description = element.select("p").text();
                    project.setDescription(description);


                }else {
                    String links = element.select(".row a").toString();
                    project.setLinks(links);
                }

                projects.add(project);
                i++;
            }
            //存入MongoDB
            projectRepository.saveAll(projects);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "/admin/project";
    }

}
