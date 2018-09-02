package com.ldy.shch91.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.ldy.shch91.activeMq.ConsumerService;
import com.ldy.shch91.activeMq.ProducerService;
import com.ldy.shch91.daoentity.Actor;
import com.ldy.shch91.daoentity.Salary;
import com.ldy.shch91.mapper.employees.SalaryMapper;
import com.ldy.shch91.mapper.employees.TmpMapper;
import com.ldy.shch91.mapper.sakila.ActorMapper;
import com.ldy.shch91.task.AsyncTask;
import com.ldy.shch91.util.readResource.ReadResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantReadWriteLock;


@RestController
public class HelloController {

    private  static final Logger logger=LoggerFactory.getLogger(HelloController.class);

    private ReentrantReadWriteLock lock=new ReentrantReadWriteLock();

    private static final LocalDate beginDate=LocalDate.of(2018,1,1);

    @Resource
    private ActorMapper actorMapper;

    @Resource
    private SalaryMapper salaryMapper;

    @Resource
    private TmpMapper tmpMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    ReadResource readResource;

    @Autowired
    private AsyncTask async;


    @Autowired
    private ProducerService producerService;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private Destination queueDestination;

    @RequestMapping("/msg")
    public  void ada() throws JMSException {

        Actor   actor=actorMapper.select(4);
        producerService.sendMessage(JSON.toJSONString(actor));
       TextMessage msg= consumerService.receive(queueDestination);
       logger.info(JSON.toJSONString(msg.getText()));

    }

    @RequestMapping("/hello/id")
    public int add(){
        Actor   actor=actorMapper.select(23);

        redisTemplate.opsForValue().set("23",actor);

          Actor aot=(Actor)redisTemplate.opsForValue().get("23");

         logger.info(JSON.toJSONString(aot));

         producerService.sendMessage(JSON.toJSONString(actor));

        System.out.println(JSON.toJSONString(actor));
        actorMapper.insertOrUpdate(actor);

        return 0;
    }


    @RequestMapping("/hello/{id}")
    public Actor index(@PathVariable Integer id) {
        Actor   actor=actorMapper.select(id);
        ValueOperations valOps=redisTemplate.opsForValue();
         HashMap<String,Object> map=new HashMap<>();
         map.put("as","fddas");
         map.put("dfa",actor);

         valOps.multiSet(map);

         async.dotask();

         logger.info("获取演员id",actor.toString());
         return actor;
    }

    @RequestMapping("/first")
    public String first() {

     //readResource.getResourceByClassOrClassLoader();
        boolean fda= Strings.isNullOrEmpty("");

        Vector ver=new Vector<String>();
        Actor   actor=actorMapper.select(32);
        logger.info(JSON.toJSONString(actor));
        redisTemplate.opsForValue().set("userToJson", JSON.toJSONString(actor));



        return "first controller";
    }

    @RequestMapping("/doError")
    public Object error() {
        return 1 / 0;
    }

    @RequestMapping("/kk")
    public void test() {
        List<Salary> res=  salaryMapper.getAll();
        tmpMapper.add(res.get(0));
        List<List<Salary>> part=Lists.partition(res,20000);

        for(List<Salary> item:part){
            threadPoolTaskExecutor.execute(new CacheTask(item));
        }
        return ;
    }

    private  class CacheTask implements Runnable {


        public CacheTask(List<Salary> list){
            salaryList=list;
        }
         List<Salary> salaryList;
        @Override
        public void run() {
              batchAdd(salaryList);
        }
    }

    private void batchAdd(List<Salary>list){
        tmpMapper.addBatch(list);
    }

}