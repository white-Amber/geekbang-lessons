package org.geekbang.thinking.in.spring.ioc.overview.dependency.lookup;


import lombok.Data;

/**
 * @Description
 * @Date 2022/1/21
 * @Author yuze
 */
@Data
public class User {

    private String name;

    private Integer age;

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

    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
        System.out.println("初始化：" + name);
    }

    public static User createUser() {
        User user = new User();
        user.setAge(18);
        user.setName("whiteAmber");
        return user;
    }
}
