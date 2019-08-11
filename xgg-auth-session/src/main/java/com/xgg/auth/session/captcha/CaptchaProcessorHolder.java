package com.xgg.auth.session.captcha;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author renchengwei
 * @Date 2019/8/6
 * @Description TODO
 */
@Component
@Slf4j
public class CaptchaProcessorHolder {

    @Resource()
    private List<CaptchaProcessor> captchaProcessors;

    /**
     * 获取CaptchaProcessor接口实现类
     *
     * @param name
     * @return
     */
    CaptchaProcessor findCaptchaProcessor(String name) {
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
    CaptchaProcessor findCaptchaProcessor(CaptchaTypeEnum captchaTypeEnum) {
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
