package com.anoyi.controller;

import com.anoyi.bean.Comment;
import com.anoyi.bean.ResponseBean;
import com.anoyi.mongo.repository.CommentRepository;
import com.anoyi.mongo.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by szz on 2018/4/23 19:01.
 * Email szhz186@gmail.com
 */
@Controller
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;

    private CommentRepository commentRepository;
    @RequestMapping("/admin/comment")
    public String comment(Model model){
        List<Comment> comments = commentService.findAll();
        model.addAttribute("comments", comments);
        return "admin/comment";
    }
    @RequestMapping("/admin/deleteAll")
    public String deleteAll(){
        commentRepository.deleteAll();
        return "redirect:/admin/comment";
    }

    @RequestMapping("/admin/comment_replay")
    @ResponseBody
    public ResponseBean comment(String id,String content){
        Comment comment=commentService.findOne(id);
        comment.setContent(comment.getContent()+"<br><span style='color:red;'>我</span>: "+content);
        commentService.save(comment);
        return ResponseBean.success(null);
    }

    @RequestMapping("/admin/comment_save")
    public String commentSave(Comment comment){
        comment.setId("5adde5f1f8c2f707982298f9");
        comment.setAvatar("https://upload.jianshu.io/collections/images/514184/WechatIMG959.jpeg?imageMogr2/auto-orient/strip|imageView2/1/w/240/h/240");
        comment.setEmail("admin@sina.com");
        comment.setName("最大的大人");
        comment.setContent("<span style='color:#87ed92'>最大的大人</span>lailailai");
        comment.setArticleId("1");
        comment.setShow(true);
        comment.setCreateTime(new Date());
        commentRepository.save(comment);
        return "/admin/comment";
    }

}
