package org.geekbang.thinking.in.spring.denpendcy.exception.demo;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description BeanInstantiationException 异常演示
 * @Date 2022/5/9
 * @Author yuze
 */
public class BeanInstantiationExceptionDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
        applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());

        applicationContext.refresh();

        applicationContext.close();



    }
}
