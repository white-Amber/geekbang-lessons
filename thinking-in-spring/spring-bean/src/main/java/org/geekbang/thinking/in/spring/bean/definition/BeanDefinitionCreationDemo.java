package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.lookup.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @Description
 * @Date 2022/4/4
 * @Author yuze
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {

        // 1. 通过 BeanDefinitionBuilder 构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 通过属性设置
        beanDefinitionBuilder
                .addPropertyValue("age", 18)
                .addPropertyValue("name", "yuze");
        // 获取 BeanDefinition 实例（这里的BeanDefinition并非 Bean 的最终形态，可以自定义修改）。
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        // 2. 通过 AbstractBeanDefinition 以及派生类。
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues
                .add("age", 18)
                .add("name", "yuze");
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);

    }


}
