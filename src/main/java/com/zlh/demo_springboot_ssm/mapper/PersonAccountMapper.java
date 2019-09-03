package com.zlh.demo_springboot_ssm.mapper;
import com.zlh.demo_springboot_ssm.domain.PersonInfo;
import java.util.List;
import org.apache.ibatis.annotations.*;

public interface PersonAccountMapper {

public List<PersonInfo> selectAll();

public PersonInfo selectById();

}
