package com.anoyi.mongo.repository;

import com.anoyi.mongo.model.RelationBean;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RelationRepository extends MongoRepository<RelationBean, String> {

    List<RelationBean> findByMeOrFriend(String userId, String friendId);

    RelationBean findByMeAndFriend(String userId, String friendId);

    void removeByMeAndFriend(String userId, String friendId);

}
