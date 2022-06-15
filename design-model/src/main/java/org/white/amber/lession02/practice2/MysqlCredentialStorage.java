package org.white.amber.lession02.practice2;

/**
 * @Description Mysql存储
 * @Date 2022/5/19
 * @Author yuze
 */
public class MysqlCredentialStorage implements CredentialStorage{
    @Override
    public String getPwdByAppId(String appId) {
        return "123456";
    }
}
