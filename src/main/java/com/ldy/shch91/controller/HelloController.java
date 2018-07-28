package com.ldy.shch91.controller;

import com.alibaba.fastjson.JSON;
import com.ldy.shch91.daoentity.Actor;
import com.ldy.shch91.mapper.ActorMapper;
import com.ldy.shch91.util.readResource.ReadResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;


@RestController
public class HelloController {

    private  final Logger logger=LoggerFactory.getLogger(getClass());


    private ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
    @Autowired
    private ActorMapper actorMapper;

    @Autowired
    RedisTemplate<String,String> redisTemplate;


    @Autowired
    ReadResource readResource;

    @RequestMapping("/hello/{id}")
    public Actor index(@PathVariable Integer id) {
        Actor   actor=actorMapper.select(id);
        ValueOperations valOps=redisTemplate.opsForValue();
         HashMap<String,String> map=new HashMap<>();
         map.put("as","fddas");
         map.put("dfa","fdsfdafdsa");

         valOps.multiSet(map);

         logger.info("获取演员id",actor.toString());
         return actor;
    }

    @RequestMapping("/first")
    public String first() {

     //readResource.getResourceByClassOrClassLoader();
        Actor   actor=actorMapper.select(32);
        logger.info(JSON.toJSONString(actor));
        redisTemplate.opsForValue().set("userToJson", JSON.toJSONString(actor));

        return "first controller";
    }

    @RequestMapping("/doError")
    public Object error() {
        return 1 / 0;
    }




}