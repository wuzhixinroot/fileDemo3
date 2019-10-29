package test;

import com.wuzhixin.dao.SqlHelperUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {
    @Test
    public void testConnection() throws SQLException {

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
                "create table tab_file1(\n" +
                "        id  int not null GENERATED ALWAYS AS IDENTITY,\n" +
                "        f_type varchar(20),\n" +
                "        f_name varchar(50),\n" +
                "        f_createtime varchar(20),\n" +
                "        f_dir varchar(20)\n" +
                ")"
        };

        Connection conn = SqlHelperUtil.getConnection();
        // 开启事物
        conn.setAutoCommit(false);
        Statement stmt = conn.createStatement();
        for (int i = 0; i < sqls.length; i++) {
            stmt.addBatch(sqls[i]);  //批处理
        }
        int[] r = stmt.executeBatch();
        System.out.println(r);
        conn.commit();

        //提交事物
        conn.close();
    }
}
