package com.anoyi.chat.Model;

import lombok.Data;

@Data
public class ChatMessage {

    // 用户账号
    private String username;

    // 用户昵称
    private String nickname;

    // 用户头像
    private String avatar;

    // 消息内容
    private String content;

    // 发送时间
    private String sendTime;

}
