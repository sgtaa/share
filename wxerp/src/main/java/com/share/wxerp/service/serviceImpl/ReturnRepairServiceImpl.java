package com.share.wxerp.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.wxerp.common.GlobalResult;
import com.share.wxerp.entity.Received;
import com.share.wxerp.entity.ReturnRepair;
import com.share.wxerp.entity.SellRepair;
import com.share.wxerp.mapper.ReturnRepairMapper;
import com.share.wxerp.mapper.SellRepairMapper;
import com.share.wxerp.service.ReturnRepairService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Title: ReturnRepairServiceImpl
 * @ProjectName wxerp
 * @Description: TODO
 * @Author suguotai
 * @Date 2020/3/199:50
 */
@Service
@Transactional
public class ReturnRepairServiceImpl implements ReturnRepairService {
    @Autowired
    private ReturnRepairMapper returnRepairMapper;
    @Autowired
    private SellRepairMapper sellRepairMapper;

    private static final Logger log = LoggerFactory.getLogger(ReturnRepairServiceImpl.class);

    public GlobalResult update(String detail) {
        log.info("进入ReturnRepairServiceImpl==>>update");
        String respmsg = "数据有误";
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        JSONObject detailJson = JSON.parseObject(detail);

        String rrid = detailJson.getString("rrid");
        String srid = detailJson.getString("srid");
        String type = detailJson.getString("type");
        String itemno = detailJson.getString("itemno");
        int amount = detailJson.getIntValue("amount");
        String addressee = detailJson.getString("addressee");
        String packard = detailJson.getString("packard");
        String express = detailJson.getString("express");
        String number = detailJson.getString("number");
        String returnCase = detailJson.getString("returnCase");
        String returnType = detailJson.getString("returnType");
        String status = detailJson.getString("status");
        String name = detailJson.getString("name");
        String username = detailJson.getString("username");

        if(!StringUtils.isEmpty(rrid)){
            ReturnRepair returnRepairObj = this.returnRepairMapper.selectById(rrid);
            if(returnRepairObj != null){
                ReturnRepair returnRepair = new ReturnRepair();
                returnRepair.setRrid(returnRepairObj.getRrid());
                returnRepair.setType(type);
                returnRepair.setItemno(itemno);
                returnRepair.setAmount(amount);
                returnRepair.setAddressee(addressee);
                returnRepair.setPackard(packard);
                returnRepair.setExpress(express);
                returnRepair.setNumber(number);
                returnRepair.setReturnCase(returnCase);
                returnRepair.setReturnType(returnType);
                returnRepair.setStatus(status);
                returnRepair.setReturnDate(new Date());
                returnRepair.setCreateDate(returnRepairObj.getCreateDate());
                returnRepair.setUsername(username);
                returnRepair.setName(name);
                returnRepair.setModiDate(new Date());
                returnRepair.setRecid(returnRepairObj.getRecid());
                int rrResult = this.returnRepairMapper.updateById(returnRepair);
                if(rrResult != 0){
                    result = GlobalResult.success("提交成功");
                }
            }else{
                result = GlobalResult.errorMsg("修改失败，该信息不存在！");
            }
        }else if(!StringUtils.isEmpty(srid)){
            SellRepair sellRepairObj = this.sellRepairMapper.selectById(srid);
            if(sellRepairObj != null){
                ReturnRepair returnRepair = new ReturnRepair();
                String newrrid = UUID.randomUUID().toString();
                returnRepair.setRrid(newrrid);
                returnRepair.setType(type);
                returnRepair.setItemno(itemno);
                returnRepair.setAmount(amount);
                returnRepair.setAddressee(addressee);
                returnRepair.setPackard(packard);
                returnRepair.setExpress(express);
                returnRepair.setNumber(number);
                returnRepair.setReturnCase(returnCase);
                returnRepair.setReturnType(returnType);
                returnRepair.setStatus(status);
                returnRepair.setReturnDate(new Date());
                returnRepair.setCreateDate(sellRepairObj.getCreateDate());
                returnRepair.setUsername(username);
                returnRepair.setName(name);
                returnRepair.setModiDate(new Date());
                returnRepair.setSrid(sellRepairObj.getSrid());
                int rrResult = this.returnRepairMapper.insert(returnRepair);
                if(rrResult != 0){
                    result = GlobalResult.success("提交成功");
                }
            }else{
                result = GlobalResult.errorMsg("修改失败，该信息不存在！");
            }
        }

        return result;
    }

    public GlobalResult query(String detail) {
        log.info("进入ReturnRepairServiceImpl==>>query");
        String respmsg = "数据有误";
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        JSONObject detailJson = JSON.parseObject(detail);
        String rrid = detailJson.getString("rrid");
        String flag = detailJson.getString("flag");
        if(flag.equals("get") && !StringUtils.isEmpty(rrid)){
            result = this.getObject(rrid);
        }
        if(flag.equals("list")){
            result = this.listObjects(detail);
        }
        return result;
    }

    public GlobalResult getObject(String rrid){
        ReturnRepair returnRepairResult = this.returnRepairMapper.selectById(rrid);
        GlobalResult result = GlobalResult.success("查询成功",returnRepairResult);
        return  result;
    }

    public GlobalResult listObjects(String detail){
        JSONObject detailJson = JSON.parseObject(detail);
        String type = detailJson.getString("type");
        String itemno = detailJson.getString("itemno");

        QueryWrapper<ReturnRepair> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(type),"type",type);
        queryWrapper.eq(!StringUtils.isEmpty(itemno),"itemno",itemno);

        List<ReturnRepair> returnRepairResult = this.returnRepairMapper.selectList(queryWrapper);
        GlobalResult result = GlobalResult.success("查询成功",returnRepairResult);
        return  result;
    }
}
