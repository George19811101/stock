//package com.example.user.common;
//
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
///**
// * desc: ip工具类
// *
// * @author: xwx
// * @mail: 12306@qq.com
// * @create 2018-08-03 09:13
// */
//@Slf4j
//public class IpUtils {
//
//    /**
//     * 获取IP地址
//     * <p>
//     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
//     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
//     */
//    @SneakyThrows
//    public static String getIpAddress(HttpServletRequest httpServletRequest) throws UnknownHostException {
//
//        String ip = httpServletRequest.getHeader("x-forwarded-for");
//
//        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
//            ip = httpServletRequest.getHeader("Proxy-Client-IP");
//        }
//        if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = httpServletRequest.getHeader("WL-Proxy-Client-IP");
//        }
//        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
//            ip = httpServletRequest.getHeader("HTTP_CLIENT_IP");
//        }
//        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
//            ip = httpServletRequest.getHeader("HTTP_X_FORWARDED_FOR");
//        }
//        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
//            ip = httpServletRequest.getRemoteAddr();
//        }
//
//        // 使用代理，则获取第一个IP地址
//        if (StringUtils.isEmpty(ip) && ip.length() > 15) {
//
//            if (ip.indexOf(",") > 0) {
//
//                ip = ip.substring(0, ip.indexOf(","));
//
//            }
//
//        }
//
//        if (ip.equals("0:0:0:0:0:0:0:1")) {
//            ip = InetAddress.getLocalHost().getHostAddress();
//        }
//
//        return ip;
//
//    }
//
//}
