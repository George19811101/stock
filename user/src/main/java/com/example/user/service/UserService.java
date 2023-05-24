package com.example.user.service;

import com.example.user.common.Result;
import com.example.user.model.bo.*;
import com.example.user.model.po.User;
import com.example.user.model.vo.UserVo;
import org.springframework.stereotype.Service;


public interface UserService {

    Result<String> sendCaptcha(SendCaptchaBo captchaBo);

    Result<String> register(RegisterBo registerBo);

    Result<UserVo> login(UserBo userBo) throws Exception;

    Result<String> updateLoginPassword(LoginPassword loginPassword);

    Result<String> addTradePassword(TradePassword tradePassword);

    Result<String> updateTradePassword(updateTradePassword updateTradePassword);

    Result<String> sendCode( );

    User getUserById(long id);
}
