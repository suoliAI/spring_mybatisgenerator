package com.gnnt.mybatisgenerator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring的事务机制提供了一个PlatformTransactionManager接口，不同的数据访问技术的事务使用不同的接口实现
 * JDBC =============> DataSourceTransactionManager
 * JPA===============> JpaTransactionManager
 * Hibernate=========> HibernateTransactionManager
 * JDO ==============> JdoTransactionManager
 * 分布式事务=========> JtaTransactionManager
 * @EnableTransactionManagement 注解在配置类上来开启声明式事务的支持 配置后，Spring容器会自动扫描注解@Transactional的方法和类。
 * 事务自动配置类：TransactionAutoConfiguration
 */
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@MapperScan(basePackages = {"com.gnnt.mybatisgenerator.mapper"})
@EnableAspectJAutoProxy //开启aop切面
@SpringBootApplication
public class MybatisGeneratorApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(MybatisGeneratorApplication.class, args);
    }

}
