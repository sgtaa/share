package com.share.wxerp.controller;

import com.share.wxerp.common.GlobalResult;
import com.share.wxerp.config.PermissionName;
import com.share.wxerp.service.serviceImpl.SellRepairServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: sellRepairControl
 * @ProjectName wxerp
 * @Description: 售后返修
 * @Author suguotai
 * @Date 2020/3/259:01
 */
@RestController
@RequestMapping("/sellRepair")
public class SellRepairControl {
    @Autowired
    private SellRepairServiceImpl sellRepairService;

    private static final Logger log = LoggerFactory.getLogger(SellRepairControl.class);
    private String respmsg = "服务器异常";
    private String msg = null;

    @RequestMapping("/query")
    @RequiresPermissions("sellRepair:query")
    @PermissionName("售后返修查询")
    public GlobalResult query(@RequestParam(value = "detail", required = false) String detail){
        log.info("进入SellRepairControl==>query");
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        if(!StringUtils.isEmpty(detail)){
            result = this.sellRepairService.query(detail);
        }
        return result;
    }
}
