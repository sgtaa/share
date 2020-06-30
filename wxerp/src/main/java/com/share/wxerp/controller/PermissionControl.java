package com.share.wxerp.controller;

import com.share.wxerp.config.PermissionName;
import com.share.wxerp.service.serviceImpl.PermissionServiceImpl;
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
@RequestMapping("/permission")
public class PermissionControl {
    @Autowired
    private PermissionServiceImpl permissionService;
    private static final Logger log = LoggerFactory.getLogger(PermissionControl.class);

    @RequestMapping("/add")
    public Map<String, Object> add() throws Exception {
        log.info("进入PermissionControladd将系统中所有权限表达式加载进入数据库");
        Map<String, Object> result;
        result = permissionService.add();
        log.info(":"+result.toString());
        return result;
    }


    @RequestMapping("/query")
    @RequiresPermissions("permission:query")
    @PermissionName("操作查询")
    public Map<String, Object> query(){

        log.info("进入PermissionControlquery");
        Map<String, Object> result = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name","添加");
        map.put("resourceType","button");
        map.put("url","permission/add");
        map.put("permission","permission:add");
        map.put("parentId","0/");
        map.put("parentIds","0/1");
        //result = permissionService.add(map);

        log.info(result.toString());
        return result;
    }

}
