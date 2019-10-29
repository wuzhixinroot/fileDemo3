package com.wuzhixin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wuzhixin.service.DownLoadFileService;
import com.wuzhixin.service.serviceimpl.DownLoadFileServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Controller
public class DownLoadController {

    private DownLoadFileService downLoadFileService = new DownLoadFileServiceImpl();

    @RequestMapping(value = "/downLoad" ,produces={"text/plain;charset=UTF-8"})
    @ResponseBody
    public String downLoad(@RequestParam("uuid")String uuid, HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, UnsupportedEncodingException {

       // res.setContentType("utf-8");
        String fileName = uuid;
      String str=  downLoadFileService.downloadFile(req,res,uuid);
      String result="";
        ObjectMapper objectMapper = new ObjectMapper();

      if(str.split("=")[1].equals("0")) {
          // downloadFile(req,res,fileName); // 传入文件名 得到绝对路径
          Map<String, String> map = new HashMap<>();
          map.put("msg", new String("下载成功".getBytes("ISO8859-1"), "UTF-8"));
          result= objectMapper.writeValueAsString(map);
      }else{
          result= objectMapper.writeValueAsString(new HashMap<String,String>().put("code",str.split("=")[0]));
      }
        return result;
    }

    /**
     * 检索列表中下载附件
     * 传入参数：@param uploadFile
     * 传入参数：@param request
     * 返回类型：void
     */


    public String getdirByUUID(String uuid){

        String dir = null;

        return dir;
    }

}
