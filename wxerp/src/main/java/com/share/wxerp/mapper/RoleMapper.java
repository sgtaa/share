package com.share.wxerp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.share.wxerp.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.sql.Wrapper;
import java.util.List;

/**
 * @Author suguotai
 * @Description //TODO $
 * @Date $ $
 * @Param $
 **/
public interface RoleMapper extends BaseMapper<Role> {
    /**
     *  @Select("select * from users where user_id in (select user_id from user_course where course_id=#{courseId})")
     *     @Results({
     *         @Result(id=true,property="userId",column="user_id",javaType=Integer.class),
     *         @Result(property="userName",column="user_name",javaType=String.class),
     *         @Result(property="userPwd",column="user_pwd",javaType=String.class),
     *         @Result(property="userType",column="user_type",javaType=Integer.class)
     *     })
     *     public User findUserByCourse(@Param("id")int courseId);
     */
    /**
     * 查询用户所拥有的角色
     * @param uid
     * @return
     */
    @Select("SELECT * from erp_role er inner join user_role ur where er.rid = ur.rid AND ur.uid = #{uid}")
    @Results({
            @Result(id=true,property="rid",column="rid",javaType=String.class),
            @Result(property="role",column="role",javaType=String.class),
            @Result(property="description",column="description",javaType=String.class)
    })
    List<Role> getRoleList(@Param("uid") String uid);

    @Select("SELECT * from erp_role")
    @Results({
            @Result(id=true,property="rid",column="rid",javaType=String.class),
            @Result(property="role",column="role",javaType=String.class),
            @Result(property="description",column="description",javaType=String.class)
    })
    List<Role> getAllRole();
}
