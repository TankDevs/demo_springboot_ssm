package com.zlh.demo_springboot_ssm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//@RestController=@Controller+@ResponseBody,不能返回页面
@RestController
public class RestTestController {

    @RequestMapping("/rest")
    public String rest() {
        return "Hello @RestController!!";
    }

}
