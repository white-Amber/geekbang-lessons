package cn.yz.future.service;

import cn.yz.future.pojo.MedalInfo;

/**
 * @Description
 * @Date 2023/4/18
 * @Author yuze
 */
public class MedalService {

    public MedalInfo getMedalInfo(Long userId) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new MedalInfo("111", "守护勋章");
    }


}
