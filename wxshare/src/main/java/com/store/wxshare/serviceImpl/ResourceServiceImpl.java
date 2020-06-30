package com.store.wxshare.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.store.wxshare.common.Approach;
import com.store.wxshare.common.GlobalResult;
import com.store.wxshare.entity.*;
import com.store.wxshare.mapper.*;
import com.store.wxshare.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @Author suguotai
 * @Description //TODO 资源操作实现
 * @Date
 * @Param
 * @return
 **/
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private ClassifyMapper classifyMapper;
    @Autowired
    private IntegralMapper integralMapper;
    @Autowired
    private ConvertMapper convertMapper;
    @Autowired
    private SkydriveMapper skydriveMapper;
    @Autowired
    private SubmitMapper submitMapper;
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
            System.out.println("operflag="+ operflag + "修改已提交资源成功");
        }
        if (operflag.equals(Approach.OPERFLAG_DELETE)){

            respData =  delete(detail,signature);
            System.out.println("operflag="+ operflag + "删除已提交资源成功");
        }
        if (operflag.equals(Approach.OPERFLAG_QUERY)){

            respData = query(detail,signature);
            System.out.println("operflag="+ operflag + "查询成功");
        }
        if (operflag.equals(Approach.OPERFLAG_CONVERT)){

            respData = convert(detail,signature);
            System.out.println("operflag="+ operflag + "兑换成功");
        }
        if (operflag.equals(Approach.OPERFLAG_SUBMIT)){

            respData = submit(detail,signature);
            System.out.println("operflag="+ operflag + "提交资源成功");
        }
        if(operflag.equals(Approach.OPERFLAG_SKYDRIVE)){

            respData = skydrive(detail,signature);
            System.out.println("operflag="+ operflag + "网盘地址失效返回码查询成功");
        }
        if(operflag.equals(Approach.OPERFLAG_CLASSIFY)){
            respData = classify(detail,signature);
            System.out.println("operflag="+ operflag + "分类查询成功");
        }
        GlobalResult result = GlobalResult.build(200, (String) respData.get("msg"), respData.get("data"));
        return result;
    }

    private Map<String,Object> skydrive(String detail, String signature) {
        System.out.println("-----------------------进入资源网盘地址失效返回码查询操作方法---------------------");
        String msg = null;
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);
        String resourceid = detailJson.getString("resourceid");
        String userid = detailJson.getString("userid");
        if(!StringUtils.isEmpty(userid) && !StringUtils.isEmpty(resourceid)){
            QueryWrapper<Skydrive> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(resourceid != null,"resourceid",resourceid);
            Skydrive skydrive = this.skydriveMapper.selectOne(queryWrapper);
            if(skydrive != null){
                String code = skydrive.getCode().toString();
                String address = skydrive.getAddress().toString();
                map1.put("code",code);
                map1.put("address",address);
                msg = "成功返回回复码！";
            }
        }else {
            msg = "返回失败，信息错误！";
        }
        map.put("msg",msg);
        map.put("data",map1);
        return  map;
    }

    private Map<String,Object> classify(String detail, String signature) {
        System.out.println("-----------------------进入分类查询方法---------------------");
        String msg = null;
        String parentid = null;
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);
        System.out.println("detail:" + detailJson.getString("date") + "++" + detailJson.getString("userid") + "++" + detailJson.getString("signature"));

        String classid = detailJson.getString("classid");
        //用户查询二级分类以上的分类
        if(!StringUtils.isEmpty(classid)){
            parentid = classid;
        }else {
            QueryWrapper<Classify> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("layer",1);
            queryWrapper.orderBy(true,true,"rank");
            //过滤字段
            queryWrapper.select("classid","classname","rank","layer");
            List<Map<String,Object>> listMap = this.classifyMapper.selectMaps(queryWrapper);

            parentid = listMap.get(0).get("classid").toString();
            map1.put("firstLevel",listMap);
        }
        if(!StringUtils.isEmpty(parentid)){
            QueryWrapper<Classify> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("parent",parentid);
            queryWrapper1.orderBy(true,true,"rank");
            //过滤字段
            queryWrapper1.select("classid","classname","rank","layer");
            List<Map<String,Object>> listMap1 = this.classifyMapper.selectMaps(queryWrapper1);
            map1.put("classList",listMap1);
        }
        map.put("data",map1);
        msg="查询成功！";
        map.put("msg",msg);
        return map;
    }

    private Map<String,Object> delete(String detail, String signature) {
        System.out.println("-----------------------进入资源删除操作方法---------------------");
        String msg = null;
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);
        System.out.println("detail:" + detailJson.getString("submitid") + "++" + detailJson.getString("userid") + "++" + detailJson.getString("signature"));

        String userid = detailJson.getString("userid");
        String submitid = detailJson.getString("submitid");
        if(!StringUtils.isEmpty(userid) && !StringUtils.isEmpty(submitid)){
            QueryWrapper<Submit> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("submitid",submitid);
            queryWrapper.eq("userid",userid);
            int subreturn = this.submitMapper.delete(queryWrapper);
            if (subreturn != 0){
                msg = "删除成功！";
            }else {
                msg = "删除已提交失败，信息错误！";
            }

        }else {
            msg = "删除失败，信息错误！";
        }
        map.put("msg",msg);

        return  map;
    }

    private Map<String,Object> modify(String detail, String signature) {
        System.out.println("-----------------------进入已提交资源修改操作方法---------------------");
        String msg = null;
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);
        System.out.println("detail:" + detailJson.getString("detail") + "++" + detailJson.getString("userid") + "++" + detailJson.getString("signature"));

        String userid = detailJson.getString("userid");
        String submitid = detailJson.getString("submitid");
        String ename = detailJson.getString("ename");
        String described = detailJson.getString("described");
        String summary = detailJson.getString("summary");
        String address = detailJson.getString("address");
        String remark = detailJson.getString("remark");
        if(!StringUtils.isEmpty(userid) && !StringUtils.isEmpty(submitid)){
            QueryWrapper<Submit> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("submitid",submitid);
            queryWrapper.eq("userid",userid);
            Submit submit = this.submitMapper.selectOne(queryWrapper);
            submit.setEname(ename);
            submit.setAddress(address);
            submit.setSummary(summary);
            submit.setDescribed(described);
            submit.setRemark(remark);
            submit.setModiDate(new Date());
            int subreturn = this.submitMapper.updateById(submit);
            if (subreturn != 0){
                msg = "修改成功！";
            }else {
                msg = "修过已提交失败，信息错误！";
            }

        }else {
            msg = "修改失败，信息错误！";
        }
        map.put("msg",msg);

        return  map;
    }

    /**
     * 资源兑换
     * @param detail
     * @param signature
     * @return
     */
    private Map<String,Object> convert(String detail, String signature) {
        System.out.println("-----------------------进入资源兑换操作方法---------------------");
        String msg = null;
        int gradesum = 0;
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);
        String resourceid = detailJson.getString("resourceid");
        String userid = detailJson.getString("userid");
        int grade = Integer.parseInt(detailJson.getString("integral"));
        if(!StringUtils.isEmpty(userid) && !StringUtils.isEmpty(resourceid)){
            QueryWrapper<Convert> queryWrapper2 = new QueryWrapper<Convert>();
            queryWrapper2.eq( "resourceid",resourceid);
            Convert convert = this.convertMapper.selectOne(queryWrapper2);
            if(convert == null){
                QueryWrapper<Integral> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq(userid != null,"userid",userid);
                Integral integerl = this.integralMapper.selectOne(queryWrapper);
                if(integerl != null){
                    gradesum = integerl.getGrade().intValue();
                    if(gradesum != 0 && gradesum > grade ){
                        int residue = gradesum - grade;
                        integerl.setGrade(residue);
                        int  intreturn = this.integralMapper.updateById(integerl);
                        if(intreturn != 0 ){
                            QueryWrapper<Resource> queryWrapper1 = new QueryWrapper<>();
                            queryWrapper1.eq(resourceid != null,"resourceid",resourceid);
                            Resource resource = this.resourceMapper.selectOne(queryWrapper1);

                            convert = new Convert();
                            String convertid = UUID.randomUUID().toString();
                            String address = resource.getAddress().toString();
                            convert.setResourceid(resourceid);
                            convert.setConvertid(convertid);
                            convert.setClassid(resource.getClassid());
                            convert.setUserid(userid);
                            convert.setEname(resource.getEname());
                            convert.setIntegral(grade);
                            convert.setAddress(address);
                            convert.setSummary(resource.getSummary());
                            convert.setPictureAddress(resource.getPictureAddress());
                            convert.setDescribed(resource.getDescribed());
                            convert.setState("0");
                            convert.setCreateDate(new Date());
                            convert.setModiDate(new Date());

                            int conretun = this.convertMapper.insert(convert);
                            if (conretun != 0){
                                msg = "兑换成功，剩余"+ residue +"积分";
                                map1.put("convertid",convertid);
                                map1.put("address",address);
                                map1.put("grade",residue);
                                map1.put("converted",1);
                            }

                        }
                    }else {
                        msg = "兑换失败，积分不足！";
                    }
                }else {
                    msg = "积分信息为空，请先领取积分再来兑换！";
                }
            }else {
                msg = "该资源已兑换！";
            }
        }else {
            msg = "兑换失败，信息错误！";
        }
        map.put("msg",msg);
        map.put("data",map1);
        return  map;
    }

    /**
     * 资源提交
     * @param detail
     * @param signature
     * @return
     */
    private Map<String,Object> submit(String detail, String signature) {
        System.out.println("-----------------------进入资源提交操作方法---------------------");
        String msg = null;
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);
        System.out.println("detail:" + detailJson.getString("detailes") + "++" + detailJson.getString("userid") + "++" + detailJson.getString("signature"));

        String userid = detailJson.getString("userid");
        String ename = detailJson.getString("ename");
        String described = detailJson.getString("described");
        String summary = detailJson.getString("summary");
        String address = detailJson.getString("address");
        String remark = detailJson.getString("remark");
        if(!StringUtils.isEmpty(userid)){
            QueryWrapper<Submit> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("described",described);
            queryWrapper1.eq("address",address);
            Submit submit = this.submitMapper.selectOne(queryWrapper1);
            if(submit == null){
                submit = new Submit();
                String submitid = UUID.randomUUID().toString();

                submit.setSubmitid(submitid);
                submit.setUserid(userid);
                submit.setEname(ename);
                submit.setAddress(address);
                submit.setSummary(summary);
                submit.setDescribed(described);
                submit.setRemark(remark);
                submit.setState("0");
                submit.setCreateDate(new Date());
                submit.setModiDate(new Date());
                int subreturn = this.submitMapper.insert(submit);
                if(subreturn != 0){
                    QueryWrapper<Integral> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq(userid != null,"userid",userid);
                    Integral integral = this.integralMapper.selectOne(queryWrapper);
                    if(integral != null){
                        int grade = integral.getGrade();
                        int add = grade + 20;
                        integral.setGrade(add);
                        int intreturn = this.integralMapper.updateById(integral);
                        if(intreturn  != 0){
                            msg = "提交成功,奖励+20积分，待审核！";
                            map1.put("submit","成功");
                        }
                    }
                }else {
                    msg = "提交失败，请从新检查再提交！";
                }
            }else {
                msg = "该资源已提交！";
            }

        }else {
            msg = "提交失败，信息错误！";
        }
        map.put("data",map1);
        map.put("msg",msg);
        return  map;
    }

    /**
     *资源新增
     * @param detail
     * @param signature
     * @return
     */
    private Map<String,Object> increase(String detail, String signature) {
        System.out.println("-----------------------进入资源新增操作方法---------------------");
        String msg = null;
        Resource resource;
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);
        JSONObject detailesJson = (JSONObject) detailJson.get("detailes");
        System.out.println("detail:" + detailJson.getString("date") + "++" + detailJson.getString("userid") + "++" + detailJson.getString("signature"));
        String ename = detailesJson.getString("ename");
        String classid = detailesJson.getString("classid");
        Integer integral = detailesJson.getInteger("integral");
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
                map.put("msg","添加成功！");
            }else {
                map.put("msg","添加失败！");
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
        System.out.println("-----------------------进入用户资源查询操作方法---------------------");
        String msg = null;
        IPage<Map<String, Object>> resultMap = null;
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        JSONObject detailJson = JSON.parseObject(detail);
        System.out.println("detail:" + detailJson.getString("refer") + "++" + detailJson.getString("userid") + "++" + detailJson.getString("signature"));
        String userid = detailJson.getString("userid");
        String classid = detailJson.getString("classid");
        String ename = detailJson.getString("ename");
        String resourceid = detailJson.getString("resourceid");
        String refer = detailJson.getString("refer");
        //根据classid来查
        if (!StringUtils.isEmpty(classid)) {
            QueryWrapper<Resource> queryWrapper = new QueryWrapper<Resource>();
            queryWrapper.eq(classid != null,"classid",classid);
            queryWrapper.in("state",0,2);

            //过滤字段
            queryWrapper.select("resourceid","ename","picture_address","integral","described");
            Page<Resource> page = new Page<Resource>(1,2);
            resultMap = this.resourceMapper.selectMapsPage(page,queryWrapper);
            if(resultMap != null){
                map.put("data",resultMap);
                msg = "分页查询成功";
            }else {
                msg = "分页查询失败";
            }
        }
        //根据userid来查
        if (!StringUtils.isEmpty(userid)) {
            QueryWrapper<Resource> queryWrapper = new QueryWrapper<Resource>();
            queryWrapper.eq(userid!= null,"userid",userid);
            queryWrapper.in("state",0,2);
            //过滤字段
            queryWrapper.select("resourceid","ename","picture_address","integral","described");
            Page<Resource> page = new Page<Resource>(1,10);
            resultMap = this.resourceMapper.selectMapsPage(page,queryWrapper);
            if(resultMap != null){
                map.put("data",resultMap);
                msg = "分页查询成功";
            }else {
                msg = "分页查询失败";
            }
        }
        //根据ename来查
        if (!StringUtils.isEmpty(ename)) {
            QueryWrapper<Resource> queryWrapper = new QueryWrapper<Resource>();
            queryWrapper.like("ename",ename);
            queryWrapper.in("state",0,2);
            //过滤字段
            queryWrapper.select("resourceid","ename","picture_address","integral","described");
            Page<Resource> page = new Page<Resource>(1,2);
            resultMap = this.resourceMapper.selectMapsPage(page,queryWrapper);
            if(resultMap != null){
                map.put("data",resultMap);
                msg = "分页查询成功";
            }else {
                msg = "分页查询失败";
            }
        }
        //根据resourceid来查详细信息
        if (!StringUtils.isEmpty(resourceid)) {
            QueryWrapper<Resource> queryWrapper = new QueryWrapper<Resource>();
            queryWrapper.eq(resourceid != null,"resourceid",resourceid);
            queryWrapper.in("state",0,2);
            //过滤字段
            queryWrapper.select("resourceid","ename","picture_address","described","address","summary","integral");
            List<Map<String, Object>> resultMap1 = this.resourceMapper.selectMaps(queryWrapper);
            if(resultMap1 != null){
                map1.put("details",resultMap1);
                map.put("data",map1);
                msg = "详情查询成功";
            }else {
                msg = "详情查询失败";
            }
        }
        //根据refer来查首页推荐资源信息
        if (!StringUtils.isEmpty(refer)) {
            //查询是否有该分类的子分类
            QueryWrapper<Classify> queryWrapper = new QueryWrapper<Classify>();
            queryWrapper.eq("layer",1);
            queryWrapper.orderBy(true, true, "rank");
            //过滤字段
            queryWrapper.select("classid","classname","rank");
            List<Map<String, Object>> listClass = this.classifyMapper.selectMaps(queryWrapper);
            for(Map<String, Object> classify : listClass){
                String name = classify.get("classname").toString();
                //map1.put(classify.get("classname").toString(),listClass.getClass());
                QueryWrapper<Resource> queryWrapper1 = new QueryWrapper<Resource>();
                queryWrapper1.eq("state",2).and(i -> i.like(name != null,"ename",name).or().like(name != null,"described",name).or().like(name != null,"summary",name));
                queryWrapper1.last("limit 4");
                //过滤字段
                queryWrapper1.select("resourceid","ename","picture_address","integral","described");
                List<Map<String, Object>> resultListMap = this.resourceMapper.selectMaps(queryWrapper1);
                classify.put("productList",resultListMap);
            }
            if(resultMap != null){
                map1.put("classname",listClass);
                map.put("data",map1);
                msg = "分类推荐查询成功";
            }else {
                msg = "分类推荐查询失败";
            }
        }
        map.put("msg", msg);

        return map;
    }

}
