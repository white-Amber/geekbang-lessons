package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.lookup.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Description
 * @Date 2022/4/4
 * @Author yuze
 */
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册配置类
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
        registerUserBeanDefinition(applicationContext, "liSi-user");
        registerUserBeanDefinition(applicationContext, null);
        // 启动容器
        applicationContext.refresh();
        // 按照类型查找依赖
        System.out.println("Config 类型的所有 beans：" + applicationContext.getBeansOfType(Config.class));
        System.out.println("User 类型的所有 beans：" + applicationContext.getBeansOfType(User.class));

        // 关闭容器
        applicationContext.close();

    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry beanDefinitionRegistry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class)
                .addPropertyValue("age", 20)
                .addPropertyValue("name", "liSi");
        if (StringUtils.hasText(beanName)) {
            // 1. 使用命名的方式注册 bean
            beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            // 2. 使用非命名的方式注册bean
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), beanDefinitionRegistry);
        }
    }

    @Component
    public static class Config {

        @Bean(name = {"user", "zhangSan-user"})
        public User user() {
            User user = new User();
            user.setAge(18);
            user.setName("zhangSan");
            return user;
        }

    }
}
