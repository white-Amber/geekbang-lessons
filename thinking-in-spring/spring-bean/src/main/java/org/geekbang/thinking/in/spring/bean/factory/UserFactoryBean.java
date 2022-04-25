package org.geekbang.thinking.in.spring.bean.factory;

import org.geekbang.thinking.in.spring.ioc.overview.dependency.lookup.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Description
 * @Date 2022/4/5
 * @Author yuze
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
