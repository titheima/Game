package com.anoyi.mongo.repository;

import com.anoyi.bean.CNCFProject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CNCFProjectRepository extends MongoRepository<CNCFProject, String> {

}