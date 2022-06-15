package org.white.amber.lession02.practice2;

import sun.security.provider.SHA;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

/**
 * @Description 操作与token相关的业务模型
 * @Date 2022/5/14
 * @Author yuze
 */
public class AuthToken {

    /**
     * 默认的失效时间
     */
    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 1 * 60 * 1000;

    private String token;

    private long createTime;

    private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;

    public AuthToken(String token, long createTime) {
        this.token = token;
        this.createTime = createTime;
    }

    public AuthToken(String token, long createTime, long expiredTimeInterval) {
        this.token = token;
        this.createTime = createTime;
        this.expiredTimeInterval = expiredTimeInterval;
    }

    /**
     * 这个方法的意义何在？如果使用构造函数创建AuthToken那里面的成员方法还能正常使用吗？
     * 生成token
     * @return
     */
    public static AuthToken generate(ApiRequest apiRequest, String pwd) {
        String withoutTokenUrl = apiRequest.getWithoutTokenUrl(pwd);
        byte[] encode = Base64.getEncoder().encode(withoutTokenUrl.getBytes(StandardCharsets.UTF_8));
        String token = new String(encode);

        return new AuthToken(token, apiRequest.getTimestamp(), DEFAULT_EXPIRED_TIME_INTERVAL);
    }

    public String getToken() {
        return this.token;
    }

    /**
     * 如何判断过期时间？
     * @return
     */
    public boolean isExpired() {
        return createTime < this.expiredTimeInterval;
    }

    public boolean match(AuthToken authToken) {
        return this.token.equals(authToken.getToken());
    }

}
