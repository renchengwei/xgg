package com.xgg.auth.session.properties;

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
    private String interceptImageUrl;

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

    public String getInterceptImageUrl() {
        return interceptImageUrl;
    }

    public void setInterceptImageUrl(String interceptImageUrl) {
        this.interceptImageUrl = interceptImageUrl;
    }
}
