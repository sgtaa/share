package com.share.wxerp.controller;

import com.share.wxerp.common.GlobalResult;
import com.share.wxerp.config.PermissionName;
import com.share.wxerp.service.serviceImpl.InputWarehouseServiceImpl;
import com.share.wxerp.service.serviceImpl.ReceivedServiceImpl;
import com.share.wxerp.service.serviceImpl.ReturnRepairServiceImpl;
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
 * @Title: ReceivedControl
 * @ProjectName wxerp
 * @Description: 进货
 * @Author suguotai
 * @Date 2020/3/209:50
 */
@RestController
@RequestMapping("/received")
public class ReceivedControl {
    @Autowired
    private ReceivedServiceImpl receivedService;
    private static final Logger log = LoggerFactory.getLogger(UserControl.class);
    private String respmsg = "服务器异常";
    private String msg = null;

    @RequestMapping("/insert")
    @RequiresPermissions("received:insert")
    @PermissionName("进货添加")
    public GlobalResult insert(@RequestParam(value = "detail", required = false) String detail){
        log.info("进入ReceivedControl==>insert");
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        if(!StringUtils.isEmpty(detail)){
            result = receivedService.insert(detail);
        }
        return result;
    }

    @RequestMapping("/delete")
    @RequiresPermissions("received:delete")
    @PermissionName("进货删除")
    public GlobalResult delete(@RequestParam(value = "detail", required = false) String detail){
        log.info("进入ReceivedControl==>delete");
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        if(!StringUtils.isEmpty(detail)){
            result = this.receivedService.delete(detail);
        }
        return result;
    }

    @RequestMapping("/query")
    @RequiresPermissions("received:query")
    @PermissionName("进货查询")
    public GlobalResult query(@RequestParam(value = "detail", required = false) String detail){
        log.info("进入ReceivedControl==>query");
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        if(!StringUtils.isEmpty(detail)){
            result = this.receivedService.query(detail);
        }
        return result;
    }

    @RequestMapping("/update")
    @RequiresPermissions("received:update")
    @PermissionName("进货修改")
    public GlobalResult update(@RequestParam(value = "detail", required = false) String detail){
        log.info("进入ReceivedControl==>update");
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        if(!StringUtils.isEmpty(detail)){
            result = this.receivedService.update(detail);
        }
        return result;
    }

}
