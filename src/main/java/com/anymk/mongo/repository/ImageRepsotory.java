package com.anymk.mongo.repository;

import com.anymk.bean.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by szz on 2018/4/23 22:08.
 * Email szhz186@gmail.com
 */
public interface ImageRepsotory extends MongoRepository<Image,String>{

}
