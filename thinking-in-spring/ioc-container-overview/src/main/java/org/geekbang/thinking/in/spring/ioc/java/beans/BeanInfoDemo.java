package org.geekbang.thinking.in.spring.ioc.java.beans;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Objects;

/**
 * @Description
 * @Date 2022/3/19
 * @Author yuze
 */
public class BeanInfoDemo {

    public static void main(String[] args) {

        try {
            Person person = new Person();
            person.setName("zhangSan");
            person.setAge(20);
            BeanInfo beanInfo = Introspector.getBeanInfo(person.getClass());
            BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            System.out.println(beanDescriptor.getBeanClass());
            System.out.println(beanDescriptor.getName());
            System.out.println(beanDescriptor.getDisplayName());
            System.out.println("===================");
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                System.out.println("属性类型：" + propertyDescriptor.getPropertyType());
                System.out.println("属性名：" + propertyDescriptor.getName());
//                propertyDescriptor.setValue("name", "liSi");
                Method writeMethod = propertyDescriptor.getWriteMethod();
                if (propertyDescriptor.getName().equals("name")) {
                    writeMethod.invoke(person, "liSi");
                }
                if (Objects.nonNull(writeMethod)) {
                    System.out.println("方法名：" + writeMethod.getName());
                    System.out.println("方法返回类型：" + writeMethod.getReturnType());
                    Parameter[] parameters = writeMethod.getParameters();
                    System.out.println("方法参数类型：" + Arrays.toString(writeMethod.getParameterTypes()));
                    System.out.println("方法参数数量：" + writeMethod.getParameterCount());
                    for (Parameter parameter : parameters) {
                        System.out.println("方法参数名：" + parameter.getName());

                    }
                }
//                break;
            }

            System.out.println(person);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
