package com.anoyi.controller;

import com.anoyi.bean.ArticleBean;
import com.anoyi.bean.Comment;
import com.anoyi.bean.JianshuBean;
import com.anoyi.bean.ResponseBean;
import com.anoyi.controller.service.JianshuService;
import com.anoyi.mongo.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class BlogController {

    private final JianshuService jianshuService;

    private final CommentService commentService;

    @GetMapping("/p/{articleId}")
    public String article(@PathVariable String articleId, Model model){
        ArticleBean article= jianshuService.parseArticle(articleId);
        article.setArticleId(articleId);
        model.addAttribute("article", article);
        List<Comment> comments = commentService.listByArticle(articleId);
        model.addAttribute("comments", comments);
        return "article";
    }

    @GetMapping("/blog")
    public String blog(Model model){
        JianshuBean jianshuBean = jianshuService.parseUserAndBlogs();
        model.addAttribute("jianshu", jianshuBean);
        return "blog";
    }

    @GetMapping("/blog/{page}")
    @ResponseBody
    public ResponseBean blog(@PathVariable("page") int page){
        JianshuBean jianshuBean = jianshuService.parseArticleList(page);
        return ResponseBean.success(jianshuBean);
    }

    @GetMapping("/nb/{id}")
    public String notebook(@PathVariable("id") String id,  Model model){
        JianshuBean jianshuBean = jianshuService.parseNoteBooks(id);
        model.addAttribute("jianshu", jianshuBean);
        return "blog";
    }

    @GetMapping("/nb/{id}/{page}")
    @ResponseBody
    public ResponseBean notebook(@PathVariable("id") String id, @PathVariable("page") int page){
        JianshuBean jianshuBean = jianshuService.parseArticleListInNotebook(id, page);
        return ResponseBean.success(jianshuBean);
    }

    @GetMapping("/nbs/{page}")
    @ResponseBody
    public ResponseBean notebooks(@PathVariable("page") int page){
        JianshuBean jianshuBean = jianshuService.parseNotebookList(page);
        return ResponseBean.success(jianshuBean);
    }

    @GetMapping("/c/{id}")
    public String collection(@PathVariable("id") String id,  Model model){
        JianshuBean jianshuBean = jianshuService.parseCollections(id);
        model.addAttribute("jianshu", jianshuBean);
        return "blog";
    }

    @GetMapping("/c/{id}/{page}")
    @ResponseBody
    public ResponseBean collection(@PathVariable("id") String id, @PathVariable("page") int page){
        JianshuBean jianshuBean = jianshuService.parseArticleListInCollection(id, page);
        return ResponseBean.success(jianshuBean);
    }

    @GetMapping("/cs/{page}")
    @ResponseBody
    public ResponseBean collections(@PathVariable("page") int page){
        JianshuBean jianshuBean = jianshuService.parseCollectionList(page);
        return ResponseBean.success(jianshuBean);
    }

}
