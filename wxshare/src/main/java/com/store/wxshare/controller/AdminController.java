package com.store.wxshare.controller;

import com.store.wxshare.common.Approach;
import com.store.wxshare.common.GlobalResult;
import com.store.wxshare.serviceImpl.AdminSourceServiceImpl;
import com.store.wxshare.serviceImpl.AdminUserServiceImpl;
import com.store.wxshare.serviceImpl.ClassifyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author suguotai
 * @Description 管理员管理
 * @Date
 * @Param
 * @return
 **/
@Controller
public class AdminController {
    @Autowired
    private AdminSourceServiceImpl adminSourceServiceImpl;
    @Autowired
    private AdminUserServiceImpl  adminUserServiceImpl;
    @Autowired
    private ClassifyServiceImpl classifyServiceImpl;
    /**
     * 管理员操作
     */
    @PostMapping("/adminControl")
    @ResponseBody
    public GlobalResult user_control(@RequestParam(value = "handle", required = false) String handle,
                                     @RequestParam(value = "operflag", required = false) String operflag,
                                     @RequestParam(value = "detail", required = false) String detail,
                                     @RequestParam(value = "signature", required = false) String signature){
        GlobalResult result = null;
        System.out.println("————————operflag="+ operflag +"detail="+ detail + " signature" +signature);
        if(handle.equals(Approach.OPERFLAG_USER)){
            result = adminUserServiceImpl.user_control(operflag,detail,signature);
        }else if(handle.equals(Approach.OPERFLAG_RESOURCE)){
            result = adminSourceServiceImpl.resource_control(operflag,detail,signature);
        }else if(handle.equals(Approach.OPERFLAG_CLASSIFY)){
            result = classifyServiceImpl.classify_control(operflag,detail,signature);
        }
        return result;
    }
}
