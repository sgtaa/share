package com.share.wxerp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.share.wxerp.mapper")
@SpringBootApplication
public class WxerpApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxerpApplication.class, args);
    }

}
