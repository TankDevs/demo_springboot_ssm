package com.zlh.demo_springboot_ssm.mapper;

import com.zlh.demo_springboot_ssm.domain.PersonInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
//在此添加注解@Mapper或者直接启动类上面添加注解@MapperScan("com.zlh.demo_springboot_ssm.mapper")
// 建议使用后者，不然每个mapper加个注解也挺麻烦的
//@Mapper
public interface PersonInfoMapper {

    @Select("select * from person_info")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "age", column = "age")
    })
    List<PersonInfo> selectAll();

    @Select("select * from person_info where id=#{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "age", column = "age")
    })
    PersonInfo selectById(String id);

    @Select("select * from person_info where id=#{id} and name=#{name} ")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "age", column = "age")
    })
    PersonInfo selectBy( @Param("name")String name,@Param("id")  String id);

}