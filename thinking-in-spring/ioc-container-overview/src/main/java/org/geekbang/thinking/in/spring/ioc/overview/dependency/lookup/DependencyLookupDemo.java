package org.geekbang.thinking.in.spring.ioc.overview.dependency.lookup;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.annotation.Super;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @Description 依赖查找示例
 * @Date 2022/1/21
 * @Author yuze
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
//        lookupByType(beanFactory);
//        lookupCollectionByType(beanFactory);
//        User bean = beanFactory.getBean(User.class);
//        System.out.println(bean);

        lookupByAnnotation(beanFactory);

    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beansWithAnnotation =(Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println(beansWithAnnotation);
        }


    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> map = listableBeanFactory.getBeansOfType(User.class);
            System.out.println(map);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = beanFactory.getBean(ObjectFactory.class);
        System.out.println(objectFactory.getObject());
    }

    public static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User object = objectFactory.getObject();
        System.out.println(object);
    }

    public static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println(user);
    }


}
