package com.wzt.tapm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.wzt.tapm.mapper")
public class TapmApplication {

    public static void main(String[] args) {
        SpringApplication.run(TapmApplication.class, args);
    }

}
