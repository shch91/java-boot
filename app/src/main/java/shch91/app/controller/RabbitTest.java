package shch91.app.controller;
import shch91.service.rabbitMq.ProducerRabbitMq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rabbit")
public class RabbitTest {
    
    @Autowired
    private ProducerRabbitMq producerRabbitMq;

    @PostMapping("/hello")
    public void hello() {
        producerRabbitMq.send();
    }

}