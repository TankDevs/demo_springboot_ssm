package com.zlh.demo_springboot_ssm;

import com.zlh.demo_springboot_ssm.domain.PersonInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.zlh.demo_springboot_ssm.mapper.PersonInfoMapper;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonInfoMapperTest {

    @Autowired
    private PersonInfoMapper personInfoMapper;

    @Test
    public void testSelectAll() {

        List<PersonInfo> personInfoList = personInfoMapper.selectAll();
        assertFalse(personInfoList.size()==0);
        for (PersonInfo person : personInfoList
                ) {

            System.out.println(person.getName() + " " + person.getSex() + " " + person.getAge());
        }

    }

    @Test
    public void testSelectById() {

        PersonInfo person = personInfoMapper.selectById("234");
        assertNotNull(person);
        System.out.println(person.getName() + " " + person.getSex() + " " + person.getAge());

    }

    @Test
    public void testSelectBy() {

        PersonInfo person = personInfoMapper.selectBy("李四", "234");
        assertNotNull(person);
        System.out.println(person.getName() + " " + person.getSex() + " " + person.getAge());
    }
}