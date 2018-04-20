package com.anoyi.mongo.repository;

import com.anoyi.bean.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

}