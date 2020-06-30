package com.share.wxerp;

import com.share.wxerp.common.RASCoder;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@SpringBootTest
public class WxerpApplicationTests {

    @Test
    public static void main(String[] args) {
        /*PasswordUtil passwordHelper = new PasswordUtil();
        User user = new User();
        user.setUsername("admin");
        user.setPassword("12345");
        passwordHelper.encryptPassword(user);
        System.out.println(user);*/

        //initKey();
        decrypt();
    }
    @Test
    public static void initKey() {
        try {
            RASCoder raCoder = new RASCoder();
            Map<String, Key> keyMap= raCoder.initKey();
            System.out.println("PrivateKey:::"+raCoder.getPrivateKey(keyMap));
            System.out.println("PublicKey:::"+raCoder.getPublicKey(keyMap));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public static void decrypt() {
        RASCoder rasCoder = new RASCoder();
        String detail = "eOCUXPwywr9zvDyBp9TfDQVno8TMtQn1iyGtK1Y46zqJjXcYsRXBq18fFPr7Qbsbg4OAR8x%2FiJbTnG6hiNn00EwNYiyTGDRHOF48N0wQG7pt2cjMOkx6lZrs1RKcY5H9Vp5WIUvCQJ2%2FGZFGlnx%2FMIJQUoFCjoXXL%2BsBz1UHHsw%3D";
        String privateKey= "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIu2WnHWVNzKRGgiknFfX0co9qbJAp7dF8829U9nAS2InlVFlhKs0BvYnV6f+ZQALJVn4LNgEIrA/rLYsjAxvJiUUQZYz0ar0SlP2I1CEUM+mMae2xRo1M6uMJCXp0IvTlp5lE4lN8ZOzqSlIs/N5YkbndxIcjAcTF9gz4k2xpL/AgMBAAECgYAVZ835rQX5gf/zGIDpX9xgMbWAnkGJh4RTWi9BiB2VFV504CF7IQENkyWhTs9VTqWGCpFRykEekS00x6CdY7FqUHHMLwjbrhZVDHzeWuVdof9yDP2jMW4WYp4fWHMXJCLP837UKHAvH/LJhijzhFIZ1j6ZJaf17N1W6UvmPI5uEQJBAN4/ebqXroLCdvgwi300CTGW9tWavi5ZJWXJEFyNqwxUkXJU8PWf2OIIZmTcW9beF6QMbZZq3HnLxTuY1t1AZ5cCQQCg7hGu49/hajJIg5p3pUAvmjjrF30H5JRvX7nI+JwTtIMJEDTLVikHqV7I3ijFmmUk2YYK9D3DnT0/yOZMP9zZAkEAx8VsyQGg9p6QS+Vu7NsCTJXzR+vxeT3edNC3qUoab1nw50Fc2pyVWg/M4mqFDu3ReD9HnkZt6fj1T+3BbV1//wJAH7zz9dbxJ2anRg/WXfRURU8YHJ1ERlM5RwHNBSqqtWQrV5U/pD541w77J4hQ6C+79NmFixGCQNr44mR/rtgFeQJAS2IQNleG8C2PzJdWQv5fatlxviST5E//6tl5B7kcc7SFOPUt/Ps20hwZsn2oQiY2OcXFB9YtXB+9Puta4oDzHw==";
        String publicKey= "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLtlpx1lTcykRoIpJxX19HKPamyQKe3RfPNvVPZwEtiJ5VRZYSrNAb2J1en/mUACyVZ+CzYBCKwP6y2LIwMbyYlFEGWM9Gq9EpT9iNQhFDPpjGntsUaNTOrjCQl6dCL05aeZROJTfGTs6kpSLPzeWJG53cSHIwHExfYM+JNsaS/wIDAQAB";

        try {
            /*byte[] pubdata=rasCoder.encryptByPublicKey(detail,publicKey);
            String str= new String (pubdata);
            System.out.println("pubdata:"+str);*/
            String data = new String(rasCoder.decryptByPrivateKey(detail,privateKey));
            System.out.println("data:"+data.toString()+data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
