package com.anoyi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("jianshu")
@Data
public class JianshuConfig {

    private String userId;

}
