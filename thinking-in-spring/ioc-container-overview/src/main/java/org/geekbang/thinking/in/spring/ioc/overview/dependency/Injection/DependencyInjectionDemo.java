package org.geekbang.thinking.in.spring.ioc.overview.dependency.Injection;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.annotation.Super;
import org.geekbang.thinking.in.spring.ioc.overview.dependency.lookup.User;
import org.geekbang.thinking.in.spring.ioc.overview.dependency.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * @Description 依赖注入示例
 * @Date 2022/1/23
 * @Author yuze
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
//        injectionToCollection(beanFactory);
        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");
        /*System.out.println(userRepository.getUser().toString());
        System.out.println(userRepository.getUsers());*/

        System.out.println(userRepository.getBeanFactory());
        System.out.println(beanFactory);
        System.out.println(userRepository.getBeanFactory() == beanFactory);
        System.out.println("========================");
        System.out.println(userRepository.getObjectFactory().getObject());
        System.out.println(userRepository.getObjectFactory().getObject() == beanFactory);


        /*
         * 获取容器内建 Bean 对象
         */
        beanFactory.getBean(Environment.class);
        System.out.println("========================");
        Map<String, User> beansOfType = context.getBeansOfType(User.class);
        System.out.println(beansOfType);

    }

    /**
     * 依赖注入集合
     * @param beanFactory
     */
    private static void injectionToCollection(BeanFactory beanFactory) {
        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");
        System.out.println(userRepository.getUsers());
    }


}
