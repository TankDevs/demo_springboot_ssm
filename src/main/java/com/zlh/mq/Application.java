package com.zlh.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//参考：https://blog.csdn.net/qq_33404395/article/details/80590113
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        try {
            System.out.println("hello world");
            System.out.println("bye");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

