package org.geekbang.thinking.in.spring.bean.factory;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description
 * @Date 2022/4/5
 * @Author yuze
 */
@Component
public class RoleService {

    @Resource
    private UserFactory userFactory;

}
