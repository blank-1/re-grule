package com.grule.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * Created on 2018/03/05.
 */
@SpringBootApplication(scanBasePackages = "com.grule")
@EnableAutoConfiguration
//@ImportResource({"classpath*:spring/application.xml"})
@EnableRetry
public class GruleApplication {
    public static void main(String[] args) {
        SpringApplication.run(GruleApplication.class);
    }
}
