package com.wuzhixin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {


    @RequestMapping(value = "/index",produces={"text/html;charset=UTF-8"})
    public String goIndex(HttpServletRequest req, HttpServletResponse rep){
        rep.setCharacterEncoding("utf-8");
     //   rep.setContentType("utf-8");

      //  rep.setContentType("utf-8");
        Object user = req.getSession().getAttribute("user");
        if(user!=null){
            return "forward:/WEB-INF/index.html";
        }else{
            return "redirect:/login2.html";
        }

    }
}
