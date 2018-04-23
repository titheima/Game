package com.anoyi.mongo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anoyi.bean.Comment;
import com.anoyi.mongo.repository.CommentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 留言相关
 */
@Service
@AllArgsConstructor
@Log4j2
public class CommentService {

    private final CommentRepository commentRepository;

    /**
     * 保存评论
     */
    public void save(Comment comment){
        comment.setCreateTime(new Date());
        commentRepository.save(comment);
    }

    /**
     * 查询文章评论
     */
    public List<Comment> listByArticle(String articleId){
        return commentRepository.findAllByShowAndArticleId(true, articleId, Sort.by(Sort.Order.desc("createTime")));
    }

    public List<Comment> findAll() {
        return commentRepository.findAll(Sort.by(Sort.Order.desc("createTime")));
    }

    public Comment findOne(String id) {
        Optional<Comment> comment = commentRepository.findById(id);
        String jsonStr = JSON.toJSON(comment).toString();
        return JSON.parseObject(jsonStr, Comment.class);
    }
}
