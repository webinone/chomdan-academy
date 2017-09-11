package com.chomdan.api.application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ComponentScan(basePackages = "com.chomdan")
@SpringBootApplication
//@SpringBootApplication(scanBasePackages={"com.websystique.springboot"})
public class ModuleApiApplication {

    private final static Logger logger = LoggerFactory.getLogger(ModuleApiApplication.class);

    public static void main(String[] args) {
        logger.info("================= start !!!!");
        SpringApplication.run(ModuleApiApplication.class, args);
    }
}