package com.yage.springenv;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.yage.springenv.mapper")
@SpringBootApplication
public class SpringEnvApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringEnvApplication.class, args);
    }
}
