package com.wuzhixin.service.serviceimpl;

import com.wuzhixin.pojo.FileDes;
import com.wuzhixin.service.UploadFileService;
import com.wuzhixin.dao.SqlHelperUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class UploadFileServiceImpl implements UploadFileService {


    /**
     *   id  int not null GENERATED ALWAYS AS IDENTITY,
     *         f_size int,
     *         f_type varchar(20),
     *         f_name varchar(50),
     *         f_createtime varchar(20),
     *         f_dir varchar(20)
     * @param fileDes
     * @return
     */

    @Override
    public int insert(FileDes fileDes) throws SQLException {
        String sql = "insert into tab_file3 (f_id,f_size,f_type,f_name,f_createtime,f_dir)" +
                "values(?,?,?,?,?,?)";
        int issuccess = -1;
        if (fileDes!=null) {
            String[] param = new String[]{fileDes.getF_id(),
                    fileDes.getF_size().toString(), fileDes.getF_type(),
                    fileDes.getF_name(), fileDes.getF_creattime(), fileDes.getF_dir()
            };
             issuccess = SqlHelperUtil.executeUpdate(sql, param);
        }else {
            throw new RuntimeException("上传文件为空");
        }
       // System.out.println(connection);
        return issuccess;
    }

    @Override
    public FileDes quetyById(int id) throws Exception {

        String sql = "select f_id,f_size,f_type,f_name,f_createtime,f_dir from tab_file3";
        ArrayList arrayList = SqlHelperUtil.executeQuery2(sql, new String[]{});

        Object o[] = (Object[])arrayList.get(0);

      /*  FileDes fileDes = new FileDes();
        for (int i = 0; i < o.length; i++) {

        }*/
        System.out.println(Arrays.toString(o));
        FileDes fileDes = new FileDes(o[0].toString(), Integer.parseInt(o[1].toString()), o[2].toString(), o[3].toString(), o[4].toString(),o[5].toString());
        return fileDes;
    }


}
