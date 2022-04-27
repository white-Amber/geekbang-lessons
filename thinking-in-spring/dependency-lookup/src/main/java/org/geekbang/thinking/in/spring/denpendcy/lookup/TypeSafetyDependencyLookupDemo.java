package org.geekbang.thinking.in.spring.denpendcy.lookup;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.lookup.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Date 2022/4/25
 * @Author yuze
 */
public class TypeSafetyDependencyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        applicationContext.refresh();

        // 演示BeanFactory#getBean 方法的安全性
        displayBeanFactoryGetBean(applicationContext);
        // 演示ObjectFactory#getObject 方法的安全性
        displayObjectFactoryGetObject(applicationContext);
        // 演示ObjectProvider#getIfAvailable 方法安全性。如果bean不存在返回null
        displayObjectProviderIfAvailable(applicationContext);

        // 演示ListableBeanFactory#getBeansOfType 方法的安全性。如果bean不存在返回一个空集合。
        displayListableBeanFactoryGetBeansOfType(applicationContext);
        // 演示ObjectProvider#stream 方法的安全性。如果bean不存在返回一个空集合。
        displayObjectProviderStream(applicationContext);

        applicationContext.close();


    }

    private static void displayObjectProviderStream(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        List<User> collect = beanProvider.stream().collect(Collectors.toList());
        System.err.println("collect======" + collect);
        printStack("displayObjectProviderStream", () -> beanProvider.stream().forEach(System.err::println));

    }

    private static void displayListableBeanFactoryGetBeansOfType(AnnotationConfigApplicationContext applicationContext) {
        Map<String, User> beansOfType = applicationContext.getBeansOfType(User.class);
        System.err.println("beansOfType=====" + beansOfType);
        printStack("displayListableBeanFactoryGetBeansOfType", () -> applicationContext.getBeansOfType(User.class));

    }

    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        User ifAvailable = beanProvider.getIfAvailable();
        System.err.println("ifAvailable========" + ifAvailable);
        printStack("displayObjectProviderIfAvailable", () -> beanProvider.getIfAvailable());

    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        printStack("displayObjectFactoryGetObject", () -> beanProvider.getObject());


    }

    private static void displayBeanFactoryGetBean(AnnotationConfigApplicationContext applicationContext) {
        printStack("displayBeanFactoryGetBean", () -> applicationContext.getBean(User.class));
    }

    public static void printStack(String methodName, Runnable runnable) {
        System.err.println("methodName = " + methodName);
        try {
            runnable.run();
        } catch (BeansException exception) {
            exception.printStackTrace();
        }
    }


}
