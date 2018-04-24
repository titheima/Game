package com.anoyi.mongo.repository;

import com.anoyi.bean.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by szz on 2018/4/23 22:08.
 * Email szhz186@gmail.com
 */
public interface ImageRepsotory extends MongoRepository<Image,String>{

}
