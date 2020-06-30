package com.store.wxshare.controller;

import com.store.wxshare.common.Approach;
import com.store.wxshare.common.GlobalResult;
import com.store.wxshare.serviceImpl.ResourceServiceImpl;
import com.store.wxshare.serviceImpl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户管理
 */
@Controller
public class UserController {
    @Autowired
    private UsersServiceImpl usersServiceImpl;
    @Autowired
    private ResourceServiceImpl resourceServiceImpl;

    /**
     * 微信用户登录详情
     */
    @PostMapping("wx/login")
    @ResponseBody
    public GlobalResult user_login(@RequestParam(value = "code", required = false) String code,
                                   @RequestParam(value = "rawData", required = false) String rawData,
                                   @RequestParam(value = "signature", required = false) String signature,
                                   @RequestParam(value = "encrypteData", required = false) String encrypteData,
                                   @RequestParam(value = "iv", required = false) String iv) {
        GlobalResult result = usersServiceImpl.user_login(code,rawData,signature,encrypteData,iv);
        return result;
    }
    /**
     * 用户操作
     */
    @PostMapping("userControl")
    @ResponseBody
    public GlobalResult user_control(@RequestParam(value = "handle", required = false) String handle,
                                     @RequestParam(value = "operflag", required = false) String operflag,
                                     @RequestParam(value = "detail", required = false) String detail,
                                     @RequestParam(value = "signature", required = false) String signature){
        GlobalResult result = null;
        System.out.println("————————operflag="+ operflag +"detail="+ detail + " signature" +signature);
        if(handle.equals(Approach.OPERFLAG_USER)){
            result = usersServiceImpl.user_control(operflag,detail,signature);
        }else if(handle.equals(Approach.OPERFLAG_RESOURCE)){
            result = resourceServiceImpl.resource_control(operflag,detail,signature);
        } 
        return result;
    }
}

