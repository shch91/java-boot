package com.ldy.shch91.util.readResource;

import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class ReadResource {


    /* 测试利用class或者classloader读取文件classpath下的文件 */
    public static void getResourceByClassOrClassLoader() {
        /*
         * Classloader是从classpath中读取资源的一个类，
         * 一般我们用classloader来加载class，实际上，但凡是处在classpath中的文件，
         * 我们称之为资源，都可以用classloader来读取。
         * 注意，使用classloader来加载资源的时候，目录前面不加“/”
         */
        System.out.println(ReadResource.class.getClassLoader().getResource(
                "spring/spring.xml"));

        /* 这个API的起始路径是当前类的路径，如果要正确的读到资源，目标资源必须和当前class在同一级，或子目录里，可以用相对路径读取到。 */
        System.out.println(ReadResource.class
                .getResource("/spring.xml"));

        /*安全用法---提倡使用  From:http://www.cnblogs.com/gaoxing/p/4703412.html*/
        System.out.println( Thread.currentThread().getContextClassLoader().getResource(
                "spring.xml"));

        /*读取到InputStream*/
        InputStream inputStream = ReadResource.class.getClassLoader()
                .getSystemResourceAsStream("spring.xml");
        System.out.println(inputStream);

    }

}
