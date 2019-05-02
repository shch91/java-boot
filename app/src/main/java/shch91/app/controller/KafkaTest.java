package shch91.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class KafkaTest {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;//kafkaTemplate相当于生产者

    @RequestMapping(value = "/{topic}/send", method = RequestMethod.GET)
    public void sendMeessage(
            @RequestParam(value = "message", defaultValue = "hello world") String message,
            @PathVariable final String topic) {
        log.info("start sned message to {}", topic);
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send(topic, message);//发送消息，topic不存在将自动创建新的topic
        listenableFuture.addCallback(//添加成功发送消息的回调和失败的回调
                result -> log.info("send message to {} success", topic),
                ex -> log.info("send message to {} failure,error message:{}", topic, ex.getMessage()));
    }

    @RequestMapping(value = "/default/send", method = RequestMethod.GET)
    public void sendMeessagedefault() {//发送消息到默认的topic
        log.info("start send message to default topic");
        kafkaTemplate.sendDefault("你好，世界");
    }

}
