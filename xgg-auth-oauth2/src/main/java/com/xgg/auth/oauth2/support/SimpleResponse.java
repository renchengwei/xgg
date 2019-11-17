package com.xgg.auth.oauth2.support;

/**
 * @author renchengwei
 * @date 2019-08-11
 * : TODO
 */
public class SimpleResponse {

    public SimpleResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public SimpleResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public SimpleResponse(String message) {
        this.message = message;
    }

    private int code;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
