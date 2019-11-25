package com.xgg.auth.authentication.captcha;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 验证码处理器持有者，通过此类可以获取验证码处理器，从而调用验证码功能的各个方法
 * @author renchengwei
 * @date 2019/8/6
 */
@Component
@Slf4j
public class CaptchaProcessorHolder {

    @Resource
    private List<CaptchaProcessor> captchaProcessors;

    /**
     * 获取CaptchaProcessor接口实现类
     *
     * @param name
     * @return
     */
    public CaptchaProcessor findCaptchaProcessor(String name) {
        CaptchaTypeEnum captchaTypeEnum = CaptchaTypeEnum.forCode(name);
        if (captchaTypeEnum == null) {
            log.error("验证码类型枚举" + name + "不存在");
            throw new CaptchaException("验证码类型枚举类" + name + "不存在");
        }
        return findCaptchaProcessor(captchaTypeEnum);
    }

    /**
     * 获取CaptchaProcessor 接口实现类
     *
     * @param captchaTypeEnum
     * @return
     */
    public CaptchaProcessor findCaptchaProcessor(CaptchaTypeEnum captchaTypeEnum) {
        if (captchaTypeEnum == null) {
            throw new CaptchaException("验证码类型枚举类不存在");
        }

        CaptchaProcessor captchaProcessor = null;
        for(CaptchaProcessor processor : captchaProcessors) {
            if(processor.support(captchaTypeEnum)) {
                captchaProcessor = processor;
                break;
            }
        }
        if (captchaProcessor == null) {
            log.error("{}处理器不存在", captchaTypeEnum.getDesc());
            throw new CaptchaException(captchaTypeEnum.getDesc() + "处理器不存在");
        }
        log.info("{}处理器获取", captchaTypeEnum.getDesc());

        return captchaProcessor;
    }
}
