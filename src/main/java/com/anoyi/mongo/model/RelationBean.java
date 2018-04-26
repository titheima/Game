package com.anoyi.mongo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 好友关系
 */
@Data
@NoArgsConstructor
@Document
public class RelationBean {

    @Id
    private String id;

    // 自身 ID
    private String me;

    // 好友 ID
    private String friend;

    public RelationBean(String me, String friend) {
        this.me = me;
        this.friend = friend;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMe() {
        return me;
    }

    public void setMe(String me) {
        this.me = me;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }
}
