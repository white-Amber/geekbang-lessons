package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.factory.RoleService;
import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @Description
 * @Date 2022/4/5
 * @Author yuze
 */
@Configuration
public class BeanInitDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration 配置类
        applicationContext.register(BeanInitDemo.class);
//        applicationContext.register(RoleService.class);
        // 启动 Spring上下文
        applicationContext.refresh();
        System.out.println("applicationContext 启动完成");

        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println("applicationContext 准备关闭...");
        // 关闭 Spring 上下文
        applicationContext.close();
        System.out.println("applicationContext 完成关闭...");


    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    public UserFactory getUserFactory() {
        return new DefaultUserFactory();
    }

}
