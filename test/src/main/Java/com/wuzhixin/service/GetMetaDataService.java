package com.wuzhixin.service;

import com.wuzhixin.pojo.FileDes;

import java.sql.SQLException;
import java.util.List;

public interface GetMetaDataService {

    FileDes getMataData(String uuid) throws SQLException;

    FileDes getlastMessage() throws Exception;

     List<FileDes> getTenFileRecord() throws Exception;
}
