package com.anymk.mongo.repository;

import com.anymk.bean.JianshuBean;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by szz on 2018/4/25 23:46.
 * Email szhz186@gmail.com
 */
public interface AuthorRepository extends MongoRepository<JianshuBean.Author,String>{
}
