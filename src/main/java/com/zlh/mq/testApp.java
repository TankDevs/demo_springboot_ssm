package com.zlh.mq;

import org.springframework.boot.SpringApplication;

public class testApp {
    public static void main(String[] args) {
        System.out.println("********************");
        try {
            System.out.println("hello world");
            System.out.println("bye");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
