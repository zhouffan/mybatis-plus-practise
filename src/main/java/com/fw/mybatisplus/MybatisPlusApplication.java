package com.fw.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MybatisPlusApplication {

    /**
     * 启动
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }

}
