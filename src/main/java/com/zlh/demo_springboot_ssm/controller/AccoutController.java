package com.zlh.demo_springboot_ssm.controller;

import com.zlh.demo_springboot_ssm.domain.Account;
import com.zlh.demo_springboot_ssm.domain.PersonInfo;
import com.zlh.demo_springboot_ssm.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AccoutController {

    @Autowired(required = false)
    private AccountMapper accountMapper;

    @RequestMapping("/accounts")
    public ModelAndView getAccounts() {
        ModelMap map=new ModelMap();
        List<Account> accounts = accountMapper.selectAll();
        map.put("accountList",accounts);
        return new ModelAndView("accounts",map);
    }

}
