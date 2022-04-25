package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.lookup.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description
 * @Date 2022/4/4
 * @Author yuze
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
        User user = beanFactory.getBean("user-by-static-method", User.class);
        User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);
        User userByFactoryBean2 = beanFactory.getBean("user-by-factory-bean2", User.class);
        System.out.println(user);
        System.out.println(userByFactoryBean);
        System.out.println(userByFactoryBean2);
        System.out.println(user == userByFactoryBean);
        System.out.println(user == userByFactoryBean2);


    }

}
