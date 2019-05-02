package shch91.app.controller;
import shch91.service.rabbitMq.HelloSender1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rabbit")
public class RabbitTest {
    
    @Autowired
    private HelloSender1 helloSender1;

    @PostMapping("/hello")
    public void hello() {
        helloSender1.send();
    }

    public static void main(String[] args) {
        System.out.println(Integer.SIZE);
    }
}