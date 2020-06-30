package com.share.wxerp.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.wxerp.common.GlobalResult;
import com.share.wxerp.entity.*;
import com.share.wxerp.mapper.*;
import com.share.wxerp.service.ReceivedService;
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
 * @Title: ReceivedServiceImpl
 * @ProjectName wxerp
 * @Description: 进货
 * @Author suguotai
 * @Date 2020/3/199:53
 */
@Service
@Transactional
public class ReceivedServiceImpl implements ReceivedService {
    @Autowired
    private ReceivedMapper receivedMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ReturnRepairMapper returnRepairMapper;
    @Autowired
    private InputWarehouseMapper inputWarehouseMapper;
    @Autowired
    private WarehouseMapper warehouseMapper;

    private static final Logger log = LoggerFactory.getLogger(ReceivedServiceImpl.class);

    public GlobalResult insert(String detail){
        log.info("进入ReceivedServiceImpl");
        String respmsg = "数据有误";
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        JSONObject detailJson = JSON.parseObject(detail);

        String type = detailJson.getString("type");
        String itemno = detailJson.getString("itemno");
        int amount = detailJson.getIntValue("amount");
        int goodAmount = detailJson.getIntValue("goodAmount");
        int badAmount = detailJson.getIntValue("badAmount");
        String principle = detailJson.getString("principle");
        String inspector = detailJson.getString("inspector");
        int cost = detailJson.getIntValue("cost");
        int sales = detailJson.getIntValue("sales");
        int retail = detailJson.getIntValue("retail");
        int wholesale = detailJson.getIntValue("wholesale");

        if(!StringUtils.isEmpty(type) && !StringUtils.isEmpty(itemno)){
            QueryWrapper<Received> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("type",type);
            queryWrapper.eq("itemno",itemno);
            Received receivedObj = this.receivedMapper.selectOne(queryWrapper);

            if(receivedObj == null){
                //进货信息
                Received received = new Received();
                String recid = UUID.randomUUID().toString();
                received.setRecid(recid);
                received.setType(type);
                received.setItemno(itemno);
                received.setAmount(amount);
                received.setGoodAmount(goodAmount);
                received.setBadAmount(badAmount);
                received.setSource("1");
                received.setPrinciple(principle);
                received.setInspector(inspector);
                received.setCost(cost);
                received.setSales(sales);
                received.setRetail(retail);
                received.setWholesale(wholesale);
                received.setReceivedDate(new Date());
                received.setCreateDate(new Date());
                int recresult = this.receivedMapper.insert(received);
                //商品SKU
                if(recresult != 0){
                    Product product = new Product();
                    String prodid = UUID.randomUUID().toString();
                    product.setProdid(prodid);
                    product.setType(type);
                    product.setItemno(itemno);
                    product.setAmount(amount);
                    product.setSource("1");
                    product.setCost(cost);
                    product.setSales(sales);
                    product.setRetail(retail);
                    product.setWholesale(wholesale);
                    product.setCreateDate(new Date());
                    product.setRecid(recid);
                    int proresult = this.productMapper.insert(product);

                    if(proresult != 0){
                        if(badAmount > 0){
                            //返修入库
                            ReturnRepair returnRepair = new ReturnRepair();
                            String rrid = UUID.randomUUID().toString();
                            returnRepair.setRrid(rrid);
                            returnRepair.setType(type);
                            returnRepair.setItemno(itemno);
                            returnRepair.setAmount(badAmount);
                            returnRepair.setReturnType("0");
                            returnRepair.setStatus("0");
                            returnRepair.setCreateDate(new Date());
                            returnRepair.setRecid(recid);
                            int rrResult = this.returnRepairMapper.insert(returnRepair);
                        }
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
                        inputWarehouse.setRecid(recid);
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
                            warehouse.setBadAmount(badAmount);
                            warehouse.setGoodAmount(goodAmount);
                            warehouse.setWarehouseType("0");
                            warehouse.setWhDate(new Date());
                            warehouse.setCreateDate(new Date());
                            warehouse.setIwhid(iwhid);
                            int whResult = this.warehouseMapper.insert(warehouse);
                            if(whResult != 0){
                                result = GlobalResult.success("添加成功");
                            }
                        }
                    }
                }

            }else {
                result = GlobalResult.resubmit("该信息已添加，请不要再提交！");
            }
        }

