package org.geekbang.thinking.in.spring.bean.factory;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.lookup.User;

/**
 * @Description
 * @Date 2022/4/4
 * @Author yuze
 */
public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }
}
