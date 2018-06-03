package com.anymk.mongo.repository;

import com.anymk.bean.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByShowAndArticleId(boolean show, String articleId, Sort sort);

}