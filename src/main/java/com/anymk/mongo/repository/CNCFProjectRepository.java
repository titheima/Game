package com.anymk.mongo.repository;

import com.anymk.bean.CNCFProject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CNCFProjectRepository extends MongoRepository<CNCFProject, String> {

}