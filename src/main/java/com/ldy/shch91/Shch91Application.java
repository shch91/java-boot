package com.ldy.shch91;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
@ImportResource(locations={"classpath:spring/spring.xml"})
@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableAsync
public class Shch91Application {

    private static final String SS="test";

    public static void main(String[] args) {
        SpringApplication.run(Shch91Application.class, args);
    }

    @Bean
    public RestTemplate fds() {
        return new RestTemplate();
    }

}
