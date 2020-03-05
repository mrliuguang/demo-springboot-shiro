package com.hnsic.demospringbootshiro;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

@Slf4j
@EnableCaching
@SpringBootApplication
@MapperScan(value = "com.hnsic.demospringbootshiro.mapper")
public class DemoSpringbootShiroApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DemoSpringbootShiroApplication.class, args);
        String serverPort = context.getEnvironment().getProperty("server.port");
        String context_path = context.getEnvironment().getProperty("server.servlet.context-path");
        log.info("mblog started at http://localhost:" + serverPort + context_path);
    }

}
