package com.gnnt.mybatisgenerator.config.shiro;

import com.gnnt.mybatisgenerator.model.Permission;
import com.gnnt.mybatisgenerator.model.Role;
import com.gnnt.mybatisgenerator.model.User;
import com.gnnt.mybatisgenerator.service.IUserService;
import com.gnnt.mybatisgenerator.untils.ShiroEncryption;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;

@Slf4j
public class CustomRealm extends AuthorizingRealm{

    private IUserService userService;

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 授权，即角色或者权限验证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        //根据用户名去数据库查询用户信息
        User user = userService.selectUserByName(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getName());
            //添加权限
            for (Permission permission : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permission.getUrl());
            }
        }
        return simpleAuthorizationInfo;
    }
    /**
     * 身份认证/登录(账号密码验证)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.selectUserByName(token.getUsername());
        String oringnPassword = new String((char[]) token.getCredentials());
        log.info("username [{}] password [{}] ",token.getUsername(),oringnPassword);

        String encryptionCode = ShiroEncryption.shiroEncryption(oringnPassword, user.getSalt());

        log.info("加密使用的盐值[{}],加密后的密码是[{}]",user.getSalt(),encryptionCode);

        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,
                    user.getPassword(),//密码
                    ByteSource.Util.bytes(user.getSalt()),//加密使用的盐值
                    getName() //当前realm类的名字
            );
            return simpleAuthenticationInfo;
        }
    }
}
