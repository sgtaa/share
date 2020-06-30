package com.share.wxerp.controller;

import com.share.wxerp.common.GlobalResult;
import com.share.wxerp.config.PermissionName;
import com.share.wxerp.service.serviceImpl.ProductServiceImpl;
import com.share.wxerp.service.serviceImpl.WarehouseServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: warehouseControl
 * @ProjectName wxerp
 * @Description: TODO
 * @Author suguotai
 * @Date 2020/3/259:03
 */
@RestController
@RequestMapping("/warehouse")
public class WarehouseControl {
    @Autowired
    private WarehouseServiceImpl warehouseService;

    private static final Logger log = LoggerFactory.getLogger(WarehouseControl.class);
    private String respmsg = "服务器异常";
    private String msg = null;

    @RequestMapping("/query")
    @RequiresPermissions("warehouse:query")
    @PermissionName("库存查询")
    public GlobalResult query(@RequestParam(value = "detail", required = false) String detail){
        log.info("进入WarehouseControl==>query");
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        if(!StringUtils.isEmpty(detail)){
            result = this.warehouseService.query(detail);
        }
        return result;
    }
}
