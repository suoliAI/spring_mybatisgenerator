package com.gnnt.mybatisgenerator.untils;

import com.gnnt.mybatisgenerator.config.shiro.ShiroEncryptionPorperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.UnknownAlgorithmException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Component
public class ShiroEncryption implements ApplicationContextAware {

    private static ShiroEncryptionPorperties shiroEncryptionPorperties;
    /***
     * 对用户的密码进行MD5加密
     * 做成工具类
     */
    public static String shiroEncryption(String password,String salt) {

        // shiro 自带的工具类生成salt
        //String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        // 加密次数
        int times = shiroEncryptionPorperties.getEncodeNum();
        // 算法名称
        String algorithmName = shiroEncryptionPorperties.getEncodeWay();

        log.info("加密方式[{}], 加密次数[{}]",algorithmName,times);

        String encodedPassword = new SimpleHash(algorithmName,password,salt,times).toString();
        // 返回加密后的密码
        return encodedPassword;
    }

    public ShiroEncryptionPorperties getShiroEncryptionPorperties() {
        return shiroEncryptionPorperties;
    }

    public void setShiroEncryptionPorperties(ShiroEncryptionPorperties shiroEncryptionPorperties) {
        this.shiroEncryptionPorperties = shiroEncryptionPorperties;
    }



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        PlatformTransactionManager bean = applicationContext.getBean(PlatformTransactionManager.class);
        log.info("事务管理器[{}]",bean.getClass().getName());
        this.setShiroEncryptionPorperties(applicationContext.getBean(ShiroEncryptionPorperties.class));
    }
}
