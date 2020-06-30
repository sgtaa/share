package com.share.wxerp.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.wxerp.entity.Permission;
import com.share.wxerp.entity.Role;
import com.share.wxerp.mapper.PermissionMapper;
import com.share.wxerp.mapper.RoleMapper;
import com.share.wxerp.service.PermissionService;
import com.share.wxerp.service.RoleService;
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
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    public Map<String, Object> add(Map<String, Object> deta){
        log.info("进入roleServiceadd:"+ deta);
        Map<String, Object> map = new HashMap<String, Object>();
        String msg;

        /*JSONObject detailJson = JSON.parseObject(String.valueOf(deta));
        String name = detailJson.getString("name");
        String resourceType = detailJson.getString("resourceType");
        String url = detailJson.getString("url");
        String permission = detailJson.getString("permission");
        String parentId = detailJson.getString("parentId");
        String parentIds = detailJson.getString("parentIds");*/

        String role = (String) deta.get("role");
        String description = (String) deta.get("description");

        Role rolel;
        QueryWrapper<Role> querywrapper = new  QueryWrapper<Role>();
        querywrapper.eq(!StringUtils.isEmpty(role),"role",role);
        querywrapper.eq(!StringUtils.isEmpty(description),"description",description);
        rolel = this.roleMapper.selectOne(querywrapper);
        if(rolel == null){
            rolel = new Role();
            String rid = UUID.randomUUID().toString();
            rolel.setRid(rid);
            rolel.setRole(role);
            rolel.setDescription(description);
            int retu = this.roleMapper.insert(rolel);
            if (retu != 0){
                msg = "角色添加成功！";
                map.put("data",rolel.toString());
            }else {
                msg = "角色添加失败！";
            }
        }else {
            msg = "角色已添加！";
        }
        map.put("msg",msg);
        return map;
    }
}
