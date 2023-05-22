//package com.example.user.config;
//
//
//
//import cn.dev33.satoken.context.SaHolder;
//import cn.dev33.satoken.filter.SaServletFilter;
//import cn.dev33.satoken.interceptor.SaRouteInterceptor;
//import cn.dev33.satoken.router.SaHttpMethod;
//import cn.dev33.satoken.router.SaRouter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//public class SaTokenConfigure implements WebMvcConfigurer {
//    // 注册拦截器，文档 https://sa-token.dev33.cn/doc/index.html#/use/route-check
//
//    @Value("${sa-token.pattern}")
//    public String pattern;
////
//    @Value("${sa-token.exclude-url}")
//    private String[] excludeArray;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册Sa-Token的路由拦截器
//        registry.addInterceptor(new SaRouteInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns(
//                        "/swagger-resources/**",
//                        "/swagger-ui.html",
//                        "/v2/api-docs",
//                        "/webjars/**",
//                        "/",
//                        "/cover",
//                        "/login"
//                );
//    }
//
//    /**
//     * 注册 [Sa-Token全局过滤器]
//     */
////    @Bean
//    public SaServletFilter getSaServletFilter() {
//        List<String> excludeList = Arrays.asList(excludeArray);
//        return new SaServletFilter()
//                // 拦截与排除 path
//                .addInclude("/**").setExcludeList(excludeList)
//
//                // 全局认证函数
//                .setAuth(obj -> {
//                    // ...
//                })
//
//                // 异常处理函数
//                .setError(e -> {
//                    return e.getMessage();
//                })
//
//                // 前置函数：在每次认证函数之前执行
//                .setBeforeAuth(obj -> {
//                    // ---------- 设置跨域响应头 ----------
//                    SaHolder.getResponse()
//                            // 允许指定域访问跨域资源
//                            .setHeader("Access-Control-Allow-Origin", "*")
//                            // 允许所有请求方式
//                            .setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
//                            // 有效时间
//                            .setHeader("Access-Control-Max-Age", "3600")
//                            // 允许的header参数
//                            .setHeader("Access-Control-Allow-Headers", "*");
//
//                    // 如果是预检请求，则立即返回到前端
//                    SaRouter.match(SaHttpMethod.OPTIONS)
//                            .free(r -> System.out.println("--------OPTIONS预检请求，不做处理"))
//                            .back();
//                })
//                ;
//    }
//
//}