        return result;
    }

    public GlobalResult update(String detail) {
        log.info("进入ReceivedServiceImpl==>>update");
        String respmsg = "数据有误";
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        JSONObject detailJson = JSON.parseObject(detail);

        String name = detailJson.getString("name");
        String username = detailJson.getString("username");
        String recid = detailJson.getString("recid");
        String type = detailJson.getString("type");
        String itemno = detailJson.getString("itemno");
        int amount = detailJson.getIntValue("amount");
        int goodAmount = detailJson.getIntValue("goodAmount");
        int badAmount = detailJson.getIntValue("badAmount");
        String principle = detailJson.getString("principle");
        String inspector = detailJson.getString("inspector");
        int cost = detailJson.getIntValue("cost");
        int sales = detailJson.getIntValue("sales");
        int retail = detailJson.getIntValue("retail");
        int wholesale = detailJson.getIntValue("wholesale");

        if(!StringUtils.isEmpty(recid)){
            Received receivedObj = this.receivedMapper.selectById(recid);
            if(receivedObj != null){
                //进货信息
                Received received = new Received();
                received.setRecid(receivedObj.getRecid());
                received.setType(type);
                received.setItemno(itemno);
                received.setAmount(amount);
                received.setGoodAmount(goodAmount);
                received.setBadAmount(badAmount);
                received.setSource(receivedObj.getSource());
                received.setPrinciple(principle);
                received.setInspector(inspector);
                received.setCost(cost);
                received.setSales(sales);
                received.setRetail(retail);
                received.setWholesale(wholesale);
                received.setReceivedDate(receivedObj.getReceivedDate());
                received.setCreateDate(receivedObj.getCreateDate());
                received.setName(name);
                received.setUsername(username);
                received.setModiTime(new Date());
                int recResult = this.receivedMapper.updateById(received);
                //商品SKU
                if(recResult != 0){
                    QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("type",type);
                    queryWrapper.eq("itemno",itemno);
                    queryWrapper.eq("create_date",received.getCreateDate());
                    Product productObj = this.productMapper.selectOne(queryWrapper);
                    if(productObj != null){
                        Product product = new Product();
                        product.setProdid(productObj.getProdid());
                        product.setType(type);
                        product.setItemno(itemno);
                        product.setAmount(amount);
                        product.setSource(productObj.getSource());
                        product.setCost(cost);
                        product.setSales(sales);
                        product.setRetail(retail);
                        product.setWholesale(wholesale);
                        product.setCreateDate(productObj.getCreateDate());
                        product.setRecid(receivedObj.getRecid());
                        int proResult = this.productMapper.updateById(product);
                        if(proResult != 0){
                            if(badAmount > 0){
                                //返修入库
                                QueryWrapper<ReturnRepair> queryReturnRepair = new QueryWrapper<>();
                                queryReturnRepair.eq("type",type);
                                queryReturnRepair.eq("itemno",itemno);
                                queryReturnRepair.eq("create_date",received.getCreateDate());
                                ReturnRepair returnRepairObj = this.returnRepairMapper.selectOne(queryReturnRepair);
                                if(returnRepairObj != null){
                                    ReturnRepair returnRepair = new ReturnRepair();
                                    returnRepair.setRrid(returnRepairObj.getRrid());
                                    returnRepair.setType(type);
                                    returnRepair.setItemno(itemno);
                                    returnRepair.setAmount(badAmount);
                                    returnRepair.setReturnType(returnRepairObj.getReturnType());
                                    returnRepair.setStatus(returnRepairObj.getStatus());
                                    returnRepair.setCreateDate(returnRepairObj.getCreateDate());
                                    returnRepair.setRecid(receivedObj.getRecid());
                                    int rrResult = this.returnRepairMapper.updateById(returnRepair);
                                }
                            }
                            //进货入库
                            QueryWrapper<InputWarehouse> queryinputWarehouse = new QueryWrapper<>();
                            queryinputWarehouse.eq("type",type);
                            queryinputWarehouse.eq("itemno",itemno);
                            queryinputWarehouse.eq("create_date",received.getCreateDate());
                            InputWarehouse inputWarehouseObj = this.inputWarehouseMapper.selectOne(queryinputWarehouse);
                            if(inputWarehouseObj != null){
                                InputWarehouse inputWarehouse = new InputWarehouse();
                                inputWarehouse.setIwhid(inputWarehouseObj.getIwhid());
                                inputWarehouse.setType(type);
                                inputWarehouse.setItemno(itemno);
                                inputWarehouse.setAmount(amount);
                                inputWarehouse.setInputType(inputWarehouseObj.getInputType());
                                inputWarehouse.setInputDate(inputWarehouseObj.getInputDate());
                                inputWarehouse.setCreateDate(inputWarehouseObj.getCreateDate());
                                inputWarehouse.setRecid(receivedObj.getRecid());
                                int iwResult = this.inputWarehouseMapper.updateById(inputWarehouse);
                                if(iwResult != 0){
                                    //库存信息
                                    QueryWrapper<Warehouse> queryWarehouse = new QueryWrapper<>();
                                    queryWarehouse.eq("type",type);
                                    queryWarehouse.eq("itemno",itemno);
                                    queryWarehouse.eq("create_date",received.getCreateDate());
                                    Warehouse warehouseObj = this.warehouseMapper.selectOne(queryWarehouse);
                                    if(warehouseObj != null){
                                        Warehouse warehouse = new Warehouse();
                                        warehouse.setWhid(warehouseObj.getWhid());
                                        warehouse.setType(type);
                                        warehouse.setItemno(itemno);
                                        warehouse.setAmount(amount);
                                        warehouse.setSource(warehouseObj.getSource());
                                        warehouse.setBadAmount(badAmount);
                                        warehouse.setGoodAmount(goodAmount);
                                        warehouse.setWarehouseType(warehouseObj.getWarehouseType());
                                        warehouse.setWhDate(warehouseObj.getWhDate());
                                        warehouse.setCreateDate(warehouseObj.getCreateDate());
                                        warehouse.setIwhid(inputWarehouseObj.getIwhid());
                                        int whResult = this.warehouseMapper.updateById(warehouse);
                                        if(whResult != 0){
                                            result = GlobalResult.success("添加成功");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }else {
                result = GlobalResult.errorMsg("修改失败，该信息不存在！");
            }
        }

        return result;
    }

    public GlobalResult delete(String detail) {
        log.info("进入ReceivedServiceImpl==>>delete");
        String respmsg = "数据有误";
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        JSONObject detailJson = JSON.parseObject(detail);
        String recid = detailJson.getString("recid");

        if(!StringUtils.isEmpty(recid)) {
            Received receivedObj = this.receivedMapper.selectById(recid);
            if(receivedObj != null){
                QueryWrapper<Product> queryProduct = new QueryWrapper<>();
                queryProduct.eq("recid",receivedObj.getRecid());
                int proResult = this.productMapper.delete(queryProduct);

                if(receivedObj.getBadAmount() > 0){
                    QueryWrapper<ReturnRepair> queryReturnRepair = new QueryWrapper<>();
                    queryReturnRepair.eq("recid",receivedObj.getRecid());
                    int rrResult = this.returnRepairMapper.delete(queryReturnRepair);
                }

                QueryWrapper<InputWarehouse> queryInputWarehouse = new QueryWrapper<>();
                queryInputWarehouse.eq("recid",receivedObj.getRecid());
                InputWarehouse inputWarehouseObj = this.inputWarehouseMapper.selectOne(queryInputWarehouse);
                if(inputWarehouseObj != null){
                    QueryWrapper<Warehouse> queryWarehouse = new QueryWrapper<>();
                    queryWarehouse.eq("iwhid",inputWarehouseObj.getIwhid());
                    int whResult = this.warehouseMapper.delete(queryWarehouse);
                    int iwResult = this.inputWarehouseMapper.deleteById(inputWarehouseObj.getIwhid());
                }
                int receivedResult = this.receivedMapper.deleteById(receivedObj.getRecid());
                if(receivedResult != 0){
                    result = GlobalResult.success("删除成功");
                }
            }else{
                result = GlobalResult.errorMsg("删除失败，该信息不存在！");
            }
        }

        return result;
    }

    public GlobalResult query(String detail) {
        log.info("进入ReceivedServiceImpl==>>query");
        String respmsg = "数据有误";
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        JSONObject detailJson = JSON.parseObject(detail);
        String recid = detailJson.getString("recid");
        String flag = detailJson.getString("flag");
        if(flag.equals("get") && !StringUtils.isEmpty(recid)){
            result = this.getObject(recid);
        }
        if(flag.equals("list")){
            result = this.listObjects(detail);
        }
        return result;
    }

    public GlobalResult getObject(String recid){
        Received recResult = this.receivedMapper.selectById(recid);
        GlobalResult result = GlobalResult.success("查询成功",recResult);
        return  result;
    }

    public GlobalResult listObjects(String detail){
        JSONObject detailJson = JSON.parseObject(detail);
        String type = detailJson.getString("type");
        String itemno = detailJson.getString("itemno");

        QueryWrapper<Received> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(type),"type",type);
        queryWrapper.eq(!StringUtils.isEmpty(itemno),"itemno",itemno);

        List<Received> sellResult = this.receivedMapper.selectList(queryWrapper);
        GlobalResult result = GlobalResult.success("查询成功",sellResult);
        return  result;
    }
}
