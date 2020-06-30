package com.share.wxerp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.wxerp.entity.User;

/**
 * @Author suguotai
 * @Description //TODO $
 * @Date $ $
 * @Param $
 **/
public interface UserMapper extends BaseMapper<User> {
    /**
     * //使用注解的方式新增用户
     *     @Insert("insert into users values(null,#{user.userName},#{user.userPwd},#{user.userType})")
     *     @Options(keyProperty="user.userId",useGeneratedKeys=true)
     *     public int addUser(@Param("user")User user);
     *     //注解的方式修改用户资料---多参数传递第二种方式
     *     @Update("update users set user_name=#{name} where user_id=#{id}")
     *     public int updateUserNameById(@Param("name")String name,@Param("id")int id);
     *     //注解的方式删除用户
     *     @Delete("delete from users where user_id=#{id}")
     *     public int delById(@Param("id") int id);
     */
}
