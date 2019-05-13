package daotest;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shch91.Shch91Application;
import shch91.repo.daoentity.Actor;
import shch91.repo.daoentity.Salary;
import shch91.repo.mapper.employees.SalaryMapper;
import shch91.repo.mapper.sakila.ActorMapper;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Shch91Application.class)

public class Tda {
    @Resource
    private ActorMapper actorMapper;

    @Resource
    private SalaryMapper salaryMapper;

    @Test
    public void dtuy(){
        Actor ae= actorMapper.select(32);
        String str=JSONUtils.toJSONString(ae);
        log.info(str);

        List<Salary> lists=salaryMapper.getAll();
        log.info(JSONUtils.toJSONString(lists));
    }
}
