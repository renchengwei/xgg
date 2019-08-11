package com.xgg.auth.demo.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: renchengwei
 * @Date: 2019-08-11
 * @Description: TODO
 */
@Data
public class ResponseVO<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    public ResponseVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
