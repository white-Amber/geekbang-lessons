package org.geekbang.thinking.in.spring.denpendcy.exception.demo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @Description BeanCreationException 异常示例
 * @Date 2022/5/9
 * @Author yuze
 */
public class BeanCreationExceptionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Pojo.class);
        applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());

        applicationContext.refresh();

        applicationContext.close();
    }

    static class Pojo implements InitializingBean {

        @PostConstruct
        public void init() throws Throwable {
            throw new Throwable("init() : For purpose...");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet : For purpose...");
        }
    }

}
