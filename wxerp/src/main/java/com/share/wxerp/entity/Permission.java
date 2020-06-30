package com.share.wxerp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author suguotai
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/
@Data
@TableName("ERP_Permission")
public class Permission {
    @TableId(value = "perid",type = IdType.INPUT)
    private String perid;//操作ID.
    @TableId("name")
    private String name;//名称.
    @TableId("resourcetype")
    private String resourceType;//资源类型，[menu|button]
    @TableId("url")
    private String url;//资源路径.
    @TableId("permission")
    private String permission; //权限字符串,menu例子：Role:*，button例子：Role:create,Role:update,Role:delete,Role:view
    @TableId("parentId")
    private String parentId; //父编号
    @TableId("parentIds")
    private String parentIds; //父编号列表

    @Override
    public String toString() {
        return "Permission{" +
                "perid='" + perid + '\'' +
                ", name='" + name + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", url='" + url + '\'' +
                ", permission='" + permission + '\'' +
                ", parentId='" + parentId + '\'' +
                ", parentIds='" + parentIds + '\'' +
                '}';
    }

    public String getPerid() {
        return perid;
    }

    public void setPerid(String perid) {
        this.perid = perid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }
}
