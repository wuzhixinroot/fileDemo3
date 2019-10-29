package com.wuzhixin.dao;

import com.wuzhixin.pojo.FileDes;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;


public class SqlHelperUtil {
    // 连接 结果集 与执行 对象
    private static Connection conn = null;
    private static ResultSet rs = null;
    private static PreparedStatement ps = null;

   // private static boolean flag = true;

    //连接对象所需要参数
    private static String driver = "";
    private static String url = "";
    private static String username = "";
    private static String pwd = "";

    static Properties pp = null;
    static InputStream fis = null;

    //只加载一次

    static {
        try {
            pp = new Properties();

            //加载配置文件
            try {
                fis = SqlHelperUtil.class.getClassLoader().getResourceAsStream("derby.properties");
                pp.load(fis);
                url = pp.getProperty("jdbc.url");
               // username = pp.getProperty("jdbc.username");
               // pwd = pp.getProperty("jdbc.password");
                driver = pp.getProperty("jdbc.driver");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Class.forName(driver).newInstance();

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //得到连接
    public static Connection getConnection() {
      //  System.out.println("aabbccdd");
        try {
            conn = DriverManager.getConnection(url+";create=true");
            //if(flag)
           // initTab(conn); // 初始化表

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    public static void initTab(Connection conn) throws SQLException {
        String[] sqls = {
                /*    "create table tab_file(\n" +
                            "        id  int not null GENERATED ALWAYS AS IDENTITY,\n" +
                            "        f_type varchar(20) default \"\",\n" +
                            "        f_name varchar(50) default \"\",\n" +
                            "        f_createtime varchar(20) default \"\",\n" +
                            "        f_dir varchar(20) default \"\"\n" +
                            ")"
                    ,*//* "create table tab_file(\n" +
                "        id  int not null GENERATED ALWAYS AS IDENTITY,\n" +
                "        f_type varchar(20) default \"\",\n" +
                "        f_name varchar(50) default \"\",\n" +
                "        f_createtime varchar(20) default \"\",\n" +
                "        f_dir varchar(20) default \"\"\n" +
                ")",*/
                "create table if not exists tab_file3(\n" +
                        "        id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)primary key,\n"+
                        "        f_id  varchar(50),\n" +
                        "        f_size varchar(50),\n" +
                        "        f_type varchar(20),\n" +
                        "        f_name varchar(50),\n" +
                        "        f_createtime varchar(20),\n" +
                        "        f_dir varchar(20)\n" +
                        ")"
        };
        // 开启事物
        conn.setAutoCommit(false);
        try {
            Statement stmt = conn.createStatement();
            for (int i = 0; i < sqls.length; i++) {
                stmt.addBatch(sqls[i]);  //批处理
            }
            int[] r = stmt.executeBatch();
            System.out.println(r);
            conn.commit();
        }catch (SQLException e){
            System.out.println("表已存在");
            conn.rollback();
        }
        //flag=false;
        //提交事物
       // conn.close();
    }
    //更新数据
    public static int executeUpdate(String sql, String[] parameters) {
        int issuccess = -1;
        try {
            conn = SqlHelperUtil.getConnection();//得到连接
            ps = conn.prepareStatement(sql);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    ps.setString(i + 1, parameters[i]);
                    System.out.println("hdfowhfw");
                }
            }
            issuccess = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            close(rs, ps, conn);
        }

        return issuccess;
    }
    /**
     * 传递多个参数，多条sql 事务提交
     * @param sql
     * @param parameters
     */
    public static void executeUpdate2(String sql[], String[][]parameters) {
        conn = getConnection();//得到连接
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (int i = 0; i < sql.length; i++) {
            try {
                ps = conn.prepareStatement(sql[i]);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (parameters[i] != null) {
                for (int j = 0; j < parameters[i].length; j++) {
                    try {
                        ps.setString(j + 1, parameters[i][j]);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            try {
                ps.executeUpdate();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            conn.commit();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    /**
     * 根据参数 获取 结果集
     */
    public static ResultSet executeQuery(String sql, String[] parameters) {
        conn = SqlHelperUtil.getConnection();//得到连接
        try {
            ps = conn.prepareStatement(sql);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    ps.setString(i + 1, parameters[i]);
                }
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //close(rs,ps,conn);
        }
        return rs;
    }
    /**
     * 根据uuid获取一个对象
     */
    public static FileDes getByuuid(String sql,String uuid) throws SQLException {
        FileDes fileDes = new FileDes();
        Connection conn = SqlHelperUtil.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,uuid);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet==null){
            throw  new RuntimeException("没有结果集");
        }
        while (resultSet.next()) {
            fileDes.setF_id(resultSet.getString(1));
            fileDes.setF_size(Integer.parseInt(resultSet.getString(2)));
            fileDes.setF_type(resultSet.getString(3));
            fileDes.setF_name(resultSet.getString(4));
            fileDes.setF_creattime(resultSet.getString(5));
            fileDes.setF_dir(resultSet.getString(6));
        }
            return fileDes;

     /*   return new FileDes(resultSet.getInt(1),
                           Integer.parseInt(resultSet.getString(2)),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                );*/
    }



    /**
     * 根据参数获取 结果集合
     */


    public static ArrayList executeQuery2(String sql, String[] parameters) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        connection = SqlHelperUtil.getConnection();

        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                try {
                    preparedStatement.setObject(i + 1, parameters[i]);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ArrayList list = new ArrayList();

        //ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        try {
            int counts = resultSet.getMetaData().getColumnCount();//
            while (resultSet.next()) {
                Object[] object = new Object[counts];
                for (int i = 1; i <= counts; i++) {
                    object[i - 1] = resultSet.getObject(i);
                }

                list.add(object);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            SqlHelperUtil.close(resultSet, preparedStatement, connection);
        }

        return list;
    }

    //存储过程
    public static void callPro1(String sql, String[] parameters) {
        conn = SqlHelperUtil.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {

                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //关闭连接
    public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            rs = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            conn = null;
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            ps = null;
        }
    }
}
