package com.share.wxerp.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.wxerp.common.GlobalResult;
import com.share.wxerp.entity.AfterSale;
import com.share.wxerp.entity.Received;
import com.share.wxerp.entity.Sell;
import com.share.wxerp.mapper.SellMapper;
import com.share.wxerp.service.SellService;
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
 * @Title: SellServiceImpl
 * @ProjectName wxerp
 * @Description: 销售订单
 * @Author suguotai
 * @Date 2020/3/199:47
 */
@Service
@Transactional
public class SellServiceImpl implements SellService {
    @Autowired
    private SellMapper sellMapper;

    private static final Logger log = LoggerFactory.getLogger(ReceivedServiceImpl.class);
    public GlobalResult insert(String detail) {
        log.info("进入sellServiceImpl==>>insert");
        String respmsg = "数据有误";
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        JSONObject detailJson = JSON.parseObject(detail);

        String type = detailJson.getString("type");
        String itemno = detailJson.getString("itemno");
        String product = detailJson.getString("product");
        int amount = detailJson.getIntValue("amount");
        int sellPrice = detailJson.getIntValue("sellPrice");
        int wholesale = detailJson.getIntValue("wholesale");
        int profit = detailJson.getIntValue("profit");
        String addressee = detailJson.getString("addressee");
        int freight = detailJson.getIntValue("freight");
        String express = detailJson.getString("express");
        String details = detailJson.getString("details");
        String products = detailJson.getString("products");
        String platform = detailJson.getString("platform");
        String saler = detailJson.getString("saler");
        String account = detailJson.getString("account");
        int parts = detailJson.getIntValue("parts");
        String whid = detailJson.getString("whid");

        if(!StringUtils.isEmpty(type) && !StringUtils.isEmpty(itemno) && !StringUtils.isEmpty(saler) && !StringUtils.isEmpty(addressee)) {
            QueryWrapper<Sell> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("type", type);
            queryWrapper.eq("itemno", itemno);
            queryWrapper.eq("saler", saler);
            queryWrapper.eq("addressee",addressee);
            Sell sellObj = this.sellMapper.selectOne(queryWrapper);
            if(sellObj == null){
                Sell sell = new Sell();
                String sid = UUID.randomUUID().toString();
                sell.setSid(sid);
                sell.setType(type);
                sell.setItemno(itemno);
                sell.setProduct(product);
                sell.setSellPrice(sellPrice);
                sell.setWholesale(wholesale);
                sell.setProfit(profit);
                sell.setAddressee(addressee);
                sell.setFreight(freight);
                sell.setExpress(express);
                sell.setDetails(details);
                sell.setProducts(products);
                sell.setAmount(amount);
                sell.setPlatform(platform);
                sell.setSaler(saler);
                sell.setAccount(account);
                sell.setParts(parts);
                sell.setSendDate(new Date());
                sell.setCreateDate(new Date());
                sell.setWhid(whid);
                int sellResult = this.sellMapper.insert(sell);
                if(sellResult != 0){
                    result = GlobalResult.success("添加成功");
                }
            }else {
                result = GlobalResult.resubmit("该信息已添加，请不要再提交！");
            }
        }
        return result;
    }

    public GlobalResult update(String detail) {
        log.info("进入sellServiceImpl==>>update");
        String respmsg = "数据有误";
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        JSONObject detailJson = JSON.parseObject(detail);

        String name = detailJson.getString("name");
        String username = detailJson.getString("username");
        String sid = detailJson.getString("sid");
        String type = detailJson.getString("type");
        String itemno = detailJson.getString("itemno");
        String product = detailJson.getString("product");
        int amount = detailJson.getIntValue("amount");
        int sellPrice = detailJson.getIntValue("sellPrice");
        int wholesale = detailJson.getIntValue("wholesale");
        int profit = detailJson.getIntValue("profit");
        String addressee = detailJson.getString("addressee");
        int freight = detailJson.getIntValue("freight");
        String express = detailJson.getString("express");
        String details = detailJson.getString("details");
        String products = detailJson.getString("products");
        String platform = detailJson.getString("platform");
        String saler = detailJson.getString("saler");
        String account = detailJson.getString("account");
        int parts = detailJson.getIntValue("parts");

        if(!StringUtils.isEmpty(sid)) {
            Sell sellObj = this.sellMapper.selectById(sid);
            if(sellObj != null){
                Sell sell = new Sell();
                sell.setSid(sellObj.getSid());
                sell.setType(type);
                sell.setItemno(itemno);
                sell.setProduct(product);
                sell.setSellPrice(sellPrice);
                sell.setWholesale(wholesale);
                sell.setProfit(profit);
                sell.setAddressee(addressee);
                sell.setFreight(freight);
                sell.setExpress(express);
                sell.setDetails(details);
                sell.setProducts(products);
                sell.setAmount(amount);
                sell.setPlatform(platform);
                sell.setSaler(saler);
                sell.setAccount(account);
                sell.setParts(parts);
                sell.setSendDate(sellObj.getSendDate());
                sell.setCreateDate(sellObj.getCreateDate());
                sell.setModiTime(new Date());
                sell.setName(name);
                sell.setUsername(username);
                sell.setWhid(sellObj.getWhid());
                int sellResult = this.sellMapper.updateById(sell);
                if(sellResult != 0){
                    result = GlobalResult.success("修改成功");
                }
            }else{
                result = GlobalResult.errorMsg("修改失败，该信息不存在！");
            }
        }

        return result;
    }

    public GlobalResult delete(String detail) {
        log.info("进入sellServiceImpl==>>delete");
        String respmsg = "数据有误";
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        JSONObject detailJson = JSON.parseObject(detail);
        String sid = detailJson.getString("sid");

        if(!StringUtils.isEmpty(sid)) {
            int sellResult = this.sellMapper.deleteById(sid);
            if(sellResult != 0){
                result = GlobalResult.success("删除成功");
            }else{
                result = GlobalResult.errorMsg("删除失败，该信息不存在！");
            }
        }

        return result;
    }

    public GlobalResult query(String detail) {
        log.info("进入sellServiceImpl==>>query");
        String respmsg = "数据有误";
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        JSONObject detailJson = JSON.parseObject(detail);

        String sid = detailJson.getString("sid");
        String flag = detailJson.getString("flag");

        if(flag.equals("get") && !StringUtils.isEmpty(sid)){
            result = getObject(sid);
        }
        if(flag.equals("list") && !StringUtils.isEmpty(detail)){
            result = listObjects(detail);
        }
        return result;
    }

    public GlobalResult getObject(String sid){
        Sell sellResult = this.sellMapper.selectById(sid);
        GlobalResult result = GlobalResult.success("查询成功",sellResult);
        return  result;
    }

    public GlobalResult listObjects(String detail){
        JSONObject detailJson = JSON.parseObject(detail);
        String type = detailJson.getString("type");
        String itemno = detailJson.getString("itemno");

        QueryWrapper<Sell> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(type),"type",type);
        queryWrapper.eq(!StringUtils.isEmpty(itemno),"itemno",itemno);

        List<Sell> sellResult = this.sellMapper.selectList(queryWrapper);
        GlobalResult result = GlobalResult.success("查询成功",sellResult);
        return  result;
    }

}
