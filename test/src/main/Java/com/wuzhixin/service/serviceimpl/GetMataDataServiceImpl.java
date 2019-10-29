package com.wuzhixin.service.serviceimpl;

import com.wuzhixin.pojo.FileDes;
import com.wuzhixin.service.GetMetaDataService;
import com.wuzhixin.dao.SqlHelperUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetMataDataServiceImpl implements GetMetaDataService {
    /**
     * f_size;
     *     private String  f_type;
     *     private String  f_name;
     *     private String  f_creatt
     */

    /**
     * 根据uuid 返回
     * @param uuid
     * @return
     * @throws SQLException
     */
    @Override
    public FileDes getMataData(String uuid) throws SQLException {
        String sql = "select f_id,f_size,f_type,f_name,f_createtime,f_dir from tab_file3 where f_id = ?";
     //   uuid = GetAbsoluteDirByuuidUtil.getRealDir(uuid); //得到uuid
        //uuid = uuid.substring(0,uuid.lastIndexOf("/")+1); // 得到目录

      //  System.out.println(uuid);

        ArrayList arrayList = SqlHelperUtil.executeQuery2(sql, new String[]{uuid});
        Object o[] = (Object[])arrayList.get(0);

      /*  FileDes fileDes = new FileDes();
        for (int i = 0; i < o.length; i++) {

        }*/
        System.out.println(Arrays.toString(o));
        FileDes fileDes = new FileDes(o[0].toString(), Integer.parseInt(o[1].toString()), o[2].toString(), o[3].toString(), o[4].toString(),o[5].toString());
        return fileDes;
       // FileDes fileDes = SqlHelperUtil.getByuuid(sql, uuid);
       // return fileDes;
    }

    @Override
    public FileDes getlastMessage() throws Exception {
        String sql = "select id,f_id,f_size,f_type,f_name,f_createtime,f_dir from tab_file3 order by id desc OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY";
       // String sql ="select * FROM (select ROW_NUMBER() OVER() AS R,id,f_id,f_size,f_type,f_name,f_createtime,f_dir) AS T where R>0 and R<2 order by id desc";
        //String sql="select row_number()over() as r,id,f_id,f_size,f_type,f_name,f_createtime,f_dir where r>0 and r<2 order by id desc";
        ArrayList arrayList = SqlHelperUtil.executeQuery2(sql, new String[]{});
        Object []file = (Object[]) arrayList.get(0);
        FileDes fileDes =  new FileDes(file[0].toString(),file[1].toString(),Integer.parseInt(file[2].toString()),
                file[3].toString(),file[4].toString(),file[5].toString(),file[6].toString());
        return fileDes;
    }

    public List<FileDes> getTenFileRecord(){
        String sql ="select * from tab_file3 order by id desc OFFSET 0 ROWS FETCH NEXT 10 ROWS ONLY";
        List<FileDes> fileDesList = new ArrayList<>();
        ArrayList arrayList = SqlHelperUtil.executeQuery2(sql,new String[]{});

        for (int i=0;i<arrayList.size();i++) {
            Object[] file = (Object[]) arrayList.get(i);
            FileDes fileDes =  new FileDes(file[0].toString(),file[1].toString(),Integer.parseInt(file[2].toString()),
                    file[3].toString(),file[4].toString(),file[5].toString(),file[6].toString());
           fileDesList.add(fileDes);

        }

        return fileDesList;
    }
}
