package com.zlh.demo_springboot_ssm.controller;

import com.zlh.demo_springboot_ssm.domain.PersonInfo;
import com.zlh.demo_springboot_ssm.mapper.PersonInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PersonInfoController {

    @Autowired
    private PersonInfoMapper personInfoMapper;

    @RequestMapping("/resbody")
    @ResponseBody
    public  String resBody() {
        return "Hello,@Controller+@ResponseBodyD!!";
    }

    @RequestMapping("/person-index")
    public String personIndex() {
        return "person-index";
    }

    @RequestMapping("/queryById")
    public ModelAndView queryById(HttpServletRequest request) {

        String id = request.getParameter("id");
        PersonInfo person = personInfoMapper.selectById(id);
        ModelMap map=new ModelMap();
        map.put("person",person);
        return  new ModelAndView("person-index",map);
    }

    //配置多个页面
    @RequestMapping({"/queryAll","/persons"})
    public ModelAndView getPersons() {
        ModelMap map=new ModelMap();
        List<PersonInfo> persons = personInfoMapper.selectAll();
        map.put("personList",persons);
        return new ModelAndView("persons",map);
    }

}
