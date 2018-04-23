package com.anoyi.mongo.repository;

import com.anoyi.bean.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, Long> {

    List<Book> findByNameLike(String name);

}