package com.gnnt.mybatisgenerator.config.shiro;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@Data
@Component
@ConfigurationProperties(prefix = "shiro")
public class ShiroPorperties {
    /**
     * 加密方式
     */
    private String algorithmName;
    /**
     * 加密次数
     */
    private Integer times;
    /**
     * session时长
     */
    private Long globalSessionTimeout;
    /**
     * 记住我时长
     */
    private Integer rememberCookieAge;

}
