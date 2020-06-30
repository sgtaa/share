package com.share.wxerp.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.wxerp.config.PermissionName;
import com.share.wxerp.controller.PermissionControl;
import com.share.wxerp.entity.Permission;
import com.share.wxerp.mapper.PermissionMapper;
import com.share.wxerp.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

/**
 * @Author suguotai
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    //请求映射处理映射器
    //springmvc在启动时候将所有贴有请求映射标签：RequestMapper方法收集起来封装到该对象中
    @Autowired
    private RequestMappingHandlerMapping rmhm;
    private static final Logger log = LoggerFactory.getLogger(PermissionServiceImpl.class);

    public Map<String, Object> add() throws  Exception{
        log.info("进入PermissionServiceadd");
        Map<String, Object> map = new HashMap<String, Object>();
        String msg = null;
        int retu = 0;
        int i=0;
        //0：从数据库中查询出所有权限表达式，然后对比，如果已经存在了，跳过，不存在添加
        List<String> permissionsList = this.permissionMapper.getAllPermissionList();
        //1:获取controller中所有带有@RequestMapper标签的方法
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = rmhm.getHandlerMethods();
        Collection<HandlerMethod> methods = handlerMethods.values();
        for (HandlerMethod method : methods) {
            i=i+1;
            //2：遍历所有方法，判断当前方法是否贴有@RequiresPermissions权限控制标签
            RequiresPermissions anno = method.getMethodAnnotation(RequiresPermissions.class);
            if(anno != null){
                //3：如果有，解析得到权限表达式，封装成Permission对象保存到Permission表中
                //权限表达式
                 String permissions = anno.value()[0];
                 String url = permissions.replaceAll(":","/");
                 String name = method.getMethodAnnotation(PermissionName.class).value();
                 //去除重复的
                if(permissionsList.contains(permissions)) {
                     continue;
                }
                Permission permissionl = new Permission();
                String perid = UUID.randomUUID().toString();
                permissionl.setPerid(perid);
                permissionl.setName(name);
                permissionl.setResourceType("button");
                permissionl.setPermission(permissions);
                permissionl.setUrl(url);
                permissionl.setParentId("0/");
                permissionl.setParentIds("0/"+ i);

                retu = this.permissionMapper.insert(permissionl);
            }
        }
        if (retu != 0){
            msg = "添加成功！";
        }else {
            msg = "添加失败！";
        }
        map.put("msg",msg);
        return map;
    }
}
