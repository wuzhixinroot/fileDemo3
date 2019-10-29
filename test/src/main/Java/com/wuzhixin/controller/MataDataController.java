package com.wuzhixin.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wuzhixin.pojo.FileDes;
import com.wuzhixin.service.GetMetaDataService;
import com.wuzhixin.service.serviceimpl.GetMataDataServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MataDataController {

    private GetMetaDataService  getMetaDataService = new GetMataDataServiceImpl();

    @RequestMapping(value = "/getMataData",method = RequestMethod.POST,produces={"text/plain;charset=UTF-8"})
    public String getData(@RequestParam("uuid")String uuid , HttpServletResponse res, HttpServletRequest req) throws JsonProcessingException, SQLException {
        Map<String,String> map = new HashMap<>();
        FileDes mataData = getMetaDataService.getMataData(uuid);
        map.put("f_id",mataData.getF_id().toString());
        map.put("f_size",mataData.getF_size().toString());
        map.put("f_type",mataData.getF_type());
        map.put("f_name",mataData.getF_name());
        map.put("f_createtime",mataData.getF_creattime());
        map.put("f_dir",mataData.getF_dir());
        ObjectMapper objectMapper = new ObjectMapper();
       return objectMapper.writeValueAsString(map);
    }


    @RequestMapping(value = "/showMessage",method = RequestMethod.POST,produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String getMessage() throws Exception {

        Map<String,String> datamap = new HashMap();
        FileDes mataData = getMetaDataService.getlastMessage();
        datamap.put("f_id",mataData.getF_id());
        datamap.put("f_size",mataData.getF_size().toString());
        datamap.put("f_type",mataData.getF_type());
        datamap.put("f_name",mataData.getF_name());
        datamap.put("f_createtime",mataData.getF_creattime());
        datamap.put("f_dir",mataData.getF_dir());
        String s = new ObjectMapper().writeValueAsString(datamap);
        return s;
    }
    @RequestMapping(value = "/getTenRecord",method = RequestMethod.GET,produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String getTenFileMataData() throws Exception {
        List<FileDes> list = new ArrayList<>();
        List<FileDes> tenFileRecord = getMetaDataService.getTenFileRecord();
       // StringBuilder sb = new StringBuilder();
        String record = new ObjectMapper().writeValueAsString(tenFileRecord);
        StringBuffer  stringBuffer = new StringBuffer("{\"code\":0,\"count\":"+tenFileRecord.size()+",\"data\":");
        return stringBuffer.append(record).append("}").toString();
    }




}
