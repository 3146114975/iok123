package com.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@ServletComponentScan(basePackages = "com.boot")
@SpringBootApplication
@MapperScan(basePackages = "com.boot.mapper")
public class SmbmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmbmsApplication.class, args);
    }

}
