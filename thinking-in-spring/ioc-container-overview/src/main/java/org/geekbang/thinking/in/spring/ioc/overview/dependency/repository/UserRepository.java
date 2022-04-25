package org.geekbang.thinking.in.spring.ioc.overview.dependency.repository;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.lookup.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * @Description 用户信息仓库
 * @Date 2022/1/23
 * @Author yuze
 */
public class UserRepository {

    private User user;

    private Collection<User> users; // 自定义 Bean

    /**
     * 自动注入的是 DefaultListableBeanFactory
     */
    private BeanFactory beanFactory;// 内建的 非 Bean 对象

    /**
     * 自动注入的是 ClassPathXmlApplicationContext
     */
    private ObjectFactory<ApplicationContext> objectFactory;

    public ObjectFactory<ApplicationContext> getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory<ApplicationContext> objectFactory) {
        this.objectFactory = objectFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
