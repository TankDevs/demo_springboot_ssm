package com.zlh.mq;

import  com.zlh.comm.MyLog;

public class testApp {
    public static void main(String[] args) {
        System.out.println("********************");
        try {
            MyLog myLog=new MyLog();
            myLog.logTest();


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
