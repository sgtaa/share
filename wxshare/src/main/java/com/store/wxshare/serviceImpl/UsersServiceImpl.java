package com.store.wxshare.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.store.wxshare.common.Approach;
import com.store.wxshare.common.GlobalResult;
import com.store.wxshare.common.WeChatUtil;
import com.store.wxshare.entity.Convert;
import com.store.wxshare.entity.Integral;
import com.store.wxshare.entity.Submit;
import com.store.wxshare.entity.Users;
import com.store.wxshare.mapper.ConvertMapper;
import com.store.wxshare.mapper.IntegralMapper;
import com.store.wxshare.mapper.SubmitMapper;
import com.store.wxshare.mapper.UsersMapper;
import com.store.wxshare.service.UsersService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
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
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private IntegralMapper integralMapper;
    @Autowired
    private SubmitMapper submitMapper;
    @Autowired
    private ConvertMapper convertMapper;
    /**
     * 时间格式转换
     */
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 微信用户登录操作
     */
    public GlobalResult user_login(String code,String rawData,String signature,String encrypteData,String iv) {
        // 用户非敏感信息：rawData
        // 签名：signature
        Map<String, Object> map = new HashMap<String, Object>();
        String userid = null;
        System.out.println("code="+ code +"detail="+ rawData + " signature" +signature);
        JSONObject rawDataJson = JSON.parseObject(rawData);
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WeChatUtil.getSessionKeyOrOpenId(code);
        // 3.接收微信接口服务 获取返回的参数
        String openid = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");
        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
        String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
        if (!signature.equals(signature2)) {
            map.put("userid","1e380450-1252-4e02-aea9-a518cc9a9b2f");
            return GlobalResult.build(500, "签名校验失败", map);
        }
        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.eq(openid != null,"openId",openid);
        Users user = this.usersMapper.selectOne(queryWrapper);
        // uuid生成唯一key，用于维护微信小程序用户与服务端的会话（自定义登录状态）
        String skey = UUID.randomUUID().toString();
        userid = user.getUserId().toString();
        if (user == null) {
            // 用户信息入库
            String nickName = rawDataJson.getString("nickName");
            String avatarUrl = rawDataJson.getString("avatarUrl");
            String gender = rawDataJson.getString("gender");
            String city = rawDataJson.getString("city");
            String country = rawDataJson.getString("country");
            String province = rawDataJson.getString("province");
            String address = country + province + city ;

            user = new Users();
            userid = UUID.randomUUID().toString();
            user.setUserId(userid);
            user.setOpenId(openid);

            user.setSkey(skey);
            user.setCreateDate(new Date());
            user.setLastVisitTime(new Date());
            user.setSessionKey(sessionKey);
            user.setAddress(address);
            user.setAvatarUrl(avatarUrl);
            user.setGender(Integer.parseInt(gender));
            user.setNickName(nickName);
            this.usersMapper.insert(user);
        } else {
            // 已存在，更新用户登录时间
            user.setLastVisitTime(new Date());
            // 重新设置会话skey
            user.setSkey(skey);
            this.usersMapper.updateById(user);
        }
        //encrypteData比rowData多了appid和openid
        //JSONObject userInfo = WechatUtil.getUserInfo(encrypteData, sessionKey, iv);
        //6. 把新的skey返回给小程序
        map.put("userid",userid);
        GlobalResult result = GlobalResult.build(200, null,  map);
        return result;
    }
    /**
     * 用户操作
     * @param operflag
     * @param detail
     * @param signature
     * @return
     */
    public GlobalResult user_control(String operflag, String detail, String signature) {
        String msg = null;
        String data = null;
        Map<String, Object> respData = null;
        System.out.println("operflag="+ operflag +"detail="+ detail + " signature" +signature);
        if (operflag.equals(Approach.OPERFLAG_NEW)){

            System.out.println("operflag="+ operflag + "新增成功");
        }
        if (operflag.equals(Approach.OPERFLAG_MODIFY)){

            respData =  modify(detail,signature);
            System.out.println("operflag="+ operflag + "修改成功");
        }
        if (operflag.equals(Approach.OPERFLAG_DELETE)){
            respData =  delete(detail,signature);
            System.out.println("operflag="+ operflag + "删除成功");
        }
        if (operflag.equals(Approach.OPERFLAG_QUERY)){
             respData = query(detail,signature);
            System.out.println("operflag="+ operflag + "查询成功");
        }
        if (operflag.equals(Approach.OPERFLAG_CHECKIN)){
            respData = checkIn(detail,signature);
            System.out.println("operflag="+ operflag + "签到成功");

        }
        if (operflag.equals(Approach.OPERFLAG_CONVERT)){
            respData = convert(detail,signature);
            System.out.println("operflag="+ operflag + "用户查看已兑换成功");
        }
        if (operflag.equals(Approach.OPERFLAG_SUBMIT)){

            respData = submit(detail,signature);
            System.out.println("operflag="+ operflag + "用户查看已提交成功");
        }
        if (operflag.equals(Approach.OPERFLAG_OWER)){

            respData = woer(detail,signature);
            System.out.println("operflag="+ operflag + "用户查看我的资源");
        }
        GlobalResult result = GlobalResult.build(200, (String) respData.get("msg"), respData.get("data"));
        return result;
    }



    /**
     * 新增
     */
    public String add(String detail, String signature){
        return null;
    }
    /**
     * 修改
     */
    public Map<String, Object> modify(String detail, String signature){
        System.out.println("-----------------------进入用户修改方法---------------------");
        String msg = null;
        Users user =null;
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);

        System.out.println("detail:" + detailJson.getString("detail")+"++"+detailJson.getString("userid"));
        String userid = detailJson.getString("userid");
        String ename = detailJson.getString("ename");
        String mobile = detailJson.getString("mobile");
        String email = detailJson.getString("email");
        String detailed_address= detailJson.getString("detailedaddress");
        if(!StringUtils.isEmpty(userid)){
            user = this.usersMapper.selectById(userid);
            if (user != null){
                user.setEname(ename);
                user.setEmail(email);
                user.setMobile(mobile);
                user.setDetailedAddress(detailed_address);
                user.setModiDate(new Date());
                this.usersMapper.updateById(user);
                msg = "修改成功";
                map.put("data",user);
            }else {
                msg = "没有该用户，修改失败！";
                map.put("data","操作失败");
            }
        }else{
            msg = "修改失败，信息错误！";
        }
        map.put("msg",msg);

        return map;
    }
    /**
     * 删除
     */
    public Map<String, Object> delete(String detail, String signature){
        System.out.println("-----------------------进入用户删除已提交资源操作方法---------------------");
        String msg = null;
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);

        return  map;
    }
    /**
     * 用户查询
     */
    public Map<String, Object> query(String detail, String signature){
        System.out.println("-----------------------进入用户查询操作方法---------------------");
        String msg = null;
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);
        System.out.println("detail:" + detailJson.getString("date")+"++"+detailJson.getString("userid")+"++"+detailJson.getString("signature"));
        String userid = detailJson.getString("userid");
        if(!StringUtils.isEmpty(userid)){
            Map<String, Object> mapuser = new HashMap<String, Object>();
            List<Users> userIntegral = this.usersMapper.selectuserIntegralById(userid);
            if(userIntegral != null){
                for (Users user : userIntegral) {
                    mapuser.put("ename",user.getEname());
                    mapuser.put("nickname",user.getNickName());
                    mapuser.put("mobile",user.getMobile());
                    mapuser.put("email",user.getEmail());
                    mapuser.put("detailedAddress",user.getDetailedAddress());
                    mapuser.put("grade",user.getIntegral().getGrade());
                }
                msg = "操作成功，查询到该用户信息！";
                map.put("data",mapuser);
            }else{
                msg = "操作失败，查询不到该用户信息！";
            }
        }else{
            msg = "查询失败，信息错误！";
        }
        map.put("msg",msg);

        return map;
    }
    /**
     * 签到
     */
    public Map<String, Object> checkIn(String detail, String signature){

        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        String msg = null;
        int grade = 0;
        System.out.println("-----------------------进入用户签到操作方法---------------------");
        JSONObject detailJson = JSON.parseObject(detail);
        System.out.println("detail:" + detailJson.getString("date")+"++"+detailJson.getString("userid")+"++"+detailJson.getString("signature"));
        //判断是否为新用户第一次签到领取积分
        String userid = detailJson.getString("userid");
        String flag = detailJson.getString("flag");
        if(!StringUtils.isEmpty(userid)){
            QueryWrapper<Integral> queryWrapper = new QueryWrapper<Integral>();
            queryWrapper.eq("userid",userid);
            Integral integral = this.integralMapper.selectOne(queryWrapper);
            if(flag.equals("onLoad")){
                if (integral == null) {
                    grade = 0;
                    msg = "成功查询积分";
                }else{
                    grade = integral.getGrade();
                    msg = "成功查询积分";
                }
            }
            if(flag.equals("checking")) {
                if (integral == null) {
                    integral = new Integral();
                    integral.setIntegrid(UUID.randomUUID().toString());
                    integral.setUserid(userid);
                    integral.setGrade(30);
                    integral.setState("0");
                    integral.setCreateDate(new Date());
                    integral.setModiDate(new Date());
                    integral.setRegisterDate(new Date());
                    this.integralMapper.insert(integral);
                    grade = 30;
                    msg = "新用户签到成功，已领取30积分";
                } else {
                    //判断当前时间进行每天签到领取积分
                    String dateStr = format.format(new Date());
                    String registerDateStr = format.format(integral.getRegisterDate());
                    System.out.println("dateStr" + dateStr + "---------------" + "registerDateStr" + registerDateStr);
                    if (registerDateStr != null && !dateStr.equals(registerDateStr)) {
                        grade = integral.getGrade() + 5;
                        integral.setGrade(grade);
                        msg = "签到成功，已领取5积分";
                        map1.put("checkIn", "成功");
                    } else {
                        grade = integral.getGrade();
                        msg = "今天已签到，请您明天再来吧！";
                        map1.put("checkIn", "已签到");
                    }
                    integral.setModiDate(new Date());
                    integral.setRegisterDate(new Date());
                    this.integralMapper.updateById(integral);

                }
            }
        }else{
            msg = "签到失败！";
        }
        map1.put("grade",grade);
        map.put("msg",msg);
        map.put("data",map1);
        return map;
    }
    /**
     * 兑换资源查询
     */
    public Map<String,Object> convert(String detail, String signature){
        System.out.println("-----------------------进入用户兑换资源查询操作方法---------------------");
        String msg = null;
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);
        System.out.println("detail:" + detailJson.toString() + "++" + detailJson.getString("userid") + "++" + detailJson.getString("signature"));

        String userid = detailJson.getString("userid");
        String convertid = detailJson.getString("convertid");
        msg = "查询失败，信息错误！";
        //根据userid来查
        if (!StringUtils.isEmpty(userid)) {
            QueryWrapper<Convert> queryWrapper = new QueryWrapper<Convert>();
            queryWrapper.eq("userid",userid);
            queryWrapper.in("state",0 );
            //过滤字段
            queryWrapper.select("convertid","ename","picture_address","integral","described","create_date");
            Page<Convert> page = new Page<Convert>(1,2);
            IPage<Map<String, Object>> resultMap = this.convertMapper.selectMapsPage(page,queryWrapper);
            if(resultMap != null){
                map.put("data",resultMap);
                msg = "分页查询成功";
            }else {
                msg = "分页查询失败";
            }
        }

        //根据convertid来查详细信息
        if (!StringUtils.isEmpty(convertid)) {
            QueryWrapper<Convert> queryWrapper = new QueryWrapper<Convert>();
            queryWrapper.eq( "convertid",convertid);
            //过滤字段
            queryWrapper.select("convertid","ename","picture_address","integral","described","address","summary");
            List<Map<String, Object>> resultMap = this.convertMapper.selectMaps(queryWrapper);
            if(resultMap != null){
                map.put("data",resultMap);
                msg = "详情查询成功";
            }else {
                msg = "详情查询失败";
            }
        }

        map.put("msg",msg);

        return  map;
    }
    /**
     * 提交资源查询
     */
    private Map<String,Object> submit(String detail, String signature) {
        System.out.println("-----------------------进入用户提交资源查询操作方法---------------------");
        String msg = null;
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);
        System.out.println("detail:" + detailJson.toString() + "++" + detailJson.getString("userid") + "++" + detailJson.getString("signature"));

        String userid = detailJson.getString("userid");
        String submitid = detailJson.getString("submitid");
        msg = "查询失败，信息错误！";
        //根据userid来查
        if (!StringUtils.isEmpty(userid)) {
            QueryWrapper<Submit> queryWrapper = new QueryWrapper<Submit>();
            queryWrapper.eq("userid",userid);
            queryWrapper.in("state",0,1);
            //过滤字段
            queryWrapper.select("submitid","ename","picture_address","described","create_date","state","remark");
            Page<Submit> page = new Page<Submit>(1,2);
            IPage<Map<String, Object>> resultMap = this.submitMapper.selectMapsPage(page,queryWrapper);
            if(resultMap != null){
                map.put("data",resultMap);
                msg = "分页查询成功";
            }else {
                msg = "分页查询失败";
            }
        }

        //根据submitid来查详细信息
        if (!StringUtils.isEmpty(submitid)) {
            QueryWrapper<Submit> queryWrapper = new QueryWrapper<Submit>();
            queryWrapper.eq( "submitid",submitid);
            //过滤字段
            queryWrapper.select("submitid","ename","picture_address","described","address","summary","remark");
            List<Map<String, Object>> resultMap = this.submitMapper.selectMaps(queryWrapper);
            if(resultMap != null){
                map.put("data",resultMap);
                msg = "详情查询成功";
            }else {
                msg = "详情查询失败";
            }
        }

        map.put("msg",msg);
        return  map;
    }
    /**
     * 我的资源
     */
    public Map<String,Object> woer(String detail, String signature){
        System.out.println("-----------------------进入用户我的资源查询操作方法---------------------");
        String msg = null;
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);
        System.out.println("detail:" + detailJson.toString() + "++" + detailJson.getString("userid") + "++" + detailJson.getString("signature"));

        String userid = detailJson.getString("userid");
        msg = "查询失败，信息错误！";
        //根据userid来查已提交资源
        if (!StringUtils.isEmpty(userid)) {
            QueryWrapper<Submit> queryWrapper = new QueryWrapper<Submit>();
            queryWrapper.eq("userid",userid);
            queryWrapper.in("state",0,1);
            queryWrapper.last("limit 4");
            //过滤字段
            queryWrapper.select("submitid","ename","picture_address","described","create_date","state");
            List<Map<String, Object>> resultList = this.submitMapper.selectMaps(queryWrapper);
            if(resultList != null){
                map1.put("submitList",resultList);
                String submitmsg = "已提交资源查询成功";
                map1.put("submitmsg",submitmsg);
            }else {
                msg = "已提交资源查询失败";
            }
            QueryWrapper<Convert> queryWrapper1 = new QueryWrapper<Convert>();
            queryWrapper1.eq("userid",userid);
            queryWrapper1.in("state",0 );
            queryWrapper1.last("limit 4");
            //过滤字段
            queryWrapper1.select("convertid","ename","picture_address","integral","described","create_date");
            List<Map<String, Object>> resultList1 = this.convertMapper.selectMaps(queryWrapper1);
            if(resultList1 != null){
                map1.put("convertList",resultList1);
                String convertmsg = "已提交资源查询成功";
                map1.put("convertmsg",convertmsg);
            }else {
                msg = "已提交资源查询失败";
            }
            msg = "我的资源查询成功！";
        }
        map.put("data",map1);
        map.put("msg",msg);
        return  map;
    }
}
