package com.store.wxshare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.store.wxshare.entity.Classify;

import java.util.List;

/**
 * @Author suguotai
 * @Description //TODO $
 * @Date $ $
 * @Param $
 **/
public interface ClassifyMapper extends BaseMapper<Classify> {
    List<Classify> selectListByLayer();

    List<Classify> selectListByParent(String parentid);

    List<Classify> selectListByClassify();
}
