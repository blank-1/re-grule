package com.regel.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * Created by  on 2017/8/11.
 */
@SpringBootApplication(scanBasePackages = "com.regel")
@EnableAutoConfiguration
//@ImportResource({"classpath*:spring/application.xml"})
@EnableRetry
public class RegelApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegelApplication.class);
    }
}
