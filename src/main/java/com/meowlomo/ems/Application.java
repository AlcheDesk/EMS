package com.meowlomo.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {
        // main configuration classes
        "com.meowlomo.ems.config",
        // scheduler
        "com.meowlomo.ems.scheduler",
        // spring service classes
        "com.meowlomo.ems.core.service",
        // listener classes
        "com.meowlomo.ems.listener",
        // type handler
        "com.meowlomo.ems.typehandler",
        // jersey REST classes
        "com.meowlomo.ems.core.resource",
        //retrofit
        "com.meowlomo.ems.http",
        // security package
        "com.meowlomo.ems.security",
        //root
        "com.meowlomo.ems",
        //scheduler
        "com.meowlomo.ems.scheduler"})
@EnableCaching
@EnableAsync
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}