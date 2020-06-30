package com.store.wxshare.serviceImpl;

import com.store.wxshare.common.Approach;
import com.store.wxshare.common.GlobalResult;
import com.store.wxshare.service.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Author suguotai
 * @Description //TODO 管理用户
 * @Date
 * @Param
 * @return
 **/
@Service
@Transactional
public class AdminUserServiceImpl implements UsersService {
    @Override
    public GlobalResult user_control(String operflag, String detail, String signature) {
        String msg = null;
        String data = null;
        Map<String, Object> respData = null;
        System.out.println("operflag="+ operflag +"detail="+ detail + " signature" +signature);
        if (operflag.equals(Approach.OPERFLAG_NEW)){

            System.out.println("operflag="+ operflag + "新增成功");
        }
        if (operflag.equals(Approach.OPERFLAG_MODIFY)){

            //respData =  modify(detail,signature);
            System.out.println("operflag="+ operflag + "修改成功");
        }
        if (operflag.equals(Approach.OPERFLAG_DELETE)){
            //respData =  delete(detail,signature);
            System.out.println("operflag="+ operflag + "删除成功");
        }
        if (operflag.equals(Approach.OPERFLAG_QUERY)){
            //respData = query(detail,signature);
            System.out.println("operflag="+ operflag + "查询成功");
        }
        if (operflag.equals(Approach.OPERFLAG_CHECKIN)){
            //respData = checkIn(detail,signature);
            System.out.println("operflag="+ operflag + "签到成功");

        }
        if (operflag.equals(Approach.OPERFLAG_CONVERT)){
            //respData = convert(detail,signature);
            System.out.println("operflag="+ operflag + "兑换成功");
        }
        if (operflag.equals(Approach.OPERFLAG_SUBMIT)){

            //respData = submit(detail,signature);
            System.out.println("operflag="+ operflag + "提交成功");
        }
        GlobalResult result = GlobalResult.build(200, (String) respData.get("msg"), respData.get("data"));
        return result;
    }
}
