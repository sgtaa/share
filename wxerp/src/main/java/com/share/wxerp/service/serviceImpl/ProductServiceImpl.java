package com.share.wxerp.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.wxerp.common.GlobalResult;
import com.share.wxerp.entity.Product;
import com.share.wxerp.entity.Warehouse;
import com.share.wxerp.mapper.ProductMapper;
import com.share.wxerp.mapper.WarehouseMapper;
import com.share.wxerp.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Title: ProductServiceImpl
 * @ProjectName wxerp
 * @Description: 商品
 * @Author suguotai
 * @Date 2020/3/199:54
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    public GlobalResult query(String detail) {
        log.info("进入ProductServiceImpl==>>query");
        String respmsg = "数据有误";
        GlobalResult result = GlobalResult.errorMsg(respmsg);
        JSONObject detailJson = JSON.parseObject(detail);
        String prodid = detailJson.getString("prodid");
        String flag = detailJson.getString("flag");
        if(flag.equals("get") && !StringUtils.isEmpty(prodid)){
            result = getObject(prodid);
        }
        if(flag.equals("list")){
            result = listObjects(detail);
        }
        return result;
    }

    public GlobalResult getObject(String prodid){
        Product productResult = this.productMapper.selectById(prodid);
        GlobalResult result = GlobalResult.success("查询成功",productResult);
        return  result;
    }

    public GlobalResult listObjects(String detail){
        JSONObject detailJson = JSON.parseObject(detail);

        String itemno = detailJson.getString("itemno");
        String product = detailJson.getString("product");

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(product),"product",product);
        queryWrapper.eq(!StringUtils.isEmpty(itemno),"itemno",itemno);
        List<Product> productResult = this.productMapper.selectList(queryWrapper);
        GlobalResult result = GlobalResult.success("查询成功",productResult);
        return  result;
    }
}
