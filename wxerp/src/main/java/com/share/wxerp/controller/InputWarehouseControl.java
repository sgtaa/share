package com.share.wxerp.controller;

import com.share.wxerp.common.GlobalResult;
import com.share.wxerp.config.PermissionName;
import com.share.wxerp.service.serviceImpl.InputSellServiceImpl;
import com.share.wxerp.service.serviceImpl.InputWarehouseServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: InputWarehouseControl
 * @ProjectName wxerp
 * @Description: TODO
 * @Author suguotai
 * @Date 2020/3/25 9:13
 */
@RestController
@RequestMapping("/inputWarehouse")
public class InputWarehouseControl {
    @Autowired
    private InputWarehouseServiceImpl inputWarehouseService;

    private static final Logger log = LoggerFactory.getLogger(InputSellControl.class);
    private String respmsg = "服务器异常";
    private String msg = null;

    @RequestMapping("/query")
    @RequiresPermissions("inputWarehouse:query")
    @PermissionName("入库查询")
    public GlobalResult query(@RequestParam(value = "detail", required = false) String detail){
        log.info("进入InputWarehouseControl==>query");
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        if(!StringUtils.isEmpty(detail)){
            result = this.inputWarehouseService.query(detail);
        }
        return result;
    }
}
