package com.gnnt.mybatisgenerator.config.shiro;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Data
@Component
@ConfigurationProperties(prefix = "shiro")
public class ShiroEncryptionPorperties {
    /**
     * 加密方式
     */
    private String encodeWay;
    /**
     * 加密次数
     */
    private Integer encodeNum;

}
