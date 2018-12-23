package com.ldy.shch91.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.ldy.shch91.daoentity.Actor;
import com.ldy.shch91.daoentity.Salary;
import com.ldy.shch91.mapper.employees.SalaryMapper;
import com.ldy.shch91.mapper.employees.TmpMapper;
import com.ldy.shch91.mapper.sakila.ActorMapper;
import com.ldy.shch91.task.AsyncTask;
import com.ldy.shch91.util.readResource.ReadResource;
import com.ldy.shch91.zk.ZkCuratorListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantReadWriteLock;


@RestController
@Slf4j
public class HelloController {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private static final LocalDate beginDate = LocalDate.of(2018, 1, 1);

    @Resource
    private ActorMapper actorMapper;

    @Resource
    private SalaryMapper salaryMapper;

    @Resource
    private TmpMapper tmpMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    ReadResource readResource;

    @Autowired
    private AsyncTask async;

    @Autowired
    ZkCuratorListener zkCuratorListener;

    @Autowired
    CuratorFramework curatorFramework;

    @RequestMapping("/msg")
    public void ada() throws Exception {

        Actor actor = actorMapper.select(4);
        zkCuratorListener.nodeCache("/shch91");
        log.info(JSON.toJSONString(actor));

    }

    @RequestMapping("/hello/id")
    @Transactional(rollbackFor = {Exception.class})
    public int add() {
        Actor actor = actorMapper.select(23);


        log.info(JSON.toJSONString(actor));

        actor.setLastName("shch91");
        actorMapper.insertOrUpdate(actor);
        return 0;
        //throw new RuntimeException("insert");

    }


    @RequestMapping("/hello/{id}")
    public Actor index(@PathVariable Integer id) {
        Actor actor = actorMapper.select(id);
        ValueOperations valOps = redisTemplate.opsForValue();
        HashMap<String, Object> map = new HashMap<>();
        map.put("as", "fddas");
        map.put("dfa", actor);

        valOps.multiSet(map);

        async.dotask();

        log.info("获取演员id", actor.toString());
        return actor;
    }

    @RequestMapping("/first")
    public String first() {

        //readResource.getResourceByClassOrClassLoader();
        boolean fda = Strings.isNullOrEmpty("");

        Vector ver = new Vector<String>();
        Actor actor = actorMapper.select(32);
        log.info(JSON.toJSONString(actor));
        redisTemplate.opsForValue().set("userToJson", JSON.toJSONString(actor));


        return "first controller";
    }

    @RequestMapping("/doError")
    public Object error() {
        return 1 / 0;
    }

    @RequestMapping("/kk")
    public void test() {
        List<Salary> res = salaryMapper.getAll();
        tmpMapper.add(res.get(0));
        List<List<Salary>> part = Lists.partition(res, 20000);

        for (List<Salary> item : part) {
            threadPoolTaskExecutor.execute(new CacheTask(item));
        }
        return;
    }

    private class CacheTask implements Runnable {


        public CacheTask(List<Salary> list) {
            salaryList = list;
        }

        List<Salary> salaryList;

        @Override
        public void run() {
            batchAdd(salaryList);
        }
    }

    private void batchAdd(List<Salary> list) {
        tmpMapper.addBatch(list);
    }

}