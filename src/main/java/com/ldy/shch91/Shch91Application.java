package com.ldy.shch91;

import com.ldy.shch91.netty.DiscardServer;
import com.ldy.shch91.netty.EchoClient;
import com.ldy.shch91.netty.EchoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
public class Shch91Application implements CommandLineRunner {

    //@Autowired
    DiscardServer discardServer;

    @Autowired
    EchoServer echoServer;


    public static void main(String[] args) {
        SpringApplication.run(Shch91Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        echoServer.run(8088);
    }

}
