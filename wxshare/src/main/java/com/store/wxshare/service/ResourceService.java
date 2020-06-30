package com.store.wxshare.service;

import com.store.wxshare.common.GlobalResult;

/**
 * @Author suguotai
 * @Description //TODO 资源操作service$
 * @Date $ $
 * @Param $
 **/
public interface ResourceService {
    public GlobalResult resource_control(String operflag, String detail, String signature);
}
