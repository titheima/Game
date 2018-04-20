package com.anoyi.mongo.service;

import com.anoyi.bean.UserInfo;
import com.anoyi.mongo.model.UserBean;
import com.anoyi.mongo.repository.RelationRepository;
import com.anoyi.mongo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 用户相关
 */
@Service
@AllArgsConstructor
@Log4j2
public class UserService {

    private static final String PASSWORD_RULE = "^(\\w){6,20}$";

    private final UserRepository userRepository;

    private final RelationRepository relationRepository;

    private final MongoTemplate mongoTemplate;

    /**
     * 添加新用户
     */
    public UserBean addUser(UserBean userBean) {
        paramsCheck(userBean);
        userBean.init();
        log.info("addUser:" + userBean.toString());
        return userRepository.save(userBean);
    }

    /**
     * 查询用户信息
     */
    public UserBean getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * 注册用户参数校验
     */
    private void paramsCheck(UserBean userBean) {
        log.info("paramsCheck:" + userBean.toString());
        String username = userBean.getUsername();
        UserBean result = getByUsername(username);
        if (result != null) {
            throw new RuntimeException("用户已存在");
        }
        String password = userBean.getPassword();
        if (!Pattern.compile(PASSWORD_RULE).matcher(password).matches()) {
            throw new RuntimeException("密码必须为 6~20 位字母、数字、下划线");
        }
        String nickname = userBean.getNickname();
        if (StringUtils.isEmpty(nickname)) {
            throw new RuntimeException("用户昵称不能为空");
        }
    }

    /**
     * 获取在线用户
     */
    public List<UserBean> getOnlineUsers() {
        return new ArrayList<>();
    }

    /**
     * 查询多个用户
     */
    public List<UserBean> getAllByUsernameList(List<String> friendUsernameList) {
        Query query = Query.query(Criteria.where("username").in(friendUsernameList));
        return mongoTemplate.find(query, UserBean.class);
    }

    /**
     * 为用户信息添加是否为好友的字段
     */
    public UserInfo toUserInfo(String username, UserBean other) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(other, userInfo);
        boolean isFriend = relationRepository.findByMeAndFriend(username, other.getUsername()) != null
                || relationRepository.findByMeAndFriend(other.getId(), username) != null;
        userInfo.setFriend(isFriend);
        return userInfo;
    }

    /**
     * 查找相关用户
     */
    public List<UserBean> searchUser(String keyword){
        return userRepository.findTop10ByUsernameLikeOrNicknameLike(keyword, keyword);
    }

}
