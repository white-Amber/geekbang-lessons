package org.geekbang.thinking.in.spring.ioc.java.beans;

import java.beans.BeanInfo;
import java.beans.beancontext.BeanContext;

/**
 * @Description
 * @Date 2022/3/19
 * @Author yuze
 */
public class Person {

    private String name;
    private Integer age;
    private boolean gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
