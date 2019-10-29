package com.wuzhixin.servlet;
import com.wuzhixin.rsasign.RSA;
import com.wuzhixin.rsasign.RSASignature;
import com.wuzhixin.rsasign.RSAUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Properties;
import java.util.Random;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterServlet implements Filter {
    public FilterServlet() {
        // 容器（服务器）启动时执行
    }
    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        /* 容器（服务器）时执行 */
        System.out.println("======== 初始化方法 ========");
        // 获取的是web.xml中配置Filter时设置的值，参数为设置值得名称，若参数不存在，则返回空
        String initParam = fConfig.getInitParameter("param");
        System.out.println("param ========" + initParam);
    }



    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //* 在访问的时候执行 *//*
        System.out.println("======= 开始执行doFilter ========");
       // String XID =  this.getRandomString();
        HttpServletRequest httpServletRequest =(HttpServletRequest)request;
        HttpServletResponse httpServletResponse =(HttpServletResponse)response;


        String sid = httpServletRequest.getHeader("SID");
        String signature = httpServletRequest.getHeader("signature");
        System.out.println(sid);
        System.out.println(signature);
        String publicKey =/*"-----BEGIN PUBLIC KEY-----" +*/
                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQD3XSdz1MnzazBEN5KOfTx0IyVJ" +
                "Z5wb57isrCuHDhnYXwtmdhQalgII0fozeeFpMpAvlnmHC1kpW7XVGvZnLx3bWbCE" +
                "bf+pMSW4kmQuI+5cxRUJbCl7sdaODBrINgERHPICVC18AJLThEVMHyjuR6Jn4zQm" +
                "yYNbReSktY/BrFTvMQIDAQAB" /*+
                "-----END PUBLIC KEY-----"*/;
        if(null!=sid&&null!=signature) {
           boolean b = RSASignature.doCheck(sid, signature,publicKey, "UTF-8");

           if(b==true){
               chain.doFilter(request, response);
               System.out.println("======= 结束执行doFilter ========");
           }else{

             //  PrintWriter writer = httpServletResponse.getWriter();

             //  writer.println("403");
             //  writer.println("您没有权限");



               //如果验证不同过 ，回到 错误；页面，显示403状态码
               httpServletResponse.sendRedirect("http://localhost:9999/error.html");
             //  System.out.println("试试试试试试");

              // ServletOutputStream outputStream = response.getOutputStream();

           }

           // boolean verify = false;
           // boolean b = false;
            try {
             //   String signstr= RSASignature.sign(sid, RSA.loadPrivateKeyByFile(new Test().path),"UTF-8");

             //   verify = RSAUtils.verify(sid.getBytes(),publicKey, signature);
                // b = RSASignature.doCheck(sid,signature, RSA.loadPublicKeyByFile("/Users/wuzhixin/oldIdeaLocation/idealocation/test/src/main/resources/publicKey.keystore"),"UTF-8");
              //  System.out.println(verify);
            } catch (Exception e) {
                e.printStackTrace();
            }
           // System.out.println(verify);
        }

        try {
          //  String signture = this.rsaSignture(this.getRandomString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 转发到下一个组件，进行后续的处理（组件可以是一个过滤器，也可以是一个servlet）


    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    public String rsaSignture(String randomStr) throws Exception {

        // 加载私钥的地址，返回私钥签名后数据
        Properties pp = new Properties();
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("privatepublicpath.properties");
        pp.load(resourceAsStream);
        String privatekeypath = pp.getProperty("privatekeypath");
        String sign = RSASignature.sign(randomStr, RSA.loadPrivateKeyByFile(privatekeypath), "UTF-8");
        return sign;
    }


    //length用户要求产生字符串的长度
    public  String getRandomString(){
        int length = new Random().nextInt(15);
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }




}