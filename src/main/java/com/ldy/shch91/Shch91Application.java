package com.ldy.shch91;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations={"classpath:spring/spring.xml"})
public class Shch91Application {

    public static void main(String[] args) {
        SpringApplication.run(Shch91Application.class, args);
    }
}
