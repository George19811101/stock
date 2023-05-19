//package com.example.user.config;
//
//import cn.dev33.satoken.context.SaHolder;
//import cn.dev33.satoken.exception.NotLoginException;
//import cn.dev33.satoken.filter.SaServletFilter;
//import cn.dev33.satoken.router.SaRouter;
//import cn.dev33.satoken.stp.StpUtil;
//import cn.dev33.satoken.strategy.SaStrategy;
//import cn.dev33.satoken.util.SaResult;
//import cn.hutool.json.JSONUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.List;
//
///**
// * desc: Sa-Token全局过滤器
// *
// * @author: xwx
// * @mail: 12306@qq.com
// * @create 2021-03-03 15:53
// */
//@Component
//public class SaTokenConfig {
//
//    @Value("${sa-token.pattern}")
//    public String pattern;
//
//    @Value("${sa-token.exclude-url}")
//    private String[] excludeArray;
//
//    /**
//     * 重写 Sa-Token
//     */
//    @Autowired
//    public void rewriteToken() {
//        SaStrategy.me.setCreateToken((loginId, loginType) -> "token_" + loginId);
//    }
//
//    /**
//     * Sa-Token全局过滤器
//     *
//     * @return
//     */
//    @Bean
//    public SaServletFilter getSaServletFilter() {
//
//        List<String> excludeList = Arrays.asList(excludeArray);
//
//        return new SaServletFilter()
//                .addInclude(pattern)
//                .setExcludeList(excludeList)
//                .setAuth(obj -> SaRouter.match(pattern).notMatch(excludeList).check(StpUtil::checkLogin))
//                .setError(e -> {
//
//                    System.out.println(e.getMessage());
//                    NotLoginException notLoginException = (NotLoginException) e;
//                    SaHolder.getResponse().setHeader( "Content-Type","application/json;charset=UTF-8");
//
//                    return JSONUtil.toJsonStr(SaResult.code(Integer.parseInt(notLoginException.getType()))
//                            .setMsg(notLoginException.getMessage()));
//
//                });
//
//    }
//
//}
