package org.geekbang.thinking.in.spring.ioc.overview.container;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @Description
 * @Date 2022/4/2
 * @Author yuze
 */
public class IoCContainerDemo {

    public static void main(String[] args) {

        /*
         * 思想：
         * 首先创建一个DefaultListableBeanFactory，因为DefaultListableBeanFactory是BeanDefinitionRegistry提供了注册
         * bean的方法，所以XmlBeanDefinitionReader可以在loadBeanDefinitions中直接调用相对应的注册bean方法，来实现bean
         * 的注册。
         *
         * 所以XmlBeanDefinitionReader作用：解析xml，并将xml中配置的bean加载到beanFactory。
         */
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        int beanDefinitionCount = xmlBeanDefinitionReader.loadBeanDefinitions(location);
        System.out.println(beanDefinitionCount);

    }



}
