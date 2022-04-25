package org.geekbang.thinking.in.spring.denpendcy.lookup;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.lookup.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @Description
 * @Date 2022/4/22
 * @Author yuze
 */
@Configuration
public class LazyDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyDemo.class);

        applicationContext.refresh();
        System.out.println(applicationContext);

        applicationContext.close();

    }

    @Bean
    @Lazy
    public User userOne() {
        return new User("zhangSan", 22);
    }

    @Bean
    public User userTwo() {
        return new User("liSi", 11);
    }



}
