package com.xgg.auth.authentication.captcha;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 验证码处理器，抽象类，实现了验证码通用功能和验证码生成、保存、发送、校验和获取的全流程内容，
 * 具体个性化实现由子类完成
 * @author renchengwei
 * @date 2019/8/5
 *
 */
public abstract class AbstractCaptchaProcessor<C extends Captcha> implements CaptchaProcessor {

    @Resource
    protected CaptchaRepository captchaRepository;

    /**
     * 创建验证码
     * @param request 封装请求和响应
     * @throws Exception
     */
    @Override
    public Captcha create(ServletWebRequest request) throws IOException {
        //生成
        C captcha = generateCaptcha(request);
        //保存
        save(captcha,request);
        //发送
        send(request,captcha);
        return captcha;
    }

    /**
     *  生成验证码对象
     * @author  renchengwei
     * @date   2019-11-17
     * @param  request spring对http请求的组装类，内部包含了HttpServletRequest和HttpServletResponse
     * @return 验证码对象
     *
     */
    protected abstract C generateCaptcha(ServletWebRequest request);

    /**
    *
    * 判断当前处理器是否有能力处理 <code>captchaTypeEnum</code>类型的验证码
    * @author  renchengwei
    * @date   2019-11-17
    * @param  captchaTypeEnum 验证码类型枚举
    * @return <code>true</code> 能够处理当前类型的验证码
    * @throws
    *
    */
    @Override
    public boolean support(CaptchaTypeEnum captchaTypeEnum) {
        return getCondition().equals(captchaTypeEnum);
    }
    /**
     *  校验验证码是否通过，通常是用户从前端传输验证码类型和验证码，判断是否和codeStore中是否一致
     * @author  renchengwei
     * @date   2019-11-17
     * @param  request spring对http请求的组装类，内部包含了HttpServletRequest和HttpServletResponse
     * @param  captchaType 验证码类型枚举
     * @return <code>true</code> 校验验证码通过
     * @throws
     *
     */
    @Override
    public void validate(ServletWebRequest request, CaptchaTypeEnum captchaType) throws CaptchaException {

        String captchaToken = getCaptchaTokenForServletRequest(request);

        Captcha captcha = captchaRepository.get(captchaToken,captchaType);
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

        if (captcha == null) {
            throw new CaptchaException(captchaType + "验证码不存在");
        }

        if (captcha.isExpried()) {
            captchaRepository.remove(captchaToken,captchaType);
            throw new CaptchaException(captchaType + "验证码已过期");
        }

        if (!StringUtils.equals(captcha.getCode(), captchaInRequest)) {
            throw new CaptchaException(captchaType + "验证码不匹配");
        }

        check(request, captcha);

        //验证成功清除缓存中的key
        captchaRepository.remove(captchaToken,captchaType);
    }

    /**
     * 从当前 <code>request</code>中获取验证码值，不同类型的验证码获取方式可能不同，具体有子类实现
     * @author  renchengwei
     * @date   2019-11-17
     * @param  request spring对http请求的组装类，内部包含了HttpServletRequest和HttpServletResponse
     * @return 验证码值
     *
     */
    protected abstract String getCaptchaTokenForServletRequest(ServletWebRequest request);
    /**
     *  保存验证码，具体保存方式由子类实现
     * @author  renchengwei
     * @date   2019-11-17
     * @param  captcha 验证码对象
     * @param  request spring对http请求的组装类，内部包含了HttpServletRequest和HttpServletResponse
     *
     */
    protected abstract void save(C captcha,ServletWebRequest request);
    /**
     * 
     * 校验验证码是否通过，子类个性化实现，validate方法会通过调用此方法完成子类的个性化校验，具体实现由子类完成
     * @author renchengwei
     * @date 2019-11-17
     * @param request spring对http请求的组装类，内部包含了HttpServletRequest和HttpServletResponse
     * @param captcha 验证码对象
     * 
     */
    protected abstract void check(ServletWebRequest request, Captcha captcha);
    /**
     * 
     * 发送验证码实现，例如短信验证码的发送功能，图片验证码的图片二进制传输功能，具体实现由子类完成
     * @author renchengwei
     * @date 2019-11-17
     * @param request spring对http请求的组装类，内部包含了HttpServletRequest和HttpServletResponse
     * @param captcha 验证码对象
     * @throws IOException 如果验证码由于IO原因发送失败
     * 
     */
    protected abstract void send(ServletWebRequest request, C captcha) throws IOException;

    /**
     *
     * 根据验证码类型和token获取验证码对象
     * @author renchengwei
     * @date 2019-11-17
     * @param captchaToken 验证码唯一标识
     * @return Captcha 验证码对象
     *
     */
    @Override
    public Captcha getCaptcha(String captchaToken, CaptchaTypeEnum captchaTypeEnum) {
        return captchaRepository.get(captchaToken,captchaTypeEnum);
    }
}
