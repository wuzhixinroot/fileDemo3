package com.wuzhixin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {



    @RequestMapping("/test")
    public String  test(){


            System.out.println("ssss");
            System.out.println("sssssss");
            return "redirect:/myhtml/test.html";
        }

}
