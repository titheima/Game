package com.anoyi.mongo.repository;

import com.anoyi.bean.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findByNameLike(String name);
}