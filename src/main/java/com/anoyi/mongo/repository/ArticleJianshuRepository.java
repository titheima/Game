package com.anoyi.mongo.repository;

import com.anoyi.bean.JianshuBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by szz on 2018/4/26 10:39.
 * Email szhz186@gmail.com
 */
public interface ArticleJianshuRepository extends MongoRepository<JianshuBean.Article,String> {
    Page<JianshuBean.Article> findByOwnId(String id,Pageable pageable);

    Page<JianshuBean.Article> findByNoteBookId(String id, Pageable pageable);

    List<JianshuBean.Article> findByTitleLike(String title);
}
