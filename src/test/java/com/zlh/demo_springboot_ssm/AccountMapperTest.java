//
//package com.zlh.demo_springboot_ssm;
//
//import com.zlh.demo_springboot_ssm.domain.Account;
//import com.zlh.demo_springboot_ssm.mapper.AccountMapper;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.Assert.assertNotNull;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class AccountMapperTest {
//
//    @Autowired
//    private AccountMapper accountMapper;
//
//
//    @Test
//    public void testSelectById() throws Exception {
////        //获取classpath路径
//        //System.out.println("--------------- "+AccountMapperTest.class.getClassLoader().getResource("").getPath());
////        ConfigurableApplicationContext context=SpringApplication.run(DemoSpringbootSsmApplication.class);
////        String str1=context.getEnvironment().getProperty("mybatis.mapper-locations");
//        Account account = accountMapper.selectByPrimaryKey("2345");
//        assertNotNull(account);
//        System.out.println("--------------- "+account.getAccounId() + " " + account.getMoney() + " " + account.getPersonId());
//    }
//
//
//}
