package com.share.wxerp.controller;

import com.share.wxerp.config.PermissionName;
import com.share.wxerp.service.serviceImpl.RoleServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author suguotai
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/role")
public class RoleControl {
    @Autowired
    private RoleServiceImpl roleService;
    private static final Logger log = LoggerFactory.getLogger(RoleControl.class);

    @RequestMapping("/add")
    @RequiresPermissions("role:add")
    @PermissionName("角色添加")
    public Map<String, Object> add(){
        log.info("进入roleControladd");
        Map<String, Object> result = new HashMap<>();
        result.put("successful","角色添加");
        //result = roleService.add(map);

        log.info(":"+result.toString());
        return result;
    }

    @RequestMapping("/delete")
    @RequiresPermissions("role:delete")
    @PermissionName("角色删除")
    public Map<String, Object> delete(){
        log.info("进入roleControldelete");
        Map<String, Object> result = new HashMap<>();
        result.put("successful","角色添加");

        log.info(":"+result.toString());
        return result;
    }

    @RequestMapping("/query")
    @RequiresPermissions("role:query")
    @PermissionName("角色查询")
    public Map<String, Object> query(){
        log.info("进入roleControlquery");
        Map<String, Object> result = new HashMap<>();
        result.put("successful","角色查询");

        log.info(":"+result.toString());
        return result;
    }

    @RequestMapping("/update")
    @RequiresPermissions("role:update")
    @PermissionName("角色修改")
    public Map<String, Object> update(){

        log.info( "进入roleControlupdate");
        Map<String, Object> result = new HashMap<>();
        result.put("successful","角色修改");

        log.info(":"+ result.toString());
        return result;
    }
}
