package com.zlh.demo_springboot_ssm.controller;

import com.zlh.demo_springboot_ssm.domain.PersonInfo;
import com.zlh.demo_springboot_ssm.mapper.PersonAccountMapper;
import com.zlh.demo_springboot_ssm.mapper.PersonInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PersonAccountController {


    @Autowired(required = false)
    private PersonAccountMapper personAccountMapper;

    @RequestMapping(value = "/PersonAccount")
    public ModelAndView PersonAccount() {
        List<PersonInfo> persons = personAccountMapper.selectAll();
        ModelMap map = new ModelMap();
        map.put("personList", persons);
        return new ModelAndView("personAccount", map);
    }

    @RequestMapping("/PersonAccountQueryById")
    public ModelAndView PersonAccountQueryById(HttpServletRequest request) {
        String id = request.getParameter("id");
        PersonInfo person = personAccountMapper.selectById(id);
        ModelMap map = new ModelMap();
        map.put("person", person);
        return new ModelAndView("personAccount", map);
    }

}
