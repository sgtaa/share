package com.share.wxerp.service.serviceImpl;

import com.share.wxerp.common.PasswordUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;



public class UserServiceImplTest {
    private static String algorithmName = "md5";
    private static int hashIterations = 2;

    @Test
    public void add() {
        /*String name = "sgt";
        String password = "sgtaa";
        Md5Hash md5Hash = new Md5Hash(name,password);
        String salt= md5Hash.toString();
        String addsalt = name + salt;
        String newPassword = new SimpleHash(algorithmName, password,  ByteSource.Util.bytes(addsalt), hashIterations).toHex();
        System.out.println("salt:::"+salt);
        System.out.println("newPassword:::"+newPassword);*/
    }

    @Test
    public void query() {
    }
}