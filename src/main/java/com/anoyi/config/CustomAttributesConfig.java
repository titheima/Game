package com.anoyi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 全局自定义属性配置
 */
@Configuration
@ConfigurationProperties("custom")
@Data
public class CustomAttributesConfig {

    // github 地址
    private String githubUrl="https://github.com/gentoo111/anymk.git";

    // 其他代码仓库地址
    private String gitlabUrl="https://gitee.com/szz715/anymk.git";

    // qq
    private String qq="85463138";

    // ICP 备案号
    private String icp="2018 湘ICP备17010933号-1";

    // 打赏二维码
    private List<String> rewardImages;

    // 打赏描述
    private String rewardDesc="";

}
