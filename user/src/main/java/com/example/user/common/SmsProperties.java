package com.example.user.common;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * desc: 短信宝：短信验证码工具
 *
 * @author LYJ
 * @mail: achou521@163.com
 * @create 2022-06-27 17:51
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "sms")
public class SmsProperties {

    @ApiModelProperty(value = "模板")
    private String template;

    @ApiModelProperty(value = "前缀")
    private String prefix;

    @ApiModelProperty(value = "后缀")
    private String suffix;

    @ApiModelProperty(value = "签名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "urls")
    private String urls;

    /**
     * 获取模板
     *
     * @param phoneNo
     * @param msgContent
     * @return
     */
    public String request(String phoneNo, String msgContent) {
        String result=StringUtils.join(url, String.format(template, userName, md5(password), phoneNo, msgContent));
        System.out.println(result);
//        String httpUrl = "http://api.smsbao.com/sms";
//        StringBuffer httpArg = new StringBuffer();
//        httpArg.append("u=").append(userName).append("&");
//        httpArg.append("p=").append(md5(password)).append("&");
//        httpArg.append("m=").append(phoneNo).append("&");
//        httpArg.append("c=").append(encodeUrlString(msgContent, "UTF-8"));

//        String result = request(httpUrl, httpArg.toString());
//        System.out.println(result);
        return result;//StringUtils.join(url, String.format(template, userName, DigestUtils.md5Hex(password), phoneNo, msgContent));
    }

    /**
     * 获取模板
     *
     * @param phoneNo
     * @param msgContent
     * @return
     */
    public String requests(String phoneNo, String msgContent) {
        String result=StringUtils.join(urls, String.format(template, userName, md5(password), phoneNo, msgContent));
        System.out.println(result);
        return result;
    }

    public static String encodeUrlString(String str, String charset) {
        String strret = null;
        if (str == null)
            return str;
        try {
            strret = java.net.URLEncoder.encode(str, charset);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return strret;
    }

    public static String md5(String plainText) {
        StringBuffer buf = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }


}