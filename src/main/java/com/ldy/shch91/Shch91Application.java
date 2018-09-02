package com.ldy.shch91;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@ImportResource(locations={"classpath:spring/spring.xml"})
@SpringBootApplication
@EnableScheduling
@EnableAsync
//@ComponentScan(basePackages ={"com.ldy.shch91"} )
public class Shch91Application {

    public static void main(String[] args) {
        SpringApplication.run(Shch91Application.class, args);
    }


}
