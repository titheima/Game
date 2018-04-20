package com.anoyi.controller.aop;

import com.anoyi.config.CustomAttributesConfig;
import com.anoyi.mongo.model.UserBean;
import com.anoyi.mongo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

/**
 * 自定义属性
 */
@ControllerAdvice
@AllArgsConstructor
public class CustomAttributesAdvice {

    private final CustomAttributesConfig customAttributesConfig;

    private final UserService userService;

    /**
     * 全局 Model 中添加自定义属性
     */
    @ModelAttribute("custom")
    public CustomAttributesConfig customAttributes(){
        return customAttributesConfig;
    }

    /**
     * 全局 Model 中添加用户信息
     */
    @ModelAttribute("user")
    public UserBean userAttributes(Principal principal){
        if (principal == null){
            return null;
        }
        String username = principal.getName();
        return userService.getByUsername(username);
    }

}
