package com.example.user.annotation;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.user.common.ExceptionEnum;
import com.example.user.common.Result;
import com.example.user.model.po.User;
import com.example.user.service.UserService;
import com.example.user.util.JwtTokenUtils;
import com.github.pagehelper.util.StringUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class UserLoginTokenAspect {
    @Autowired
    private UserService userService;

    @SneakyThrows
    @Around("@annotation(com.example.user.annotation.UserLoginToken)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if (Objects.isNull(requestAttributes)) {
            return requestAttributes;
        }
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)requestAttributes;

        //获取到Request对象
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader("token");
        if (ObjectUtil.isEmpty(token)) {
            return Result.exception(ExceptionEnum.NO_TOKEN);

        }

        long id= JwtTokenUtils.getAppUID(token)  ;
        User userInfoParam = userService.getUserById(id);
        if(!ObjectUtils.isEmpty(userInfoParam)){
            return proceedingJoinPoint.proceed();
        }else{
            return Result.exception(ExceptionEnum.TOKEN_ERROR);
        }
    }
}
