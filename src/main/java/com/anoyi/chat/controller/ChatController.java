package com.anoyi.chat.controller;

import com.alibaba.fastjson.JSON;
import com.anoyi.chat.Model.BaseMessage;
import com.anoyi.chat.service.ChatService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Date;

/**
 * 即时通信
 */
@Controller
@AllArgsConstructor
@Log4j2
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/chat")
    public void chat(Principal principal, String message) {
        BaseMessage baseMessage = JSON.parseObject(message, BaseMessage.class);
        baseMessage.setSender(principal.getName());
        baseMessage.setSendTime(new Date());
        chatService.sendMessage(baseMessage);
    }

}
