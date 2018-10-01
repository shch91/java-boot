package com.ldy.shch91.test;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldy.shch91.daoentity.Actor;
import com.ldy.shch91.mapper.sakila.ActorMapper;
import com.ldy.shch91.util.serialize.XmlUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XmlTest {

    private  static final Logger logger= LoggerFactory.getLogger(XmlTest.class);

    @Resource
    ActorMapper actorMapper;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    public  void fdsa(){

        Actor actor=actorMapper.select(23);
        String xml=XmlUtil.toXML(actor);
        logger.info(xml);

        Actor deser=(Actor) XmlUtil.fromXML(xml,Actor.class);
        logger.info(JSON.toJSONString(deser));
    }
    @Test
    public void  dsf(){
        Pattern INTEGER_PATTERN = Pattern.compile("-?[0-9]+");
        boolean fa=INTEGER_PATTERN.matcher("-06565").matches();
        logger.info(String.valueOf(fa));
    }

    @Test
    public void vd(){


        String fds=UUID.randomUUID().toString();
        System.out.println(fds);
    }
}
