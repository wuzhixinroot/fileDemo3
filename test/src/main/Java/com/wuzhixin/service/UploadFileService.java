package com.wuzhixin.service;

import com.wuzhixin.pojo.FileDes;

import java.io.File;
import java.sql.SQLException;

public interface UploadFileService {

    int insert(FileDes fileDes) throws SQLException;

    FileDes quetyById(int id) throws Exception;
}
