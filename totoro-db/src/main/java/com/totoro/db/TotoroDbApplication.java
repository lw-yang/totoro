package com.totoro.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.totoro.db.dao")
public class TotoroDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(TotoroDbApplication.class, args);
    }

}
