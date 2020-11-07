package shch91.com.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class KeyUtil {

   public static   synchronized String   getUniqueKey(){
       Random random = new Random();
       Integer num = random.nextInt(100000);
       return  num.toString()+System.currentTimeMillis();
   }
}