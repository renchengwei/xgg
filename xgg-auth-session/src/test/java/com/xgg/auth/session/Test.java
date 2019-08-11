package com.xgg.auth.session;

import org.springframework.http.HttpMethod;

/**
 * @Author: renchengwei
 * @Date: 2019-08-11
 * @Description: TODO
 */
public class Test {
    @org.junit.Test
    public void test() {
        System.out.println(HttpMethod.GET.matches("GET"));
    }
}
