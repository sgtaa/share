package com.share.wxerp.controller;

import com.share.wxerp.common.GlobalResult;
import com.share.wxerp.config.PermissionName;
import com.share.wxerp.service.serviceImpl.ReceivedServiceImpl;
import com.share.wxerp.service.serviceImpl.ReturnRepairServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: ReturnRepairControl
 * @ProjectName wxerp
 * @Description: TODO
 * @Author suguotai
 * @Date 2020/3/259:06
 */
@RestController
@RequestMapping("/returnRepair")
public class ReturnRepairControl {
    @Autowired
    private ReturnRepairServiceImpl returnRepairService;
    private static final Logger log = LoggerFactory.getLogger(ReturnRepairControl.class);
    private String respmsg = "服务器异常";
    private String msg = null;

    @RequestMapping("/query")
    @RequiresPermissions("returnRepair:query")
    @PermissionName("进货查询")
    public GlobalResult query(@RequestParam(value = "detail", required = false) String detail){
        log.info("进入ReturnRepairControl==>query");
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        if(!StringUtils.isEmpty(detail)){
            result = this.returnRepairService.query(detail);
        }
        return result;
    }

    @RequestMapping("/update")
    @RequiresPermissions("returnRepair:update")
    @PermissionName("进货修改")
    public GlobalResult update(@RequestParam(value = "detail", required = false) String detail){
        log.info("进入ReturnRepairControl==>update");
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        if(!StringUtils.isEmpty(detail)){
            result = this.returnRepairService.update(detail);
        }
        return result;
    }
}
