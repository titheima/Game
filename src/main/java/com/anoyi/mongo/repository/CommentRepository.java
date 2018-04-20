package com.anoyi.mongo.repository;

import com.anoyi.bean.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByShowAndArticleId(boolean show, String articleId, Sort sort);

}