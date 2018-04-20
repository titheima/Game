package com.anoyi.mongo.service;

import com.anoyi.mongo.model.RelationBean;
import com.anoyi.mongo.model.UserBean;
import com.anoyi.mongo.repository.RelationRepository;
import com.anoyi.mongo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 好友关系
 */
@Service
@Log4j2
@AllArgsConstructor
public class RelationService {

    private final RelationRepository relationRepository;

    private final UserRepository userRepository;

    /**
     *  添加好友
     */
    public RelationBean addFriend(RelationBean relationBean) {
        paramsCheck(relationBean);
        log.info("addFriend:" + relationBean.toString());
        return relationRepository.save(relationBean);
    }

    /**
     * 删除好友
     */
    public void removeFriend(String userId, String friendId){
        relationRepository.removeByMeAndFriend(userId, friendId);
        relationRepository.removeByMeAndFriend(friendId, userId);
    }

    /**
     * 参数校验
     */
    private void paramsCheck(RelationBean relationBean) {
        if (relationBean.getMe().equals(relationBean.getFriend())){
            throw new RuntimeException("不能添加自己为好友");
        }
        UserBean result = userRepository.findByUsername(relationBean.getFriend());
        if (result == null) {
            throw new RuntimeException("用户不存在");
        }
        RelationBean hasRelationBean = relationRepository.findByMeAndFriend(relationBean.getMe(), relationBean.getFriend());
        if (hasRelationBean != null){
            throw new RuntimeException("你们已经是好友关系啦");
        }else {
            hasRelationBean = relationRepository.findByMeAndFriend(relationBean.getMe(), relationBean.getFriend());
        }
        if (hasRelationBean != null){
            throw new RuntimeException("你们已经是好友关系啦");
        }
    }

    /**
     * 查询好友
     */
    public List<String> friends(String username) {
        List<String> friendUsernameList = new ArrayList<>();
        List<RelationBean> relations = relationRepository.findByMeOrFriend(username, username);
        for (RelationBean relation : relations) {
            String friendUsername = username.equals(relation.getMe()) ? relation.getFriend() : relation.getMe();
            friendUsernameList.add(friendUsername);
        }
        return friendUsernameList;
    }

}
