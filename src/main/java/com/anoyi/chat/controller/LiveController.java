package com.anoyi.chat.controller;

import com.alibaba.fastjson.JSONObject;
import com.anoyi.mongo.model.UserBean;
import com.anoyi.mongo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class LiveController {

    private final UserService userService;

    private final SimpMessagingTemplate template;

    @MessageMapping("/live")
    public void liveMessage(Principal principal, String message){
        String username = principal.getName();
        UserBean user = userService.getByUsername(username);
        JSONObject json = new JSONObject();
        json.put("content", message);
        json.put("avatar", user.getAvatar());
        template.convertAndSend("/topic/live", json.toJSONString());
    }

}
