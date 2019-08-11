package com.xgg.auth.session.captcha;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: renchengwei
 * @Date: 2019-08-03
 * @Description: 验证码控制器
 */
@RestController
@Slf4j
public class CaptchaController {
    @Resource
    private CaptchaCreateService captchaCreateService;
    /**
     * 获取图片验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/captcha/{type}")
    public  void createKaptcha(HttpServletRequest request, HttpServletResponse response, @PathVariable("type") String type) throws IOException {
        captchaCreateService.createCaptcha(new ServletWebRequest(request, response), type);
    }
}
