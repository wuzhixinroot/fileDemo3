package com.wuzhixin.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BaseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream outputStream = resp.getOutputStream();

     //   PrintWriter printWriter = resp.getWriter();
       // printWriter.println("hello world");
        System.out.println("sssssssss");
        PrintWriter writer = resp.getWriter();
        writer.println("fheowhfoiwhfwfhdfh");
        System.out.println("+++++++++++++=");
        resp.sendRedirect("/index.jsp");



    }
}
