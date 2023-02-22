package com.hjx.onlineedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hjx.onlineedu.mapper")
public class OnlineeduApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineeduApplication.class, args);
    }

}
