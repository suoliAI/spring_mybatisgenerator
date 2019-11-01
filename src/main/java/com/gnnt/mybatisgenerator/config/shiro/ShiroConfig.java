package com.gnnt.mybatisgenerator.config.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Configuration
public class ShiroConfig {
    @Autowired
    private ShiroEncryptionPorperties shiroEncryptionPorperties;


    //将自己的验证方式加入容器
    @Bean
    public CustomRealm myShiroRealm() {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }

    /**
     * 设置加密方式及加密次数
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        log.info("properties[{}]",shiroEncryptionPorperties.toString());
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 使用md5 算法进行加密
        hashedCredentialsMatcher.setHashAlgorithmName(shiroEncryptionPorperties.getEncodeWay());
        // 设置散列次数： 意为加密几次
        hashedCredentialsMatcher.setHashIterations(shiroEncryptionPorperties.getEncodeNum());

        return hashedCredentialsMatcher;
    }

    /**
     * session管理器
     * @return
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 设置session过期时间10分钟
        sessionManager.setGlobalSessionTimeout(60000L);
        sessionManager.setSessionIdCookie(new SimpleCookie("userID"));
        return sessionManager;
    }

    /**
     * 记住我 功能管理器
     * @return
     */
    public CookieRememberMeManager cookieRememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");

        simpleCookie.setMaxAge(3*60);
        //设置只允许http访问
        simpleCookie.setHttpOnly(true);
        //simpleCookie.setDomain("");
        cookieRememberMeManager.setCookie(simpleCookie);
        cookieRememberMeManager.setCipherKey(Base64.getDecoder().decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }


    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager  securityManager(CustomRealm realm,DefaultWebSessionManager defaultWebSessionManager ) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setSessionManager(defaultWebSessionManager);
        securityManager.setRememberMeManager(cookieRememberMeManager());
        return securityManager;
    }
    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<>();

        /**
         * Shiro连接约束配置,即过滤链的定义
         *                 此处可配合这篇文章来理解各个过滤连的作用http://blog.csdn.net/jadyer/article/details/12172839
         *                 下面value值的第一个'/'代表的路径是相对于HttpServletRequest.getContextPath()的值来的
         *                 shiro的默认过滤器分为两种：认证过滤器：anon，authcBasic，auchc，user 和授权过滤器：perms，roles，ssl，rest，port
         *                 anon：它对应的过滤器里面是空的,什么都没做,这里.do和.jsp后面的*表示参数,比方说login.jsp?main这种
         *                 authc：该过滤器下的页面必须验证后才能访问,它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
         *                 user: 表示身份验证通过，或者记住我
         *                 rememberMe:记住我设置后不用再需要登录
         */
        // 配置不会被拦截的链接 顺序判断
        //登出
        map.put("/logout", "logout");
        map.put("/logon","anon");
        map.put("/loginPage","anon");

        //添加静态资源过滤放弃
        map.put("/images/*","anon");
        map.put("/css/*","anon");
        map.put("/fonts/*","anon");


        //对所有用户认证
        map.put("/**", "user");

        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/loginPage");
        //首页 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");

        //错误页面，认证不通过跳转 //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;

    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     *
     * @return
     */
    @Bean
    //@DependsOn({"lifecycleBeanPostProcessor"})
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
