package com.store.wxshare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.store.wxshare.entity.Users;

import java.util.List;

/**
 * @Author suguotai
 * @Description //TODO $
 * @Date $ $
 * @Param $
 **/
public interface UsersMapper extends BaseMapper<Users> {
    //@Select("SELECT u.ENAME,u.NICK_NAME,u.MOBILE,u.DETAILED_ADDRESS,u.EMAIL,i.GRADE from share_user u,share_integral i WHERE u.USERID = i.USERID AND u.USERID =#{userid}")
    List<Users> selectuserIntegralById(String userid);
}
