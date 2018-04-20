package com.anoyi.api;

import com.anoyi.bean.ResponseBean;
import com.anoyi.bean.UserInfo;
import com.anoyi.mongo.model.RelationBean;
import com.anoyi.mongo.model.UserBean;
import com.anoyi.mongo.service.RelationService;
import com.anoyi.mongo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserAPI {

    private final RelationService relationService;

    private final UserService userService;

    /**
     * 查询个人信息
     */
    @GetMapping("/userInfo")
    public ResponseBean userInfo(@AuthenticationPrincipal Principal principal) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UserBean userBean = userService.getByUsername(principal.getName());
        return ResponseBean.success(userBean);
    }

    /**
     * 添加好友
     */
    @PostMapping("/friend")
    public ResponseBean add(@AuthenticationPrincipal Principal principal, @RequestParam String friendUsername) {
        RelationBean relationBean = new RelationBean(principal.getName(), friendUsername);
        relationService.addFriend(relationBean);
        return ResponseBean.success(null);
    }

    /**
     * 获取好友列表
     */
    @GetMapping("/friend")
    public ResponseBean friends(@AuthenticationPrincipal Principal principal) {
        String username = principal.getName();
        List<String> friendUsernameList = relationService.friends(username);
        List<UserBean> friendList = userService.getAllByUsernameList(friendUsernameList);
        Collections.sort(friendList);
        return ResponseBean.success(friendList);
    }

    /**
     * 删除好友
     */
    @DeleteMapping("/friend")
    public ResponseBean remove(@AuthenticationPrincipal Principal principal, @RequestParam String friendId) {
        relationService.removeFriend(principal.getName(), friendId);
        return ResponseBean.success(null);
    }

    /**
     * 查看用户信息
     */
    @GetMapping("/user/{username}")
    public ResponseBean getUser(@AuthenticationPrincipal Principal principal, @PathVariable String username) {
        UserBean userBean = userService.getByUsername(username);
        if (userBean == null) {
            throw new RuntimeException("用户不存在");
        }
        UserInfo userInfo = userService.toUserInfo(principal.getName(), userBean);
        return ResponseBean.success(userInfo);
    }

}
