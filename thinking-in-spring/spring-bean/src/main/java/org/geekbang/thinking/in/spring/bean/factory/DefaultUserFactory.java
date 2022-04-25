package org.geekbang.thinking.in.spring.bean.factory;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Description
 * @Date 2022/4/4
 * @Author yuze
 */
@Data
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {


    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct 初始化 DefaultUserFactory...");
    }

    public void initUserFactory() {
        System.out.println("自定义方法 初始化 DefaultUserFactory...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet() 初始化 DefaultUserFactory...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy : UserFactory 销毁中...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy : UserFactory 销毁中...");
    }

    public void doDestroy() {
        System.out.println("doDestroy() : UserFactory 销毁中...");
    }
}
