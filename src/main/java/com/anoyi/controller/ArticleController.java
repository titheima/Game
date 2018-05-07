package com.anoyi.controller;

import com.anoyi.bean.ArticleBean;
import com.anoyi.bean.JianshuBean;
import com.anoyi.bean.ResponseBean;
import com.anoyi.mongo.repository.ArticleJianshuRepository;
import com.anoyi.mongo.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Optional;

/**
 * Created by szz on 2018/4/26 17:45.
 * Email szhz186@gmail.com
 */
@Controller
@AllArgsConstructor
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final ArticleJianshuRepository articleJianshuRepository;

    @GetMapping("/admin/edit")
    public String blog(Model model) {
        ArticleBean article=new ArticleBean();
        model.addAttribute("article", article);
        return "admin/edit";
    }
    @GetMapping("/admin/blog/edit/{id}")
    public String blog_edit(Model model, @PathVariable String id) {
        Optional<ArticleBean> article = articleRepository.findById(id);
        model.addAttribute("article", article.get());
        return "admin/edit";
    }

    @PostMapping("/admin/article_save")
    @ResponseBody
    public ResponseBean articleSave(ArticleBean articleBean) {
        System.out.println(articleBean);
        articleBean.setUpdateTime(new Date().toString());

        if (articleBean.getContent().length() > 80) {
            articleBean.setDescription(articleBean.getContent().substring(0, 80));
        }else {
            articleBean.setDescription(articleBean.getContent());
        }
        if (articleBean.getArticleId()==null){
            articleBean.setTime(new Date().toString());
        }
        articleBean.setUpdateTime(new Date().toString());
        articleBean.setWords((articleBean.getContent().length() + ""));
        JianshuBean.Article article = new JianshuBean.Article();

        BeanUtils.copyProperties(articleBean, article);

        articleRepository.save(articleBean);
        articleJianshuRepository.save(article);

        return ResponseBean.success(null);
    }
}
