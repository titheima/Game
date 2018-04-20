package com.anoyi.chat.Model;

import java.util.Date;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * 基本消息类型
 */
@Data
public class BaseMessage {

    @Id
    private String id;

    // 发送者
    private String sender;

    // 接受者
    private String receiver;

    // 接受者类型
    private String receiverType;

    // 消息内容
    private String content;

    // 发送时间
    private Date sendTime;

}
