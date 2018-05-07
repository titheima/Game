package com.anoyi.mongo.repository;

import com.anoyi.bean.ArticleBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * 完全字段的文章
 * Created by szz on 2018/4/25 0:27.
 * Email szhz186@gmail.com
 */
public interface ArticleRepository extends MongoRepository<ArticleBean,String>{

}
