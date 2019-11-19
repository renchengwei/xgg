package com.xgg.auth.oauth2.support.strategy;

/**
 * 策略接口
 * @author renchengwei
 * @date 2019-08-11
 */
public interface IStrategy<C> {
    /**
     * 获得策略条件
     *
     * @param
     * @return 用来注册的策略处理条件
     */
    C getCondition();
}
