package com.anymk.config.sercurity;

import com.anymk.mongo.model.UserBean;
import com.anymk.mongo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户登录及授权配置
 */
@Service
@AllArgsConstructor
public class AnyUserDetailsService implements UserDetailsService {

    private final static String ROLE_TAG = "USER";

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserBean userBean = userService.getByUsername(username);
        if (userBean == null) {
            throw new UsernameNotFoundException("username:" + username);
        }
        UserDetails userPrincipal = createUserPrincipal(userBean);
        return userPrincipal;
    }


    /**
     *
     * @param userBean
     * @return
     *
     * 如果是admin用户就给一个admin的角色
     */
    private UserDetails createUserPrincipal(UserBean userBean){
        // 用户认证（用户名，密码，权限）
        if ("admin".equalsIgnoreCase(userBean.getUsername())) {
            return org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                    .username(userBean.getUsername())
                    .password(userBean.getPassword())
                    .roles("admin").build();
        }

        return org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
                .username(userBean.getUsername())
                .password(userBean.getPassword())
                .roles(ROLE_TAG).build();
    }

}