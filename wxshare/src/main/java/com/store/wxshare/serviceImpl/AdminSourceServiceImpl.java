package com.store.wxshare.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.store.wxshare.common.Approach;
import com.store.wxshare.common.GlobalResult;
import com.store.wxshare.entity.Resource;
import com.store.wxshare.mapper.ClassifyMapper;
import com.store.wxshare.mapper.ResourceMapper;
import com.store.wxshare.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author suguotai
 * @Description //TODO 管理资源
 * @Date
 * @Param
 * @return
 **/
@Service
@Transactional
public class AdminSourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private ClassifyMapper classifyMapper;
    /**
     * 资源操作
     * @param operflag 操作类型
     * @param detail   详细数据
     * @param signature  安全处理
     * @return
     */
    public GlobalResult resource_control(String operflag, String detail, String signature) {

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

            //respData = query(detail,signature);
            System.out.println("operflag="+ operflag + "查询成功");
        }
        if (operflag.equals(Approach.OPERFLAG_CONVERT)){

            //respData = convert(detail,signature);
            System.out.println("operflag="+ operflag + "兑换成功");
        }
        if (operflag.equals(Approach.OPERFLAG_SUBMIT)){

            //respData = submit(detail,signature);
            System.out.println("operflag="+ operflag + "提交成功");
        }
        if (operflag.equals(Approach.OPERFLAG_CLASSIFY)){

            respData = classify(detail,signature);
            System.out.println("operflag="+ operflag + "查询分类");
        }
        if (operflag.equals(Approach.OPERFLAG_SKYDRIVE)){

            respData = skyDrive(detail,signature);
            System.out.println("operflag="+ operflag + "处理网盘");
        }
        GlobalResult result = GlobalResult.build(200, (String) respData.get("msg"), respData.get("data"));
        return result;
    }

    private Map<String,Object> skyDrive(String detail, String signature) {
        System.out.println("-----------------------进入管理网盘操作方法---------------------");
        String msg = null;
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);
        JSONObject detailesJson = (JSONObject) detailJson.get("detailes");
        String operateflag = detailesJson.getString("operateflag");
        //添加
        if(operateflag.equals(Approach.OPERFLAG_NEW)){

        }
        //修改
        if (operateflag.equals(Approach.OPERFLAG_MODIFY)){

        }
        //删除
        if(operateflag.equals(Approach.OPERFLAG_DELETE)){

        }
        //查询
        if (operateflag.equals(Approach.OPERFLAG_QUERY)){

        }
        return  map;
    }

    private Map<String,Object> classify(String detail, String signature) {
       /* System.out.println("-----------------------进入分类查询方法---------------------");
        String msg = null;
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);
        JSONObject detailesJson = (JSONObject) detailJson.get("detailes");
        System.out.println("detail:" + detailJson.getString("date") + "++" + detailJson.getString("userid") + "++" + detailJson.getString("signature"));

        String operateflag = detailJson.getString("operflag");
        String classid = detailesJson.getString("classid");
        String classname = detailesJson.getString("classname");
        String userid = detailesJson.getString("userid");
        Integer layer = Integer.valueOf(detailesJson.getString("layer"));
        String parent = detailesJson.getString("parent");
        Integer rank = Integer.valueOf(detailesJson.getString("rank"));

        List<Classify> listClass = this.classifyMapper.selectListByLayer();
        Map<String,Object> mapObj = new HashMap<String,Object>();
        List<Object> listObj = new ArrayList<Object>();
        if(listClass != null){
            for(Classify classify : listClass){
                mapObj.put(classify.getRank().toString(),classify.getClassName().toString());
                mapObj.put(classify.getClassName().toString(),classify.getClassid().toString());
                //二级分类以上的分类
                String parentid = classify.getClassid().toString();
                System.out.println("------parentid=="+parentid);
                if(parentid != null){
                    List<Classify> listClass2 = this.classifyMapper.selectListByParent(parentid);
                    Map<String,Object> mapObj2 = new HashMap<String,Object>();
                    List<Object> listObj2 = new ArrayList<Object>();
                    if(listClass2 != null){
                        for(Classify classify1 : listClass2){
                            mapObj2.put(classify1.getRank().toString(),classify1.getClassName().toString());
                            mapObj2.put(classify1.getClassName().toString(),classify1.getClassid().toString());
                        }
                        listObj2.add(mapObj2);
                        map1.put(classify.getClassName().toString(),listObj2);
                    }
                }
            }
            listObj.add(mapObj);
            map1.put("firstLevel",listObj);
            map.put("data",map1);
            msg="查询成功！";
        }else{
            msg="查询不到该信息！";
        }
        map.put("msg",msg);*/
        return null;
    }

    private Map<String,Object> delete(String detail, String signature) {
        System.out.println("-----------------------进入管理资源删除操作方法---------------------");
        String msg = null;
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);

        return  map;
    }

    private Map<String,Object> modify(String detail, String signature) {
        System.out.println("-----------------------进入管理员资源修改操作方法---------------------");
        String msg = null;
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);

        return  map;
    }

    /**
     * 资源兑换
     * @param detail
     * @param signature
     * @return
     */
    private Map<String,Object> convert(String detail, String signature) {
        System.out.println("-----------------------进入管理员资源兑换操作方法---------------------");
        String msg = null;
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);

        return  map;
    }

    /**
     * 资源提交
     * @param detail
     * @param signature
     * @return
     */
    private Map<String,Object> submit(String detail, String signature) {
        System.out.println("-----------------------进入管理员资源提交操作方法---------------------");
        String msg = null;
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);
        //添加
        //修改
        //删除
        //查询
        return  map;
    }

    /**
     *资源新增
     * @param detail
     * @param signature
     * @return
     */
    private Map<String,Object> increase(String detail, String signature) {
        System.out.println("-----------------------进入管理员资源新增操作方法---------------------");
        String msg = null;
        Resource resource;
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);
        JSONObject detailesJson = (JSONObject) detailJson.get("detailes");
        System.out.println("detail:" + detailJson.getString("date") + "++" + detailJson.getString("userid") + "++" + detailJson.getString("signature"));
        String ename = detailesJson.getString("ename");
        String classid = detailesJson.getString("classid");
        Integer integral = Integer.valueOf(detailesJson.getString("integral"));
        String address = detailesJson.getString("address");
        String pictureaddress = detailesJson.getString("pictureaddress");
        String summary = detailesJson.getString("summary");
        String descriped = detailesJson.getString("descriped");

        QueryWrapper<Resource> queryWrapper = new QueryWrapper<Resource>();
        queryWrapper.eq(ename != null,"ename",ename);
        queryWrapper.eq(address != null,"address",address);
        resource = this.resourceMapper.selectOne(queryWrapper);
        String resourceid = UUID.randomUUID().toString();
        if(resource == null){
            resource = new Resource();
            resource.setResourceid(resourceid);
            resource.setClassid(classid);
            resource.setEname(ename);
            resource.setIntegral(integral);
            resource.setAddress(address);
            resource.setPictureAddress(pictureaddress);
            resource.setSummary(summary);
            resource.setDescribed(descriped);
            resource.setState("0");
            resource.setCreateDate(new Date());
            resource.setModiDate(new Date());

            int retu = this.resourceMapper.insert(resource);
            if(retu != 0){
                map.put("msg","资源添加成功！");
            }else {
                map.put("msg","资源添加失败！");
            }
        }else {
            map.put("msg","该资源已添加！");
        }
        //map.put("data", data);
        return map;
    }

    /**
     * 查询
     */
    public Map<String, Object> query(String detail, String signature) {
        System.out.println("-----------------------进入管理员查询操作方法---------------------");
        String msg = null;
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);
        System.out.println("detail:" + detailJson.getString("date") + "++" + detailJson.getString("userid") + "++" + detailJson.getString("signature"));
        String userid = detailJson.getString("userid");
        if (!StringUtils.isEmpty(userid)) {

        }
        map.put("msg", msg);

        return map;
    }

}
