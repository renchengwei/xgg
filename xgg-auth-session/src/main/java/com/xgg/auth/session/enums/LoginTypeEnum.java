package com.xgg.auth.session.enums;

import lombok.Getter;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: TODO
 */
@Getter
public enum LoginTypeEnum implements CharSequence {
    /**
     * json数据返回
     */
    JSON,
    /**
     * 重定向
     */
    REDIRECT;

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }
}
