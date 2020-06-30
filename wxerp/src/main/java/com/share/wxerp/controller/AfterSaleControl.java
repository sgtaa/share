package com.share.wxerp.controller;

import com.share.wxerp.common.GlobalResult;
import com.share.wxerp.config.PermissionName;
import com.share.wxerp.service.serviceImpl.AfterSaleServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: AfterSale
 * @ProjectName wxerp
 * @Description: 售后
 * @Author suguotai
 * @Date 2020/3/238:29
 */
@RestController
@RequestMapping("/afterSale")
public class AfterSaleControl {
    @Autowired
    private AfterSaleServiceImpl afterSaleService;

    private static final Logger log = LoggerFactory.getLogger(UserControl.class);
    private String respmsg = "服务器异常";
    private String msg = null;

    @RequestMapping("/insert")
    @RequiresPermissions("afterSale:insert")
    @PermissionName("销售添加")
    public GlobalResult insert(@RequestParam(value = "detail", required = false) String detail){
        log.info("进入sellControl==>insert");
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        if(!StringUtils.isEmpty(detail)){
            result = this.afterSaleService.insert(detail);
        }
        return result;
    }

    @RequestMapping("/delete")
    @RequiresPermissions("afterSale:delete")
    @PermissionName("销售删除")
    public GlobalResult delete(@RequestParam(value = "detail", required = false) String detail){
        log.info("进入sellControl==>delete");
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        if(!StringUtils.isEmpty(detail)){
            result = this.afterSaleService.delete(detail);
        }
        return result;
    }

    @RequestMapping("/query")
    @RequiresPermissions("afterSale:query")
    @PermissionName("销售查询")
    public GlobalResult query(@RequestParam(value = "detail", required = false) String detail){
        log.info("进入sellControl==>query");
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        if(!StringUtils.isEmpty(detail)){
            result = this.afterSaleService.query(detail);
        }
        return result;
    }

    @RequestMapping("/update")
    @RequiresPermissions("afterSale:update")
    @PermissionName("销售修改")
    public GlobalResult update(@RequestParam(value = "detail", required = false) String detail){
        log.info("进入sellControl==>update");
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        if(!StringUtils.isEmpty(detail)){
            result = this.afterSaleService.update(detail);
        }
        return result;
    }

}
