package com.anymk.mongo.repository;

import com.anymk.bean.ArticleBean;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 完全字段的文章
 * Created by szz on 2018/4/25 0:27.
 * Email szhz186@gmail.com
 */
public interface ArticleRepository extends MongoRepository<ArticleBean,String>{

}
