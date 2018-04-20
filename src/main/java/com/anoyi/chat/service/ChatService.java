package com.anoyi.chat.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.anoyi.chat.Model.BaseMessage;
import com.anoyi.chat.Model.ChatMessage;
import com.anoyi.mongo.model.UserBean;
import com.anoyi.mongo.service.UserService;
import com.anoyi.tools.RobotTools;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@AllArgsConstructor
@Log4j2
public class ChatService {

    private static final String ROBOT = "IM_ROBOT";

    private static final String ALL = "IM_ALL";

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");

    private final SimpMessagingTemplate template;

    private final UserService userService;

    /**
     * 发送消息
     */
    public void sendMessage(BaseMessage baseMessage) {
        String receiver = baseMessage.getReceiver();
        switch (receiver) {
            case ROBOT:
                robotReply(baseMessage);
                break;
            case ALL:
                sendToAll(JSON.toJSONString(baseMessage));
                break;
            default:
                sendToUser(JSON.toJSONString(baseMessage));
                break;
        }
    }

    /**
     * 发送给用户
     */
    private void sendToUser(String baseMessage) {
        JSONObject json = JSONObject.parseObject(baseMessage);
        ChatMessage chatMessage = createChatMessage(json.getString("sender"), json.getString("content"));
        log.info("[发送][个人消息][{} -> {}] <{}>", chatMessage.getUsername(), json.getString("receiver"), chatMessage.getContent());
        template.convertAndSendToUser(json.getString("receiver"), "/topic/chat", JSON.toJSONString(chatMessage));
    }

    /**
     * 发送给所有人
     */
    private void sendToAll(String baseMessage) {
        JSONObject json = JSONObject.parseObject(baseMessage);
        ChatMessage chatMessage = createChatMessage(json.getString("sender"), json.getString("content"));
        log.info("[发送][全服消息][{}] <{}>", json.getString("receiver"), chatMessage.getContent());
        template.convertAndSend("/topic/notice", JSON.toJSONString(chatMessage));
    }

    /**
     * 机器人回复
     */
    private void robotReply(BaseMessage baseMessage) {
        String replyContent = RobotTools.reply(baseMessage.getContent());
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setAvatar("http://img.hb.aicdn.com/79cf2c86f7c626ae5d135786b912803275fcd0c82423-LJyhZR_fw658");
        chatMessage.setNickname("AnyIM 小沫");
        chatMessage.setContent(replyContent);
        chatMessage.setSendTime(simpleDateFormat.format(new Date()));
        chatMessage.setUsername(ROBOT);
        log.info("[发送][机器人]" + baseMessage.getSender());
        template.convertAndSendToUser(baseMessage.getSender(), "/topic/chat", JSON.toJSONString(chatMessage));
    }

    private ChatMessage createChatMessage(String username, String message) {
        ChatMessage chatMessage = new ChatMessage();
        UserBean userBean = userService.getByUsername(username);
        chatMessage.setAvatar(userBean.getAvatar());
        chatMessage.setNickname(userBean.getNickname());
        chatMessage.setContent(message);
        chatMessage.setSendTime(simpleDateFormat.format(new Date()));
        chatMessage.setUsername(userBean.getUsername());
        return chatMessage;
    }

}
