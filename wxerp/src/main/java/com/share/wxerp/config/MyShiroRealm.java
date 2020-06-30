package com.share.wxerp.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.wxerp.entity.Permission;
import com.share.wxerp.entity.Role;
import com.share.wxerp.entity.User;
import com.share.wxerp.mapper.PermissionMapper;
import com.share.wxerp.mapper.RoleMapper;
import com.share.wxerp.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.*;

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    private static final Logger log = LoggerFactory.getLogger(MyShiroRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user  = (User)principals.getPrimaryPrincipal();
        if("admin".equals(user.getUsername())){
            //查询所有角色
            List<Role> roleList = this.roleMapper.getAllRole();
            for(Role role:roleList){
                authorizationInfo.addRole(role.getRole());
            }
            //拥有所有权限
            authorizationInfo.addStringPermission("*:*");
        }else{
            //根据用户id查询该用户所具有的角色
            List<Role> roleList = this.roleMapper.getRoleList(user.getUid());
            if(!roleList.isEmpty()){
                for(Role role:roleList){
                    authorizationInfo.addRole(role.getRole());
                    //根据角色id查询该用户所具有的权限
                    List<Permission> permissionList = this.permissionMapper.getPermissionList(role.getRid());
                    for(Permission permission:permissionList){
                        authorizationInfo.addStringPermission(permission.getPermission());
                    }
                }
            }
        }
        log.info("角色-->Roles::"+ authorizationInfo.getRoles().toString());
        log.info("操作方法-->Permissions::"+ authorizationInfo.getStringPermissions().toString());
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        log.info("身份认证-->MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!org.springframework.util.StringUtils.isEmpty(username),"username",username);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            return null;
        }
        log.info("身份认证-->"+ user.toString());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }
    //清除缓存
    public void clearAuthz(){
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}