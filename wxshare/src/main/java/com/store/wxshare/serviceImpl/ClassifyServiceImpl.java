package com.store.wxshare.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.store.wxshare.common.Approach;
import com.store.wxshare.common.CharStringUtil;
import com.store.wxshare.common.GlobalResult;
import com.store.wxshare.entity.Classify;
import com.store.wxshare.mapper.ClassifyMapper;
import com.store.wxshare.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @Author suguotai
 * @Description //TODO 分类操作
 * @Date
 * @Param
 * @return
 **/
@Service
@Transactional
public class ClassifyServiceImpl implements ClassifyService {

    @Autowired
    private ClassifyMapper classifyMapper;
    /**
     * 资源操作
     * @param operflag 操作类型
     * @param detail   详细数据
     * @param signature  安全处理
     * @return
     */
    public GlobalResult classify_control(String operflag, String detail, String signature) {

        String msg = null;
        String data = null;
        Map<String, Object> respData = null;
        System.out.println("operflag="+ operflag +"detail="+ detail + " signature" +signature);
        if (operflag.equals(Approach.OPERFLAG_NEW)){

            respData = increase(detail,signature);
            System.out.println("operflag="+ operflag + "新增成功");
        }
        if (operflag.equals(Approach.OPERFLAG_MODIFY)){

            respData =  modify(detail,signature);
            System.out.println("operflag="+ operflag + "修改成功");
        }
        if (operflag.equals(Approach.OPERFLAG_DELETE)){

            respData =  delete(detail,signature);
            System.out.println("operflag="+ operflag + "删除成功");
        }
        if (operflag.equals(Approach.OPERFLAG_QUERY)){

            respData = query(detail,signature);
            System.out.println("operflag="+ operflag + "查询成功");
            System.out.println("respData.get(\"data\").toString()=="+respData.get("data").toString());

        }
        GlobalResult result = GlobalResult.build(200, (String) respData.get("msg"), respData.get("data"));
        return result;
    }

    private Map<String,Object> modify(String detail, String signature) {

        return null;
    }

    private Map<String,Object> delete(String detail, String signature) {
        System.out.println("-----------------------进入分类删除方法---------------------");
        String msg = null;
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);
        JSONObject detailesJson = (JSONObject) detailJson.get("detailes");
        System.out.println("detail:" + detailJson.getString("date") + "++" + detailJson.getString("userid") + "++" + detailJson.getString("signature"));

        String classid = detailesJson.getString("classid");
        String classname = detailesJson.getString("classname");
        String userid = detailesJson.getString("userid");
        Integer layer = Integer.valueOf(detailesJson.getString("layer"));
        String parent = detailesJson.getString("parent");
        Integer rank = Integer.valueOf(detailesJson.getString("rank"));

        QueryWrapper<Classify> queryWrapper = new QueryWrapper<Classify>();
        queryWrapper.eq(!StringUtils.isEmpty(classid),"parent",classid);
        List<Classify> listClassify = this.classifyMapper.selectList(queryWrapper);
        // 从内容上截取路径各类括号("()","（）","{}","[]")内的内容数组
        String listClassUtil = CharStringUtil.getPatternString(listClassify.toString());
        System.out.println("__________listClassify=="+ listClassUtil);
        if (!StringUtils.isEmpty(listClassUtil)){
            List<String> list = new ArrayList<String>();
            for(Classify classify : listClassify){
                list.add(classify.getClassid().toString());
            }
            list.add(classid);
            int listReturn = this.classifyMapper.deleteBatchIds(list);
            if(listReturn != 0){
                msg = "成功删除"+listReturn+"个分类，同时已删除所有子类了！";
            }
        }else{
            int idReturn = this.classifyMapper.deleteById(classid);
            if(idReturn != 0){
                msg = "成功删除"+1+"个分类！";
            }else {
                msg = "没有该数据或该数据已删除！";
            }
        }
        map.put("msg",msg);
        return  map;
    }

    private Map<String,Object> query(String detail, String signature) {
        System.out.println("-----------------------进入分类查询方法---------------------");
        String msg = null;
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);
        JSONObject detailesJson = (JSONObject) detailJson.get("detailes");
        System.out.println("detail:" + detailJson.getString("date") + "++" + detailJson.getString("userid") + "++" + detailJson.getString("signature"));

        /*
        String classid = detailesJson.getString("classid");
        String classname = detailesJson.getString("classname");
        String userid = detailesJson.getString("userid");
        Integer layer = Integer.valueOf(detailesJson.getString("layer"));
        String parent = detailesJson.getString("parent");
        Integer rank = Integer.valueOf(detailesJson.getString("rank"));*/
        map.put("msg",msg);
        return  map;
    }

    private Map<String,Object> increase(String detail, String signature) {
        System.out.println("-----------------------进入分类新增方法---------------------");
        String msg = null;
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);
        JSONObject detailesJson = (JSONObject) detailJson.get("detailes");
        System.out.println("detail:" + detailJson.getString("date") + "++" + detailJson.getString("userid") + "++" + detailJson.getString("signature"));

        String classname = detailesJson.getString("classname");
        String userid = detailesJson.getString("userid");
        Integer layer = Integer.valueOf(detailesJson.getString("layer"));
        String parent = detailesJson.getString("parent");
        Integer rank = Integer.valueOf(detailesJson.getString("rank"));

        Classify classify;
        QueryWrapper<Classify> querywrapper = new  QueryWrapper<Classify>();
        querywrapper.eq(!StringUtils.isEmpty(classname),"classname",classname);
        querywrapper.eq(!StringUtils.isEmpty(layer),"layer",layer);
        classify = this.classifyMapper.selectOne(querywrapper);
        if(classify == null){
            classify = new Classify();
            classify.setClassid(UUID.randomUUID().toString());
            classify.setClassName(classname);
            classify.setUserid(userid);
            classify.setLayer(layer);
            classify.setRank(rank);
            classify.setState(0);
            classify.setCreateDate(new Date());
            classify.setModiDate(new Date());

            if (layer > 1 && !StringUtils.isEmpty(parent)){
                classify.setParent(parent);
            }
            System.out.println("----------------classify=="+classify.toString());
            int retu = this.classifyMapper.insert(classify);
            if (retu != 0){
                msg = "分类添加成功";
            }
        }else{
            msg = "分类已添加";
        }
        map.put("msg",msg);
        return  map;
    }
}
