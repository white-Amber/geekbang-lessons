package org.geekbang.thinking.in.spring.denpendcy.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Map;

/**
 * @Description
 * @Date 2022/4/21
 * @Author yuze
 */
public class ObjectProviderDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);
        applicationContext.refresh();

        lookupByObjectProvider(applicationContext);

        applicationContext.close();


    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {

        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());

        Map<String, String> beansOfType = applicationContext.getBeansOfType(String.class);
        System.out.println(beansOfType);

    }

    @Bean("hello")
    public String helloWorld() {
        return "hello, world";
    }

    @Bean("hello")
    public String world() {
        return "world";
    }


}
