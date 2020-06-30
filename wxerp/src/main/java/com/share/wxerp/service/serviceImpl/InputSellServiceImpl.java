package com.share.wxerp.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.wxerp.common.GlobalResult;
import com.share.wxerp.entity.AfterSale;
import com.share.wxerp.entity.InputSell;
import com.share.wxerp.mapper.InputSellMapper;
import com.share.wxerp.service.InputSellService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Title: InputSellServiceImpl
 * @ProjectName wxerp
 * @Description: 售后入库
 * @Author suguotai
 * @Date 2020/3/199:57
 */
@Service
@Transactional
public class InputSellServiceImpl implements InputSellService {
    @Autowired
    private InputSellMapper inputSellMapper;

    private static final Logger log = LoggerFactory.getLogger(InputSellServiceImpl.class);

    public GlobalResult query(String detail) {
        log.info("进入InputSellServiceImpl==>>query");
        String respmsg = "数据有误";
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        JSONObject detailJson = JSON.parseObject(detail);
        String isid = detailJson.getString("isid");
        String flag = detailJson.getString("flag");
        if(flag.equals("get") && !StringUtils.isEmpty(isid)){
            result = getObject(isid);
        }
        if(flag.equals("list")){
            result = listObjects(detail);
        }
        return result;
    }

    public GlobalResult getObject(String isid){
        InputSell inputSellResult = this.inputSellMapper.selectById(isid);
        GlobalResult result = GlobalResult.success("查询成功",inputSellResult);
        return  result;
    }

    public GlobalResult listObjects(String detail){
        JSONObject detailJson = JSON.parseObject(detail);
        String itemno = detailJson.getString("itemno");
        String product = detailJson.getString("product");

        QueryWrapper<InputSell> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(product),"product",product);
        queryWrapper.eq(!StringUtils.isEmpty(itemno),"itemno",itemno);
        List<InputSell> inputSellResult = this.inputSellMapper.selectList(queryWrapper);
        GlobalResult result = GlobalResult.success("查询成功",inputSellResult);
        return  result;
    }
}
