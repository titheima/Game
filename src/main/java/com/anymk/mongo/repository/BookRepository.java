package com.anymk.mongo.repository;

import com.anymk.bean.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, Long> {

    List<Book> findByNameLike(String name);

}