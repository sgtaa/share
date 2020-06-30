package com.share.wxerp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.wxerp.entity.Permission;
import com.share.wxerp.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author suguotai
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 查询角色所拥有的操作方法
     * @param rid
     * @return
     */
    @Select("SELECT * from erp_permission WHERE perid = ANY (select rp.perid from role_permission rp inner join erp_role er where rp.rid = er.rid AND er.rid =#{rid})")
    @Results({
            @Result(id=true,property="perid",column="perid",javaType=String.class),
            @Result(property="name",column="name",javaType=String.class),
            @Result(property="resourceType",column="resource_Type",javaType=String.class),
            @Result(property="url",column="url",javaType=String.class),
            @Result(property="permission",column="permission",javaType=String.class),
            @Result(property="parentId",column="parent_Id",javaType=String.class),
            @Result(property="parentIds",column="parent_Ids",javaType=String.class)
    })
    List<Permission> getPermissionList(@Param("rid") String rid);

    /**
     * 查询所有的操作方法
     * @param
     * @return
     */
    @Select("SELECT permission from erp_permission")
    @Results({
            @Result(property="permission",column="permission",javaType=String.class)
    })
    List<String> getAllPermissionList();
    /**
     * 查询所有操作属性
     * @param
     * @return
     */
    @Select("SELECT permission from erp_permission")
    @Results({
            @Result(id=true,property="perid",column="perid",javaType=String.class),
            @Result(property="name",column="name",javaType=String.class),
            @Result(property="resourceType",column="resource_Type",javaType=String.class),
            @Result(property="url",column="url",javaType=String.class),
            @Result(property="permission",column="permission",javaType=String.class),
            @Result(property="parentId",column="parent_Id",javaType=String.class),
            @Result(property="parentIds",column="parent_Ids",javaType=String.class)
    })
    List<Permission> getAllPermissionsList();
}
