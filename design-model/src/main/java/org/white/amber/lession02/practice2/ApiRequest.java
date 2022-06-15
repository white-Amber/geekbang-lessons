package org.white.amber.lession02.practice2;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description
 * @Date 2022/5/14
 * @Author yuze
 */
public class ApiRequest {

    private String baseUrl;
    private String token;
    private String appId;
    private long timestamp;

    public ApiRequest(String baseUrl, String token, String appId, long timestamp) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.appId = appId;
        this.timestamp = timestamp;
    }

    /**
     * 这个静态方法又是什么意义？
     * 对URL中的请求参数进行解析
     * @param url
     * @return
     */
    public static ApiRequest buildFormUrl(String url) {
        String[] split = url.split("\\?");
        String baseUrl = split[0];
        String token = null;
        String appId = null;
        long timestamp = 0;
        String[] keyValue = split[1].split("&");
        for (String ele : keyValue) {
            String[] keyValueArray = ele.split("=");
            if (keyValueArray[0].equals("token")) {
                token = keyValueArray[1];
            } else if (keyValueArray[0].equals("appId")) {
                appId = keyValueArray[1];
            } else if (keyValueArray[0].equals("timestamp")) {
                timestamp = Long.parseLong(keyValueArray[1]);
            }
        }
        if (StringUtils.isBlank(baseUrl) || StringUtils.isBlank(token) || StringUtils.isBlank(appId)) {
            throw new RuntimeException("build ApiRequest find exception.");
        }

        return new ApiRequest(baseUrl, token, appId, timestamp);
    }

    public String getWithoutTokenUrl(String pwd) {
        return baseUrl.concat("?").concat("appId=").concat(appId).concat("&pwd=").concat(pwd).concat("&timestamp=").concat(timestamp+"");
    }

    public String getFullNewUrl() {
        return baseUrl.concat("?").concat("appId=").concat(appId).concat("&timestamp=").concat(timestamp+"").concat("&token=").concat(token);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getToken() {
        return token;
    }

    public String getAppId() {
        return appId;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
