package com.wuzhixin.controller;


import com.wuzhixin.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @RequestMapping(value = "/login",method = RequestMethod.POST/*,produces = {"text/plain;charset=UTF-8"}*/)
    public String userLogin(@RequestParam(value = "username",required = false)String username,
                            @RequestParam(value = "password",required = false)String password,
                            HttpServletRequest req, HttpServletResponse rep){
       /* rep.setCharacterEncoding("utf-8");
        rep.setContentType("utf-8");*/


       // List<User> user = new ArrayList<>();

        /**
         * 这里用user模拟 数据库查询出来的数据,来进行登录验证。
         */
        User user = new User("wuzhixin", "111111");

        if(user.getUsername().equals(username)&&user.getPassword().equals(password)){
            HttpSession session = req.getSession();
            session.setMaxInactiveInterval(1000*60*60*30);
            session.setAttribute("user",user);
            return "redirect:/index";
                
        }else{
            return "redirect:/login2.html";
        }

    }
}
