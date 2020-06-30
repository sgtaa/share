package com.share.wxerp.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.wxerp.common.GlobalResult;
import com.share.wxerp.entity.SellRepair;
import com.share.wxerp.mapper.SellRepairMapper;
import com.share.wxerp.service.SellRepairService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Title: SellRepairServiceImpl
 * @ProjectName wxerp
 * @Description: 售后返修
 * @Author suguotai
 * @Date 2020/3/199:46
 */
@Service
@Transactional
public class SellRepairServiceImpl implements SellRepairService {
    @Autowired
    private SellRepairMapper sellRepairMapper;

    private static final Logger log = LoggerFactory.getLogger(SellRepairServiceImpl.class);

    public GlobalResult query(String detail) {
        log.info("进入SellRepairServiceImpl==>>query");
        String respmsg = "数据有误";
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        JSONObject detailJson = JSON.parseObject(detail);
        String srid = detailJson.getString("srid");
        String flag = detailJson.getString("flag");
        if(flag.equals("get") && !StringUtils.isEmpty(srid)){
            result = getObject(srid);
        }
        if(flag.equals("list")){
            result = listObjects(detail);
        }
        return result;
    }

    public GlobalResult getObject(String srid){
        SellRepair sellRepairResult = this.sellRepairMapper.selectById(srid);
        GlobalResult result = GlobalResult.success("查询成功",sellRepairResult);
        return  result;
    }

    public GlobalResult listObjects(String detail){
        JSONObject detailJson = JSON.parseObject(detail);
        String itemno = detailJson.getString("itemno");
        String product = detailJson.getString("product");

        QueryWrapper<SellRepair> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(product),"product",product);
        queryWrapper.eq(!StringUtils.isEmpty(itemno),"itemno",itemno);
        List<SellRepair> sellRepairResult = this.sellRepairMapper.selectList(queryWrapper);
        GlobalResult result = GlobalResult.success("查询成功",sellRepairResult);
        return  result;
    }
}
