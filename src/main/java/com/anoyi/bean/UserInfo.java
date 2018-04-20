package com.anoyi.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class UserInfo {

    @Id
    private String id;

    // 用户名
    @Indexed(unique = true)
    private String username;

    // 昵称
    private String nickname;

    // 头像
    private String avatar;

    // 在线状态
    private Integer status;

    // 是否是好友
    private boolean friend;

}
