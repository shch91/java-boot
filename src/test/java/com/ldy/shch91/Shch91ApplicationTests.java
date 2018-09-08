package com.ldy.shch91;

import com.ldy.shch91.test.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Shch91ApplicationTests {


    private static Logger logger = LoggerFactory.getLogger(Shch91ApplicationTests.class);

    @Test
    public void contextLoads() {
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testSpringRedis() {
        //stringRedisTemplate的操作
        // String读写
        redisTemplate.delete("myStr");
        redisTemplate.opsForValue().set("myStr", "skyLine");
        logger.info(redisTemplate.opsForValue().get("myStr").toString());
        logger.info("---------------");

        // List读写
        redisTemplate.delete("myList");
        redisTemplate.opsForList().rightPush("myList", "T");
        redisTemplate.opsForList().rightPush("myList", "L");
        redisTemplate.opsForList().leftPush("myList", "A");
        List<String> listCache = redisTemplate.opsForList().range(
                "myList", 0, -1);
        for (String s : listCache) {
            logger.info(s);
        }
        logger.info("---------------");

        // Set读写
        redisTemplate.delete("mySet");
        redisTemplate.opsForSet().add("mySet", "A");
        redisTemplate.opsForSet().add("mySet", "B");
        redisTemplate.opsForSet().add("mySet", "C");
        Set<String> setCache = redisTemplate.opsForSet().members(
                "mySet");
        for (String s : setCache) {
            logger.info(s);
        }
        logger.info("---------------");

        // Hash读写
        redisTemplate.delete("myHash");
        redisTemplate.opsForHash().put("myHash", "BJ", "北京");
        redisTemplate.opsForHash().put("myHash", "SH", "上海");
        redisTemplate.opsForHash().put("myHash", "HN", "河南");
        Map<String, String> hashCache = redisTemplate.opsForHash()
                .entries("myHash");
        for (Map.Entry entry : hashCache.entrySet()) {
            logger.info(entry.getKey() + " - " + entry.getValue());
        }
        logger.info("---------------");
    }

    @Test
    public  void fa(){
        Person person1 = new Person(1,"fastjson1",1);
        Person person2 = new Person(2,"fastjson2",2);

        redisTemplate.opsForList().rightPush("persions",person1);
        redisTemplate.opsForList().rightPush("persions",person2);

       List<Person>  out=  redisTemplate.opsForList().range("persions",0,-1);
       for (Person p:out)
           logger.info(p.toString());
    }

    @Test
    public  void freqa(){
        Person person1 = new Person(1,"fastjson1",1);
        Person person2 = new Person(2,"fastjson2",2);

         redisTemplate.opsForHash().put("person",person1.getId().toString(),person1);
        redisTemplate.opsForHash().put("person",person2.getId().toString(),person2);

         Map<Integer,Person> map= redisTemplate.opsForHash().entries("person");
         for(Map.Entry<Integer,Person> entry:map.entrySet()){
             logger.info(entry.getKey()+":"+entry.getValue());
         }

    }

    @Test
    public void re(){

    }

}
