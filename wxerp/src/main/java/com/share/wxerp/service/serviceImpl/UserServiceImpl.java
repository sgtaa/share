package com.share.wxerp.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.wxerp.entity.User;
import com.share.wxerp.mapper.UserMapper;
import com.share.wxerp.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author suguotai
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    private String algorithmName = "md5";
    private int hashIterations = 2;

    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    public Map<String, Object> add(Map<String, Object> detail){
        log.info("进入userServiceadd:"+ detail);
        Map<String, Object> map = new HashMap<String, Object>();
        String msg;
        String username = (String) detail.get("username");
        String name = (String) detail.get("name");
        String password = (String) detail.get("password");
        User user;
        QueryWrapper<User> querywrapper = new  QueryWrapper<User>();
        querywrapper.eq(!StringUtils.isEmpty(username),"username",username);
        user = this.userMapper.selectOne(querywrapper);
        if(user == null){
            //密码加密，加盐
            Md5Hash md5Hash = new Md5Hash(username,password);
            String salt= md5Hash.toString();
            String addsalt = username + salt;
            String newPassword = new SimpleHash(algorithmName, password,  ByteSource.Util.bytes(addsalt), hashIterations).toHex();
            String uid = UUID.randomUUID().toString();
            user = new User();
            user.setUid(uid);
            user.setName(name);
            user.setUsername(username);
            user.setPassword(newPassword);
            user.setSalt(salt);
            user.setState("1");
            log.info("user:"+ user.toString());
            int retu = this.userMapper.insert(user);
            log.info("retu:"+ retu);
            if (retu != 0){
                msg = "用户添加成功！";
                map.put("data",user.toString());
            }else {
                msg = "用户添加失败！";
            }
        }else {
            msg = "用户已添加！";
        }
        map.put("msg",msg);
        return map;
    }

    /**
     * 用户查询
     * @param username
     * @param password
     * @return
     */
    public Map<String, Object> query(String username,String password){
        log.info("进入userService add:"+ username);
        Map<String, Object> map = new HashMap<String, Object>();
        String msg = null;
        //密码加密，加盐
        Md5Hash md5Hash = new Md5Hash(username,password);
        String salt= md5Hash.toString();
        String addsalt = username + salt;
        String newPassword = new SimpleHash(algorithmName, password,  ByteSource.Util.bytes(addsalt), hashIterations).toHex();
        QueryWrapper<User> querywrapper = new  QueryWrapper<User>();
        querywrapper.eq(!StringUtils.isEmpty(username),"username",username);
        querywrapper.eq(!StringUtils.isEmpty(newPassword),"password",newPassword);
        User user = this.userMapper.selectOne(querywrapper);
        if(user != null){
            msg = "用户查询成功";
            map.put("user",user);
        }else {
            msg = "用户查询失败";
        }
        log.info("userService add:"+ msg);
        return map;
    }
}
