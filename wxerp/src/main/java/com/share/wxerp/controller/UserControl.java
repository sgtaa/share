package com.share.wxerp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.share.wxerp.common.GlobalResult;
import com.share.wxerp.common.RASCoder;
import com.share.wxerp.config.MyShiroRealm;
import com.share.wxerp.config.PermissionName;

import com.share.wxerp.service.serviceImpl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/user")
public class UserControl {
    @Autowired
    private UserServiceImpl userService;
    private static final Logger log = LoggerFactory.getLogger(UserControl.class);

    @RequestMapping(value = "/loginAuth", method = RequestMethod.POST)
    public GlobalResult loginAuth(@RequestParam(value = "detail", required = false) String detail) throws Exception{
        log.info("进入UserControlalogin");
        String respmsg = "服务器异常";
        String msg = null;
        JSONObject detailJson = JSON.parseObject(detail);
        String username = detailJson.getString("username");
        String password = detailJson.getString("password");
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password );
        try {
            subject.login(token);
            Map<String, Object> resultmap = userService.query(username,password);
            respmsg = "登录成功！";
            result = GlobalResult.success(respmsg,resultmap);
        }catch (UnknownAccountException e) {
            msg = "UnknownAccountException -- > 账号不存在：";
            respmsg = "输入的账号或密码不正确！";
            result = GlobalResult.errorRealm(respmsg);
        }catch (IncorrectCredentialsException e){
            msg = "IncorrectCredentialsException -- > 密码不正确：";
            respmsg = "输入的账号或密码不正确！";
            result = GlobalResult.errorRealm(respmsg);
        }catch (Exception e) {
            e.printStackTrace();
            respmsg = "内部出错";
            result = GlobalResult.errorRealm(respmsg);
        }
        //此方法不处理登陆成功（认证成功），shiro认证成功会自动跳转到上一个请求路径
        log.info(":"+result.toString()+msg);
        return result;
    }

    @RequestMapping("/add")
    @RequiresPermissions("user:add")
    @PermissionName("用户添加")
    public Map<String, Object> add(){
        log.info("进入UserControladd");
        Map<String, Object> result;
        Map<String, Object> map = new HashMap<>();
        map.put("username","student");
        map.put("name","学员");
        map.put("password","888");
        result = userService.add(map);
        log.info(":"+result.toString());
        return result;
    }

    @RequestMapping("/delete")
    @RequiresPermissions("user:delete")
    @PermissionName("用户删除")
    public Map<String, Object> delete(){
        log.info("进入UserControldelete");
        Map<String, Object> map = new HashMap<>();

        map.put("successful","删除成功");
        log.info(":"+map.toString());
        return map;
    }

    @RequestMapping("/query")
    @PermissionName("用户查询")
    @RequiresPermissions("user:query")
    public Map<String, Object> query(){
        log.info("进入UserControlquery");
        Map<String, Object> map = new HashMap<>();

        map.put("successful","查询成功");
        log.info(":"+map.toString());
        return map;
    }

    @RequestMapping("/updated")
    @RequiresPermissions("user:updated")
    @PermissionName("用户修改")
    public Map<String, Object> update(){
        log.info("进入UserControlupdate");
        Map<String, Object> map = new HashMap<>();

        map.put("successful","更新成功");
        log.info(":"+map.toString());
        return map;
    }

    @RequestMapping("/clearCacheAuth")
    public Map<String, Object> clearCacheAuth(){
        log.info("进入clearCacheAuth");
        Map<String, Object> map = new HashMap<>();
        RealmSecurityManager rsm = (RealmSecurityManager)SecurityUtils.getSecurityManager();
        MyShiroRealm realm = (MyShiroRealm) rsm.getRealms().iterator().next();
        realm.clearAuthz();
        map.put("clearCacheAuth","清理权限缓存成功");
        return map;
    }

    @RequestMapping("/403")
    public Map<String, Object> Unauthorized(){
        log.info("进入Unauthorized");
        Map<String, Object> map = new HashMap<>();
        map.put("Unauthorized","该用户未授权");
        return map;
    }

    @RequestMapping("/login")
    public Map<String, Object> login(@RequestParam(value = "detail", required = false) String detail){
        log.info("进入login");
        Map<String, Object> map = new HashMap<>();
        //String privateKey= "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIu2WnHWVNzKRGgiknFfX0co9qbJAp7dF8829U9nAS2InlVFlhKs0BvYnV6f+ZQALJVn4LNgEIrA/rLYsjAxvJiUUQZYz0ar0SlP2I1CEUM+mMae2xRo1M6uMJCXp0IvTlp5lE4lN8ZOzqSlIs/N5YkbndxIcjAcTF9gz4k2xpL/AgMBAAECgYAVZ835rQX5gf/zGIDpX9xgMbWAnkGJh4RTWi9BiB2VFV504CF7IQENkyWhTs9VTqWGCpFRykEekS00x6CdY7FqUHHMLwjbrhZVDHzeWuVdof9yDP2jMW4WYp4fWHMXJCLP837UKHAvH/LJhijzhFIZ1j6ZJaf17N1W6UvmPI5uEQJBAN4/ebqXroLCdvgwi300CTGW9tWavi5ZJWXJEFyNqwxUkXJU8PWf2OIIZmTcW9beF6QMbZZq3HnLxTuY1t1AZ5cCQQCg7hGu49/hajJIg5p3pUAvmjjrF30H5JRvX7nI+JwTtIMJEDTLVikHqV7I3ijFmmUk2YYK9D3DnT0/yOZMP9zZAkEAx8VsyQGg9p6QS+Vu7NsCTJXzR+vxeT3edNC3qUoab1nw50Fc2pyVWg/M4mqFDu3ReD9HnkZt6fj1T+3BbV1//wJAH7zz9dbxJ2anRg/WXfRURU8YHJ1ERlM5RwHNBSqqtWQrV5U/pD541w77J4hQ6C+79NmFixGCQNr44mR/rtgFeQJAS2IQNleG8C2PzJdWQv5fatlxviST5E//6tl5B7kcc7SFOPUt/Ps20hwZsn2oQiY2OcXFB9YtXB+9Puta4oDzHw==";
        //String data= null;
        String respmsg = "服务器异常";
        String respcode = "500";
        /*String msg = null;
        try {
            System.out.println("detail:"+detail.toString());
            data = new String(rasCoder.decryptByPrivateKey(detail,privateKey));
            System.out.println("detailJson:"+data.toString());
            //JSONObject detailJson = JSON.parseObject(data);
            //System.out.println("detailJson:"+detailJson.toString());
            *//*String username = detailJson.getString("username");
            String password = detailJson.getString("password");
            System.out.println("username:"+username+"==password:"+password);*//*
            respmsg = "解密成功";
            respcode = "200";
        } catch (Exception e) {
            e.printStackTrace();
            respmsg = "解密出错";
            respcode = "400";
            map.put("respmsg",respmsg);
            map.put("respcode",respcode);
            return map;
        }*/
        map.put("respmsg",respmsg);
        map.put("respcode",respcode);
        map.put("msg","用户登录");
        return map;
    }

    @RequestMapping("/index")
    public Map<String, Object> index(){
        log.info("进入index");
        Map<String, Object> map = new HashMap<>();
        map.put("msg","用户登录成功进入首页");
        return map;
    }

    @RequestMapping("/logout")
    public Map<String, Object> logout(){
        log.info("进入logout");
        Map<String, Object> map = new HashMap<>();
        map.put("msg","退出登录");
        return map;
    }
}
