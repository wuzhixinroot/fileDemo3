package com.wuzhixin.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wuzhixin.pojo.FileDes;
import com.wuzhixin.service.GetMetaDataService;
import com.wuzhixin.service.UploadFileService;
import com.wuzhixin.service.serviceimpl.GetMataDataServiceImpl;
import com.wuzhixin.service.serviceimpl.UploadFileServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Controller
@MultipartConfig
public class UpLoadController {

    private static final String PATH="/fileserver/";
    private UploadFileService uploadFileService =new UploadFileServiceImpl(); //文件上传接口
    private GetMetaDataService getMetaDataService = new GetMataDataServiceImpl();
    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST,produces={"text/plain;charset=UTF-8"})
    @ResponseBody
    public String handleFile(@RequestParam("file") MultipartFile file, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws Exception {
        //如果文件内容不为空，则写入上传路径
        //uuid
      /*  Connection connection = SqlHelper.getConnection();
        System.out.println(connection);*/
        //  System.out.println(UUID);
        //String str = "";
       /* JSONObject res = new JSONObject();
        JSONObject resUrl = new JSONObject();*/


        //上传文件路径
        String path = servletRequest.getServletContext().getRealPath("/uploadFile");
        String UUID = java.util.UUID.randomUUID().toString().replace("-","");
        //上传文件名
        String name = file.getOriginalFilename();//上传文件的真实名称
        String suffixName = name.substring(name.lastIndexOf("."));//获取后缀名
        Integer size =(int) file.getSize(); //获取文件大小
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd"); //转换时间格式
        LocalDate now = LocalDate.now();
        String format = now.format(dateTimeFormatter);// 得到当前日期时间
        String realName = UUID/*+format+suffixName*/; //得到最终要保存的文件名
        //System.out.println(realName);
        File filedir = new File(PATH+format); //文件目录
        File realFilePath = new File(filedir,realName); //文件名字


        //如果不存在目录 ，创建目录
        if(!isExists(format)){
            boolean mkdir = filedir.mkdir();
            if(mkdir){
                try {
                    file.transferTo(realFilePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{ //存在 将文件放入该目录下
            file.transferTo(realFilePath);
        }

        FileDes fileDes  = new FileDes(UUID,size,suffixName,name,now.toString(),PATH+format);



        int issuccess = uploadFileService.insert(fileDes);


        FileDes  mataData=null;
        //插入成功
        if(issuccess==1){
            mataData = getMetaDataService.getMataData(UUID);

        }else{

        }
        //FileDes f = uploadFileService.quetyById(1);
        // 输出结果[1, 62464, .doc, 附件1：海南师范大学毕业生就业推荐表（适用本科生）.doc, 2019-10-18, /fileserver/20191018]
        //System.out.println(f);

        String str = "";

        Map<String,String> datamap = new HashMap<>();
        datamap.put("f_id",mataData.getF_id());
        datamap.put("f_size",mataData.getF_size().toString());
        datamap.put("f_type",mataData.getF_type());
        datamap.put("f_name",mataData.getF_name());
        datamap.put("f_createtime",mataData.getF_creattime());
        datamap.put("f_dir",mataData.getF_dir());

        ObjectMapper objectMapper = new ObjectMapper();

       /* str="{\n" +
                "  \"code\":" +"\""+0+"\""+"\n"+
                "  ,\"msg\": \"\"\n" +
                "  ,\"data\": {\n" +
                "    \"uuid\":" +"\""+UUID+"\""+
                "  }\n" +
                "}    ";*/
      /*  Cookie uuid = new Cookie("uuid", UUID);
        uuid.setMaxAge(1000*60*60*24*15);
        uuid.setPath("/");
        servletResponse.addCookie(uuid);*/
      Map<String,Object> map = new HashMap<>();

      map.put("code","0");
      map.put("msg","上传成功");
      map.put("data",datamap);

       // StringBuilder sb= new StringBuilder();
        str = "{\"code\": 0,\"msg\": \"上传成功\",\"data\": {\"uuid\":\""+UUID + "\"}}";


        //return str;

        return objectMapper.writeValueAsString(map);
    }

    //处理文件
    public void hanle(){

    }



    //判断目录是否已经存在 可能存在 线程安全问题
    public  boolean isExists(String path){
        boolean flag = false;
        File file = new File(PATH);
        File[] files = file.listFiles(); //得到所有的目录
        for (File file1 : files) {
            String dir =file1.getName();
            if(path.equals(dir)){
                flag = true;
            }
        }
        return flag;
    }

}
