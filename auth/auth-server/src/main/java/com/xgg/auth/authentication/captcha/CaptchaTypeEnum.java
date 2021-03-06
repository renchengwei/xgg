package com.xgg.auth.authentication.captcha;

import com.xgg.auth.authentication.SecurityConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * 验证码类型枚举
 * @author renchengwei
 * @date 2019/8/5
 */
public enum CaptchaTypeEnum {

    SMS("sms","短信验证码") {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },
    IMAGE("image","图形验证码") {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    CaptchaTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static Map<String,CaptchaTypeEnum> codeLookup = new HashMap<String,CaptchaTypeEnum>();
    private String code;
    private String desc;

    static {
        for (CaptchaTypeEnum type : CaptchaTypeEnum.values()) {
            codeLookup.put(type.code, type);
        }
    }

    /**
     * 根据类型获取枚举类
     * @param code
     * @return
     */
    public static CaptchaTypeEnum forCode(String code)  {
        return codeLookup.get(code);
    }

    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }
    public abstract String getParamNameOnValidate();

}
