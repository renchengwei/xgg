package com.xgg.auth.oauth2.properties;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: TODO
 */
public class ImageCaptchaProperties {
    /**
     * 验证码长度
     */
    private int size = 4;

    /**
     * 宽度
     */
    private int width;
    /**
     * 高度
     */
    private int height;
    /**
     * 失效秒数，默认三分钟
     */
    private int expireAfterSeconds = 3 * 60;
    /**
     * 验证码拦截的路径 多个路径以,(逗号)进行分割
     */
    private String interceptUrl;
    private String grantType;
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getExpireAfterSecondes() {
        return expireAfterSeconds;
    }

    public void setExpireAfterSecondes(int expireAfterSecondes) {
        this.expireAfterSeconds = expireAfterSecondes;
    }

    public int getExpireAfterSeconds() {
        return expireAfterSeconds;
    }

    public void setExpireAfterSeconds(int expireAfterSeconds) {
        this.expireAfterSeconds = expireAfterSeconds;
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
