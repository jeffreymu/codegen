package com.cjhxfund.autocode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class OpenCodeGenApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenCodeGenApplication.class, args);
    }
}
