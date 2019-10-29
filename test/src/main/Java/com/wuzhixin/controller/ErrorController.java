package com.wuzhixin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorController {


    @RequestMapping(value = "/error")
    @ResponseBody
    public String returnErr(){



        return "code:403";

    }
}
