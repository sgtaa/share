package com.share.wxerp.common;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.Map;

/**
 * @Author suguotai
 * @Description //TODO
 * @Date
 * @Param
 * @return
 **/
public class PasswordUtil {

    private static String algorithmName = "md5";
    private static int hashIterations = 2;

    public static Map<String, String> encryption(String name, String password) {
        System.out.println("username:::"+ name+"newPassword:::"+password);
        Map<String,  String> map = null;
        Md5Hash md5Hash = new Md5Hash(name,password);
        String salt= md5Hash.toString();
        String addsalt = name + salt;
        String newPassword = new SimpleHash(algorithmName, password,  ByteSource.Util.bytes(addsalt), hashIterations).toHex();
        System.out.println("salt:::"+salt);
        System.out.println("newPassword:::"+newPassword);
        map.put("salt",salt);
        map.put("newPassword",newPassword);
        return map;
    }
}
