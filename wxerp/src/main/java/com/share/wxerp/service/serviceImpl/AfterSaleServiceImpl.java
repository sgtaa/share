package com.share.wxerp.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.wxerp.common.GlobalResult;
import com.share.wxerp.entity.*;
import com.share.wxerp.mapper.*;
import com.share.wxerp.service.AfterSaleService;
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
 * @Title: AfterSaleServiceImpl
 * @ProjectName wxerp
 * @Description: 售后处理
 * @Author suguotai
 * @Date 2020/3/199:58
 */
@Service
@Transactional
public class AfterSaleServiceImpl implements AfterSaleService {
    @Autowired
    private AfterSaleMapper afterSaleMapper;
    @Autowired
    private InputSellMapper inputSellMapper;
    @Autowired
    private InputWarehouseMapper inputWarehouseMapper;
    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private SellRepairMapper sellRepairMapper;

    String respmsg = "数据有误";
    GlobalResult result = GlobalResult.errorMsg(respmsg);

    private static final Logger log = LoggerFactory.getLogger(ReceivedServiceImpl.class);
    public GlobalResult insert(String detail) {
        log.info("进入sellServiceImpl==>>insert");
        JSONObject detailJson = JSON.parseObject(detail);

        String type = detailJson.getString("type");
        String itemno = detailJson.getString("itemno");
        String product = detailJson.getString("product");
        String sender = detailJson.getString("sender");
        String odd = detailJson.getString("odd");
        String products = detailJson.getString("products");
        int amount = detailJson.getIntValue("amount");
        String part = detailJson.getString("part");
        String parts = detailJson.getString("parts");
        String problems = detailJson.getString("problems");
        String phoneProblems = detailJson.getString("phone_problems");
        String resultProblems = detailJson.getString("result_problems");
        String asResult = detailJson.getString("result");
        String sid = detailJson.getString("sid");

        if(!StringUtils.isEmpty(type) && !StringUtils.isEmpty(itemno) && !StringUtils.isEmpty(sender) && !StringUtils.isEmpty(odd)){
            QueryWrapper<AfterSale> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("type",type);
            queryWrapper.eq("itemno",itemno);
            queryWrapper.eq("sender",sender);
            queryWrapper.eq("odd",odd);
            AfterSale asres = this.afterSaleMapper.selectOne(queryWrapper);
            if(asres == null){
                AfterSale afterSale = new AfterSale();
                String asid = UUID.randomUUID().toString();
                afterSale.setAsid(asid);
                afterSale.setType(type);
                afterSale.setItemno(itemno);
                afterSale.setProduct(product);
                afterSale.setSender(sender);
                afterSale.setOdd(odd);
                afterSale.setProducts(products);
                afterSale.setAmount(amount);
                afterSale.setPart(part);
                afterSale.setParts(parts);
                afterSale.setProblems(problems);
                afterSale.setPhoneProblems(phoneProblems);
                afterSale.setResultProblems(resultProblems);
                afterSale.setResult(asResult);
                afterSale.setAfterDate(new Date());
                afterSale.setCreateDate(new Date());
                afterSale.setSid(sid);
                int asRes = this.afterSaleMapper.insert(afterSale);
                if(asRes != 0){

                    if(asResult.equals("1")){
                        InputSell inputSell = new InputSell();
                        String isid = UUID.randomUUID().toString();
                        inputSell.setIsid(isid);
                        inputSell.setItemno(itemno);
                        inputSell.setProduct(product);
                        inputSell.setPart(part);
                        inputSell.setIntdate(new Date());
                        inputSell.setCreateDate(new Date());
                        inputSell.setAsid(asid);
                        int isResult = this.inputSellMapper.insert(inputSell);
                        if(isResult != 0){
                            //进货入库
                            InputWarehouse inputWarehouse = new InputWarehouse();
                            String iwhid = UUID.randomUUID().toString();
                            inputWarehouse.setIwhid(iwhid);
                            inputWarehouse.setType(type);
                            inputWarehouse.setItemno(itemno);
                            inputWarehouse.setAmount(amount);
                            inputWarehouse.setInputType("0");
                            inputWarehouse.setInputDate(new Date());
                            inputWarehouse.setCreateDate(new Date());
                            inputWarehouse.setAsid(asid);
                            int iwResult = this.inputWarehouseMapper.insert(inputWarehouse);
                            if(iwResult != 0){
                                //库存信息
                                Warehouse warehouse = new Warehouse();
                                String whid = UUID.randomUUID().toString();
                                warehouse.setWhid(whid);
                                warehouse.setType(type);
                                warehouse.setItemno(itemno);
                                warehouse.setAmount(amount);
                                warehouse.setSource("1");
                                warehouse.setBadAmount(0);
                                warehouse.setGoodAmount(amount);
                                warehouse.setWarehouseType("1");
                                warehouse.setWhDate(new Date());
                                warehouse.setCreateDate(new Date());
                                warehouse.setIwhid(iwhid);
                                int whResult = this.warehouseMapper.insert(warehouse);
                            }
                        }

                    }else {
                        SellRepair sellRepair = new SellRepair();
                        String srid = UUID.randomUUID().toString();
                        sellRepair.setSrid(srid);
                        sellRepair.setItemno(itemno);
                        sellRepair.setProduct(product);
                        sellRepair.setResultProblems(resultProblems);
                        sellRepair.setSrDate(new Date());
                        sellRepair.setCreateDate(new Date());
                        sellRepair.setAsid(asid);
                        int srRes = this.sellRepairMapper.insert(sellRepair);

                    }
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

        String asid = detailJson.getString("asid");
        String type = detailJson.getString("type");
        String itemno = detailJson.getString("itemno");
        String product = detailJson.getString("product");
        String sender = detailJson.getString("sender");
        String odd = detailJson.getString("odd");
        String products = detailJson.getString("products");
        int amount = detailJson.getIntValue("amount");
        String part = detailJson.getString("part");
        String parts = detailJson.getString("parts");
        String problems = detailJson.getString("problems");
        String phoneProblems = detailJson.getString("phone_problems");
        String resultProblems = detailJson.getString("result_problems");
        String asResult = detailJson.getString("result");

        if(!StringUtils.isEmpty(asid)){
            AfterSale afterSaleObj = this.afterSaleMapper.selectById(asid);
            if(afterSaleObj != null){
                AfterSale afterSale = new AfterSale();
                afterSale.setAsid(afterSaleObj.getAsid());
                afterSale.setType(type);
                afterSale.setItemno(itemno);
                afterSale.setProduct(product);
                afterSale.setSender(sender);
                afterSale.setOdd(odd);
                afterSale.setProducts(products);
                afterSale.setAmount(amount);
                afterSale.setPart(part);
                afterSale.setParts(parts);
                afterSale.setProblems(problems);
                afterSale.setPhoneProblems(phoneProblems);
                afterSale.setResultProblems(resultProblems);
                afterSale.setResult(asResult);
                afterSale.setAfterDate(afterSaleObj.getAfterDate());
                afterSale.setCreateDate(afterSaleObj.getCreateDate());
                afterSale.setSid(afterSaleObj.getSid());
                int asRes = this.afterSaleMapper.updateById(afterSale);
                if(asRes != 0){
                    if(asResult.equals("1")){
                        QueryWrapper<InputSell> queryInputSell = new QueryWrapper<>();
                        queryInputSell.eq("type",type);
                        queryInputSell.eq("itemno",itemno);
                        queryInputSell.eq("create_date",afterSaleObj.getCreateDate());
                        InputSell inputSellObj = this.inputSellMapper.selectOne(queryInputSell);
                        if(inputSellObj !=null ){
                            InputSell inputSell = new InputSell();
                            inputSell.setIsid(inputSellObj.getIsid());
                            inputSell.setItemno(itemno);
                            inputSell.setProduct(product);
                            inputSell.setPart(part);
                            inputSell.setIntdate(inputSellObj.getIntdate());
                            inputSell.setCreateDate(inputSellObj.getCreateDate());
                            inputSell.setAsid(afterSaleObj.getAsid());
                            int isres = this.inputSellMapper.updateById(inputSell);
                        }
                    }else {
                        QueryWrapper<SellRepair> querySellRepair = new QueryWrapper<>();
                        querySellRepair.eq("type",type);
                        querySellRepair.eq("itemno",itemno);
                        querySellRepair.eq("create_date",afterSaleObj.getCreateDate());
                        SellRepair sellRepairObj = this.sellRepairMapper.selectOne(querySellRepair);
                        if(sellRepairObj !=null ){
                            SellRepair sellRepair = new SellRepair();
                            sellRepair.setSrid(sellRepairObj.getSrid());
                            sellRepair.setItemno(itemno);
                            sellRepair.setProduct(product);
                            sellRepair.setResultProblems(resultProblems);
                            sellRepair.setSrDate(sellRepairObj.getSrDate());
                            sellRepair.setCreateDate(sellRepairObj.getCreateDate());
                            sellRepair.setAsid(afterSaleObj.getAsid());
                            int srRes = this.sellRepairMapper.updateById(sellRepair);
                        }
                    }
                    result = GlobalResult.success("修改成功");
                }

            }else {
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
        String asid = detailJson.getString("asid");

        if(!StringUtils.isEmpty(asid)) {
            AfterSale afterSaleObj = this.afterSaleMapper.selectById(asid);
            if(afterSaleObj != null){
                if(afterSaleObj.getResult().equals("1")){
                    QueryWrapper<InputSell> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("asid",afterSaleObj.getAsid());
                    int isRes = this.inputSellMapper.delete(queryWrapper);
                }else {
                    QueryWrapper<SellRepair> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("asid",afterSaleObj.getAsid());
                    int srRes = this.sellRepairMapper.delete(queryWrapper);
                }
                int sellRepairResult = this.afterSaleMapper.deleteById(asid);
                if(sellRepairResult != 0){
                    result = GlobalResult.success("删除成功");
                }
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
        String asid = detailJson.getString("asid");
        String flag = detailJson.getString("flag");
        if(flag.equals("get") && !StringUtils.isEmpty(asid)){
            result = getObject(asid);
        }
        if(flag.equals("list")){
            result = listObjects(detail);
        }
        return result;
    }

    public GlobalResult getObject(String asid){
        AfterSale afterSaleResult = this.afterSaleMapper.selectById(asid);
        GlobalResult result = GlobalResult.success("查询成功",afterSaleResult);
        return  result;
    }

    public GlobalResult listObjects(String detail){
        JSONObject detailJson = JSON.parseObject(detail);
        String type = detailJson.getString("type");
        String itemno = detailJson.getString("itemno");

        QueryWrapper<AfterSale> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(type),"type",type);
        queryWrapper.eq(!StringUtils.isEmpty(itemno),"itemno",itemno);

        List<AfterSale> afterSaleResult = this.afterSaleMapper.selectList(queryWrapper);
        GlobalResult result = GlobalResult.success("查询成功",afterSaleResult);
        return  result;
    }
}
