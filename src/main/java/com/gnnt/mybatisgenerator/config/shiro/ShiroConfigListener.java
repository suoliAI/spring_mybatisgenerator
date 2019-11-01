package com.gnnt.mybatisgenerator.config.shiro;

import com.gnnt.mybatisgenerator.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * springBoot shiro 中 realm中注入service 使得 service的事务失效：
 *  由于自定义shiro的权限认证类CustomRealm  需要userService的支持
 *  如果在CustomRealm类中使用@Autowired直接注入会出现userService被标注了@Transactional的方法 对于出现异常 事务不能回滚问题
 *  因此在容器刷新完成后，从容器冲取出userService 手动赋值
 */
@Slf4j
@Component
public class ShiroConfigListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("ShiroConfigListener的onApplicationEvent方法在容器刷新完成后将容器中的userService注入到shiro的CustomRealm中....");
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        PlatformTransactionManager bean = applicationContext.getBean(PlatformTransactionManager.class);
        log.info("事务管理器[{}]",bean.getClass().getName());
        CustomRealm customRealm = applicationContext.getBean(CustomRealm.class);
        IUserService userService = applicationContext.getBean(IUserService.class);
        customRealm.setUserService(userService);

    }
}
