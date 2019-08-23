package com.zlh.demo_springboot_ssm.mapper;

import com.zlh.demo_springboot_ssm.domain.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//在此添加注解@Mapper或者直接启动类上面添加注解@MapperScan("com.zlh.demo_springboot_ssm.mapper")
// 建议使用后者，不然每个mapper加个注解也挺麻烦的
//@Mapper
public interface AccountMapper {
    int deleteByPrimaryKey(String accounId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(String accounId);

    List<Account> selectAll();

    int updateByPrimaryKeySelective(Account record);


    int updateByPrimaryKey(Account record);

}