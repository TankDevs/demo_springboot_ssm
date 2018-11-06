package com.zlh.demo_springboot_ssm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoSpringbootSsmApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoSpringbootSsmApplication.class, args);
        System.out.println("+++++++++++++++++++++++ "+DemoSpringbootSsmApplication.class.getClassLoader().getResource("").getPath());

    }
}
