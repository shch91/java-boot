package shch91.app.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
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
import shch91.inter.DemoService;
import shch91.repo.daoentity.Actor;
import shch91.repo.daoentity.Salary;
import shch91.repo.mapper.employees.SalaryMapper;
import shch91.repo.mapper.sakila.ActorMapper;
import shch91.service.task.AsyncTask;
import shch91.service.zk.ZkCuratorListener;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author shch
 */
@Slf4j
@RestController
public class HelloController {


    @Resource
    private ActorMapper actorMapper;

    @Resource
    private SalaryMapper salaryMapper;

    @Autowired
    private DemoService demoService;


    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private AsyncTask async;

    @Autowired
    ZkCuratorListener zkCuratorListener;

    @Autowired
    CuratorFramework curatorFramework;

    @RequestMapping("/msg")
    public void ada() throws Exception {

        Actor actor = actorMapper.select(4);
        zkCuratorListener.nodeCache("/shch91/app");
        log.info(JSON.toJSONString(actor));

    }

    @RequestMapping("/hello/id")
    @Transactional(rollbackFor = {Exception.class})
    public int add() {
        Actor actor = actorMapper.select(23);


        log.info(JSON.toJSONString(actor));

        actor.setLastName("shch91/app");
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
    public Set<Integer> first() {

         return demoService.getSetInteger();
    }

    @RequestMapping("/doError")
    public Object error() {
        return 1 / 0;
    }

}