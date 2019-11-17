package com.xgg.auth.session.support.strategy;

/**
 * @author renchengwei
 * @date 2019-08-11
 * : TODO
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
