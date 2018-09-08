package com.ldy.shch91.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFastJson {
    private static Logger logger = LoggerFactory.getLogger(TestFastJson.class);


    @Test
    public  void gfds() {
        method1();  
        method2();
    }  
      
    static void method1(){  
        logger.info("javabean转化示例开始----------");
        Person person = new Person(1,"fastjson",1);
          
        //这里将javabean转化成json字符串  
        String jsonString = JSON.toJSONString(person);
        logger.info(jsonString);
        //这里将json字符串转化成javabean对象,  
        person =JSON.parseObject(jsonString,Person.class);  
        logger.info(person.toString());
          
        logger.info("javabean转化示例结束----------");
    }  
      
    static void method2(){  
        logger.info("List<javabean>转化示例开始----------");
          
        Person person1 = new Person(1,"fastjson1",1);
        Person person2 = new Person(2,"fastjson2",2);
        List<Person> persons = new ArrayList<Person>();
        persons.add(person1);  
        persons.add(person2);  
        String jsonString = JSON.toJSONString(persons);  
        logger.info("json字符串:"+jsonString);
          
        //解析json字符串  
        List<Person> persons2 = JSON.parseArray(jsonString,Person.class);  
        //输出解析后的person对象，也可以通过调试模式查看persons2的结构  
        logger.info("person1对象："+persons2.get(0).toString());
        logger.info("person2对象："+persons2.get(1).toString());
          
        logger.info("List<javabean>转化示例结束----------");
    }

    static void  method3(){


       Person person=new Person(23,"fdadsfgdsfds",55435252);
        Student<Person> stu=new Student<Person>(3123,"fdsfdfa",person);

       String jsonString=JSON.toJSONString(stu);
       logger.info(jsonString);

       logger.info("jsonString 包含泛型转换");
       Student<Person> result=JSON.parseObject(jsonString,new TypeReference<Student<Person>>(){});

        logger.info(result.getCourse().toString());
    }
    @Test
    public void fdsf(){
        method3();
    }

}

