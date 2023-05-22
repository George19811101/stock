package com.example.user.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.NotLoginException;

import cn.dev33.satoken.filter.SaServletFilter;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.strategy.SaStrategy;
import cn.dev33.satoken.util.SaFoxUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * desc: Sa-Token全局过滤器
 *
 * @author: xwx
 * @mail: 12306@qq.com
 * @create 2021-03-03 15:53
 */
@Component
@Slf4j
public class SaTokenConfig   implements WebMvcConfigurer {

    @Value("${sa-token.pattern}")
    public String pattern;

    @Value("${sa-token.exclude-url}")
    private String[] excludeArray;

    /**
     * 重写 Sa-Token
     */
    @Autowired
    public void rewriteToken() {
        SaStrategy.me.setCreateToken((loginId, loginType) -> "token_" + loginId);
    }

    @Autowired
    public void rewriteSaStrategy() {
        // 重写 Token 生成策略
        SaStrategy.me.createToken = (loginId, loginType) -> {
            return SaFoxUtil.getRandomString(15);    // 随机60位长度字符串
        };
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludeList = Arrays.asList(excludeArray);
        // 注册 Sa-Token 拦截器，定义详细认证规则
        registry.addInterceptor(new SaInterceptor(handler -> {
            // 指定一条 match 规则
            SaRouter
                    .match("/**")    // 拦截的 path 列表，可以写多个 */
                    .notMatch(excludeList)        // 排除掉的 path 列表，可以写多个
                    .check(r -> StpUtil.checkLogin());        // 要执行的校验动作，可以写完整的 lambda 表达式


        })).addPathPatterns("/**");
    }
    /**
     * 注册 [Sa-Token全局过滤器]
     */
//    @Bean
//    public SaServletFilter getSaServletFilter() {
//        List<String> excludeList = Arrays.asList(excludeArray);
//        log.info(excludeList.toString());
//        return new SaServletFilter()
//                .addInclude(pattern)
//                .setExcludeList(excludeList)
//                .setAuth(obj -> SaRouter.match(pattern).notMatch(excludeList).check(StpUtil::checkLogin))
//                .setError(e -> {
//
//                    NotLoginException notLoginException = (NotLoginException) e;
//                    SaHolder.getResponse().setHeader("Content-Type", "application/json;charset=UTF-8");
//
//                    return JSONUtil.toJsonStr(SaResult.code(Integer.parseInt(notLoginException.getType()))
//                            .setMsg(notLoginException.getMessage()));
//
//                });
//
//    }

}
