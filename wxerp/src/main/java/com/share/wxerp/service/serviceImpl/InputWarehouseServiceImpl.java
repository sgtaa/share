package com.share.wxerp.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.wxerp.common.GlobalResult;
import com.share.wxerp.entity.InputWarehouse;
import com.share.wxerp.mapper.InputWarehouseMapper;
import com.share.wxerp.service.InputWarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Title: InputWarehouseServiceImpl
 * @ProjectName wxerp
 * @Description: 进货入库
 * @Author suguotai
 * @Date 2020/3/199:56
 */
@Service
@Transactional
public class InputWarehouseServiceImpl implements InputWarehouseService {
    @Autowired
    private InputWarehouseMapper inputWarehouseMapper;

    private static final Logger log = LoggerFactory.getLogger(InputWarehouseServiceImpl.class);

    public GlobalResult query(String detail) {
        log.info("进入InputWarehouseServiceImpl==>>query");
        String respmsg = "数据有误";
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        JSONObject detailJson = JSON.parseObject(detail);
        String iwhid = detailJson.getString("iwhid");
        String flag = detailJson.getString("flag");
        if(flag.equals("get") && !StringUtils.isEmpty(iwhid)){
            result = getObject(iwhid);
        }
        if(flag.equals("list")){
            result = listObjects(detail);
        }
        return result;
    }

    public GlobalResult getObject(String iwhid){
        InputWarehouse inputWarehouseResult = this.inputWarehouseMapper.selectById(iwhid);
        GlobalResult result = GlobalResult.success("查询成功",inputWarehouseResult);
        return  result;
    }

    public GlobalResult listObjects(String detail){
        JSONObject detailJson = JSON.parseObject(detail);

        String itemno = detailJson.getString("itemno");
        String product = detailJson.getString("product");

        QueryWrapper<InputWarehouse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(product),"product",product);
        queryWrapper.eq(!StringUtils.isEmpty(itemno),"itemno",itemno);
        List<InputWarehouse> inputWarehouseResult = this.inputWarehouseMapper.selectList(queryWrapper);
        GlobalResult result = GlobalResult.success("查询成功",inputWarehouseResult);
        return  result;
    }
}
