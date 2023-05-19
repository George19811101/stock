package com.example.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.user.model.po.User;
import com.example.user.model.vo.UserVo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public   interface UserMapper extends BaseMapper<User>  {

    User getUserByInvitationCode(@Param("invitationCode") String invitationCode);

    UserVo loginByPhoneNo(@Param("phoneNo") String phoneNo, @Param("areaCode") String areaCode);


    int saveUserByEntity( User user);

    int updateByUserId(User userVo);
}
