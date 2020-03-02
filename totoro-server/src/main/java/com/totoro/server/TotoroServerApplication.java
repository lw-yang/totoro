package com.totoro.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.totoro")
public class TotoroServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TotoroServerApplication.class, args);
    }

}
