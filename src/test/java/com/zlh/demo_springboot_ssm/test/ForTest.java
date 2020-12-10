package com.zlh.demo_springboot_ssm.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ForTest {

    @Before
    public void setUp()  {


    }

    @After
    public void tearDown() {
    }

    @Test
    public void testDecimal() {

        double d1 = 0.1;
        double d2 = 0.69;
        //一些小数无法用的decimal精确表示，在运算时就会体现精度问题
        // 结果为0.7899999999999999
        double dSum = d1 + d2;
        System.out.println(d1 + " + " + d2 + " = " + dSum);
        System.out.println("（0.1 + 0.69） = " + (0.1 + 0.69));
        //必须使用BigDecimal的string参数的构造函数，才能保持精度一致
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        BigDecimal bSum = b1.add(b2);
        System.out.println("正确做法："+b1 + " + " + b2 + " = " + bSum);
        //反例
        BigDecimal bb1 = new BigDecimal(d1);
        BigDecimal bb2 = new BigDecimal(d2);
        BigDecimal bbSum = bb1.add(bb2);
        System.out.println("反例："+bb1 + " + " + bb2 + " = " + bbSum);
        assertNotEquals(b1,bb1);
    }


}