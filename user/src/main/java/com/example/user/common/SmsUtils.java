package com.example.user.common;


import cn.hutool.http.HttpUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;

/**
 * desc: 短信宝：短信验证码工具
 *
 * @author LYJ
 * @mail: achou521@163.com
 * @create 2022-06-27 17:51
 */
@Slf4j
@Component
public class SmsUtils {

    @Autowired
    private SmsProperties smsProperties;

    /**
     * 国内
     */
    @SneakyThrows
    public String sendSms(String phoneNo, String verificationCode) {

        String msgContent = StringUtils.join(verificationCode);

        String result = HttpUtil.get(smsProperties.request(phoneNo, msgContent));

        if (result.equals("51")) {
            throw new MyRuntimeException(ExceptionEnum.SMS_VERIFICATION_CODE_ERROR);
        }

        return msgContent;
    }

    /**
     * 国际短信
     */
    @SneakyThrows
    public String sendSmss(String phoneNo, String verificationCode) {

        String msgContent = StringUtils.join(verificationCode);

        String result = HttpUtil.get(smsProperties.requests(URLEncoder.encode("+" + phoneNo), msgContent));

        if (result.equals("51")) {
            throw new MyRuntimeException(ExceptionEnum.SMS_VERIFICATION_CODE_ERROR);
        }

        return msgContent;

    }

    public static void main(String[] args) {
        SmsUtils smsUtils=new SmsUtils();
       String result= smsUtils.sendSms("855715653216","小子张，你太大胆了些吧，现在还没有联系我，你知道我是谁吗");
        System.out.println(result);
    }

}

