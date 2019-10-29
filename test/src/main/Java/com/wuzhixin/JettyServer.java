package com.wuzhixin;

import com.wuzhixin.dao.SqlHelperUtil;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;


public class JettyServer {

 /*   public static void main(String[] args) throws Exception {
*//*
            Server server = new Server(9999);

            ResourceHandler resourceHandler = new ResourceHandler();
          //  resourceHandler.setResourceBase("D:/test");

            server.setHandler(resourceHandler);

            server.start();*//*

       String host = "127.0.0.1";
       String port = "9999";
        InetSocketAddress address = new InetSocketAddress(host, Integer.parseInt(port));
        // 新建web服务器
        Server server = new Server(address);
        // 添加自定义的Servlet
       // ServletContextHandler handler = new ServletContextHandler();

        ServletContextHandler context = new WebAppContext("src/main/webapp", "/");
        context.setMaxFormContentSize(900000);
       // handler.addServlet(BaseServlet.class, "/base");
        server.setHandler(context);

        // 启动web服务器
        server.start();


        }*/


    public static void main(String[] args) throws Exception {
        Server server = new Server(9999);

        String webAppPath ="src/main/webapp";
       WebAppContext webAppContext = new WebAppContext("WebContent","/");
        webAppContext.setContextPath("/");
        webAppContext.setDescriptor(webAppPath+"/WEB-INF/web.xml");
        webAppContext.setResourceBase(webAppPath);
        webAppContext.setDisplayName("test");
        webAppContext.setClassLoader(Thread.currentThread().getContextClassLoader());
        webAppContext.setConfigurationDiscovered(true);
        webAppContext.setParentLoaderPriority(true);
        server.setHandler(webAppContext);
        System.out.println(webAppContext.getContextPath());
        System.out.println(webAppContext.getDescriptor());
        System.out.println(webAppContext.getResourceBase());
        System.out.println(webAppContext.getBaseResource());

        SqlHelperUtil.initTab(SqlHelperUtil.getConnection());

        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("server is  start");
    }
}

