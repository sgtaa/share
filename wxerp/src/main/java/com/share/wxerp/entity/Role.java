package com.share.wxerp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("ERP_role")
public class Role {
    @TableId(value = "rid",type = IdType.INPUT)
    private String rid; // 角色ID
    @TableField("role")
    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    @TableField("description")
    private String description; // 角色描述,UI界面显示使用

    @Override
    public String toString() {
        return "Role{" +
                "rid='" + rid + '\'' +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
