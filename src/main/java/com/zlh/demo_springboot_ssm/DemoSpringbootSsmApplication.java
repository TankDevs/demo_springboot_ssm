package com.zlh.demo_springboot_ssm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zlh.demo_springboot_ssm.mapper")
@SpringBootApplication
public class DemoSpringbootSsmApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoSpringbootSsmApplication.class, args);
        try {
            System.out.println("+++++++++++++++++++++++ " + DemoSpringbootSsmApplication.class.getClassLoader().getResource("").getPath());
        }
        catch (NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
