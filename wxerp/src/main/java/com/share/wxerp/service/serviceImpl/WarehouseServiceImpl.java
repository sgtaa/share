package com.share.wxerp.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.wxerp.common.GlobalResult;
import com.share.wxerp.entity.InputWarehouse;
import com.share.wxerp.entity.Warehouse;
import com.share.wxerp.mapper.InputWarehouseMapper;
import com.share.wxerp.mapper.WarehouseMapper;
import com.share.wxerp.service.WarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Title: WarehouseServiceImpl
 * @ProjectName wxerp
 * @Description: TODO
 * @Author suguotai
 * @Date 2020/3/199:45
 */
@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private WarehouseMapper warehouseMapper;

    private static final Logger log = LoggerFactory.getLogger(WarehouseServiceImpl.class);

    public GlobalResult query(String detail) {
        log.info("进入WarehouseServiceImpl==>>query");
        String respmsg = "数据有误";
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        JSONObject detailJson = JSON.parseObject(detail);
        String whid = detailJson.getString("whid");
        String flag = detailJson.getString("flag");
        if(flag.equals("get") && !StringUtils.isEmpty(whid)){
            result = getObject(whid);
        }
        if(flag.equals("list")){
            result = listObjects(detail);
        }
        return result;
    }

    public GlobalResult getObject(String whid){
        Warehouse warehouseResult = this.warehouseMapper.selectById(whid);
        GlobalResult result = GlobalResult.success("查询成功",warehouseResult);
        return  result;
    }

    public GlobalResult listObjects(String detail){
        JSONObject detailJson = JSON.parseObject(detail);

        String itemno = detailJson.getString("itemno");
        String product = detailJson.getString("product");

        QueryWrapper<Warehouse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(product),"product",product);
        queryWrapper.eq(!StringUtils.isEmpty(itemno),"itemno",itemno);
        List<Warehouse> warehouseResult = this.warehouseMapper.selectList(queryWrapper);
        GlobalResult result = GlobalResult.success("查询成功",warehouseResult);
        return  result;
    }
}
