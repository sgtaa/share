package com.share.wxerp.controller;

import com.share.wxerp.common.GlobalResult;
import com.share.wxerp.config.PermissionName;
import com.share.wxerp.service.serviceImpl.InputWarehouseServiceImpl;
import com.share.wxerp.service.serviceImpl.ProductServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: ProductControl
 * @ProjectName wxerp
 * @Description: TODO
 * @Author suguotai
 * @Date 2020/3/259:11
 */
@RestController
@RequestMapping("/product")
public class ProductControl {
    @Autowired
    private ProductServiceImpl productService;

    private static final Logger log = LoggerFactory.getLogger(ProductControl.class);
    private String respmsg = "服务器异常";
    private String msg = null;

    @RequestMapping("/query")
    @RequiresPermissions("product:query")
    @PermissionName("商品查询")
    public GlobalResult query(@RequestParam(value = "detail", required = false) String detail){
        log.info("进入ProductControl==>query");
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        if(!StringUtils.isEmpty(detail)){
            result = this.productService.query(detail);
        }
        return result;
    }
}
