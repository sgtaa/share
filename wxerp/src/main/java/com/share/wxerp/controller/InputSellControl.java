package com.share.wxerp.controller;

import com.share.wxerp.common.GlobalResult;
import com.share.wxerp.config.PermissionName;
import com.share.wxerp.service.serviceImpl.InputSellServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: InputSellControl
 * @ProjectName wxerp
 * @Description: 售后入库
 * @Author suguotai
 * @Date 2020/3/258:58
 */
@RestController
@RequestMapping("/inputSell")
public class InputSellControl {
    @Autowired
    private InputSellServiceImpl inputSellService;

    private static final Logger log = LoggerFactory.getLogger(InputSellControl.class);
    private String respmsg = "服务器异常";
    private String msg = null;

    @RequestMapping("/query")
    @RequiresPermissions("inputSell:query")
    @PermissionName("售后入库查询")
    public GlobalResult query(@RequestParam(value = "detail", required = false) String detail){
        log.info("进入sellControl==>query");
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        if(!StringUtils.isEmpty(detail)){
            result = this.inputSellService.query(detail);
        }
        return result;
    }
}
