package com.anoyi.api;

import com.anoyi.bean.Comment;
import com.anoyi.bean.MessageBean;
import com.anoyi.bean.ResponseBean;
import com.anoyi.mongo.model.UserBean;
import com.anoyi.mongo.service.CommentService;
import com.anoyi.mongo.service.UserService;
import com.anoyi.tools.DingTalkTools;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

/**
 * 通用 API
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/common")
@Log4j2
public class CommonAPI {

	private final UserService userService;

	private final CommentService commentService;

    /**
     * 用户注册
     */
	@PostMapping(value = "/register")
	public ResponseBean register(@RequestParam String username, @RequestParam String password, @RequestParam String nickname) {
	    UserBean userBean = new UserBean(username, password, nickname);
        userService.addUser(userBean);
		return ResponseBean.success(userBean);
	}

    /**
     * 在线用户
     */
    @GetMapping(value = "/online")
    public ResponseBean online() {
        return ResponseBean.success(userService.getOnlineUsers());
    }

    /**
     * 查找用户
     */
    @PostMapping(value = "/user")
    public ResponseBean findUser(@RequestParam String keyword){
        List<UserBean> userBeanList = userService.searchUser(keyword);
        Collections.sort(userBeanList);
        return ResponseBean.success(userBeanList);
    }

    /**
     * 用户留言
     */
    @PostMapping(value = "/message")
    public ResponseBean message(MessageBean messageBean) {
        DingTalkTools.textMessage(messageBean);
        return ResponseBean.success(null);
    }

    /**
     * 文章评论
     */
    @PostMapping(value = "/comment")
    public ResponseBean message(Comment comment, Principal principal) {
        if (principal != null){
            UserBean user = userService.getByUsername(principal.getName());
            comment.setName(user.getNickname());
            comment.setAvatar(user.getAvatar());
        }
        comment.setShow(false);
        commentService.save(comment);
        MessageBean messageBean = new MessageBean();
        messageBean.setContent(comment.getContent());
        messageBean.setEmail(comment.getEmail());
        messageBean.setNickname(comment.getName());
        DingTalkTools.textMessage(messageBean);
        return ResponseBean.success(null);
    }

}
