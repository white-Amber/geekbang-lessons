package org.geekbang.thinking.in.spring.denpendcy.exception.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description BeanDefinitionStoreException 异常示例
 * @Date 2022/5/9
 * @Author yuze
 */
public class BeanDefinitionStoreExceptionDemo {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:xxx.xml");
        applicationContext.getApplicationName();


    }
}
