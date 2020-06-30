package com.store.wxshare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.store.wxshare.mapper")
@SpringBootApplication
@EnableCaching
public class WxshareApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxshareApplication.class, args);
    }

}
