package com.xgg.auth.oauth2.captcha;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author renchengwei
 * @Date 2019/8/5
 * @Description TODO
 */
public abstract class AbstractCaptchaProcessor<C extends CaptchaVO> implements CaptchaProcessor {

    @Resource
    protected CaptchaRepository captchaRepository;

    /**
     * 创建验证码
     * @param request 封装请求和响应
     * @throws Exception
     */
    @Override
    public void create(ServletWebRequest request) throws Exception {
        //生成
        C captcha = generateCaptcha(request);
        //保存
        save(request,captcha);
        //发送
        send(request,captcha);
    }

    protected abstract C generateCaptcha(ServletWebRequest request);

    protected abstract void send(ServletWebRequest request, C captcha) throws IOException;

    @Override
    public boolean support(CaptchaTypeEnum captchaTypeEnum) {
        return getCondition().equals(captchaTypeEnum);
    }

    @Override
    public void validate(ServletWebRequest request, CaptchaTypeEnum captchaType) throws CaptchaException {
        CaptchaVO captchaVO = captchaRepository.get(request,captchaType);
        String captchaInRequest;
        try {
            captchaInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    captchaType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new CaptchaException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(captchaInRequest)) {
            throw new CaptchaException(captchaType + "验证码的值不能为空");
        }

        if (captchaVO == null) {
            throw new CaptchaException(captchaType + "验证码不存在");
        }

        if (captchaVO.isExpried()) {
            captchaRepository.remove(request,captchaType);
            throw new CaptchaException(captchaType + "验证码已过期");
        }

        if (!StringUtils.equals(captchaVO.getCode(), captchaInRequest)) {
            throw new CaptchaException(captchaType + "验证码不匹配");
        }

        check(request, captchaVO);

        //验证成功清除缓存中的key
        captchaRepository.remove(request,captchaType);
    }

    protected abstract void save(ServletWebRequest request, C captcha);
    protected abstract void check(ServletWebRequest request, CaptchaVO captcha);
}
