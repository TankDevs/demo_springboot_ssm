package com.zlh.demo_springboot_ssm.controller;

import com.zlh.demo_springboot_ssm.domain.PersonInfo;
import com.zlh.demo_springboot_ssm.mapper.PersonInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonInfoController {

    @Autowired
    private PersonInfoMapper personInfoMapper;

    @RequestMapping("/resbody")
    @ResponseBody
    public  String resBody() {
        return "Hello,@Controller+@ResponseBodyD!!";
    }

    @RequestMapping("/home")
    public String index() {
        System.out.println("public String index()");
        String url="/index.html";
        return "/index";
    }

//    @RequestMapping("/home")
//    public List<PersonInfo> selectAll() throws Exception {
//        //List<PersonInfo> perList = personInfoMapper.selectAll();
//        //return perList;
//        return "index";
//    }

    @RequestMapping(value = "/personInfoIndex")
    @ResponseBody
    public String show(@RequestParam(value = "id")String id){
        PersonInfo user = personInfoMapper.selectById(id);
        if(null != user)
            return user.getId()+"/"+user.getName()+"/"+user.getSex();
        else return "null";
    }
}
