package com.anoyi.mongo.repository;

import com.anoyi.bean.JianshuBean;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by szz on 2018/4/25 23:46.
 * Email szhz186@gmail.com
 */
public interface OwnCollectionRepository extends MongoRepository<JianshuBean.OwnCollection,String>{
}