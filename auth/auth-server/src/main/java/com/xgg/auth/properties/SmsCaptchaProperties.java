package com.xgg.auth.properties;

/**
 * 短信验证码配置文件
 * @author renchengwei
 * @date 2019-08-04
 */
public class SmsCaptchaProperties {
    /**
     * 长度
     */
    private int length = 6;
    /**
     * 过期秒数 默认3分钟
     */
    private int expireSeconds = 180;

    private String interceptUrl;

    private String grantType;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public String getInterceptUrl() {
        return interceptUrl;
    }

    public void setInterceptUrl(String interceptUrl) {
        this.interceptUrl = interceptUrl;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}
