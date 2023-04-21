package cn.yz.future.service;

import cn.yz.future.pojo.UserInfo;

/**
 * @Description
 * @Date 2023/4/18
 * @Author yuze
 */
public class UserInfoService {

    public UserInfo getUserInfo(Long userId) {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new UserInfo("666", "zhangSan", 18);
    }

}
