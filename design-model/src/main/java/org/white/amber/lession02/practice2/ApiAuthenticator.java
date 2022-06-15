package org.white.amber.lession02.practice2;

/**
 * @Description Api认证器，对外暴露鉴权接口
 * @Date 2022/5/19
 * @Author yuze
 */
public interface ApiAuthenticator {

    void auth(String url);

    void auth(ApiRequest apiRequest);
}
