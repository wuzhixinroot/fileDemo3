package com.wuzhixin.service.serviceimpl;

import com.wuzhixin.service.DownLoadFileService;
import com.wuzhixin.util.GetAbsoluteDirByuuidUtil;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class DownLoadFileServiceImpl implements DownLoadFileService {

/*    private static final String PATH="/fileserver/";
    private  List <String> list= new ArrayList<>();*/

        /*文件下载*/
        public  String downloadFile(HttpServletRequest req,HttpServletResponse response,String fileName){
            String str="code=0";
            if (fileName != null) {
                //设置文件路径
                //List<String> files = this.getFiles(new File(PATH));
                //得到文件的绝对路径
                String realAbsoluteDir = GetAbsoluteDirByuuidUtil.getRealDir(fileName);
                if(null!=realAbsoluteDir) {
                    File file = new File(realAbsoluteDir);
                    System.out.println("文件下载");


                    if (file.exists()) { response.setHeader("content-type", "application/octet-stream");
                        response.setContentType("application/octet-stream");
                        try {
                            System.out.println("++++++++++++++");

                            // 解决获得中文参数的乱码
                            fileName = new String(fileName.getBytes("ISO8859-1"), "UTF-8");
                            // 获得请求头中的User-Agent
                            String agent = req.getHeader("User-Agent");
                            // 根据不同的客户端进行不同的编码
                            String filenameEncoder = "";
                            if (agent.contains("MSIE")) {
                                // IE浏览器
                                filenameEncoder = URLEncoder.encode(fileName, "utf-8");
                                filenameEncoder = filenameEncoder.replace("+", " ");
                            } else if (agent.contains("Firefox")) {
                                // 火狐浏览器
                                BASE64Encoder base64Encoder = new BASE64Encoder();
                                filenameEncoder = "=?utf-8?B?" + base64Encoder.encode(fileName.getBytes("utf-8")) + "?=";
                            } else {
                                // 其它浏览器
                                filenameEncoder = URLEncoder.encode(fileName, "utf-8");
                            }

                            response.setHeader("Content-Disposition", "attachment;filename=" + filenameEncoder);
                            System.out.println("++++++++++++++");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        byte[] buffer = new byte[1024];
                        FileInputStream fis = null;
                        BufferedInputStream bis = null;
                        try {
                            fis = new FileInputStream(file);
                            bis = new BufferedInputStream(fis);
                            OutputStream os = response.getOutputStream();
                            int i = bis.read(buffer);
                            while (i != -1) {
                                os.write(buffer, 0, i);
                                i = bis.read(buffer);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            str="code=410"; //有异常 str状态码的值发生改变。
                        } finally {
                            if (bis != null) {
                                try {
                                    bis.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (fis != null) {
                                try {
                                    fis.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }



            return str;

        }


   /* *//**
     * 根据文件目录 返回 含有uuid 的 绝对路径 集合
     * @param file
     * @return
     *//*
    public List<String> getFiles(File file) {
        File[] a = file.listFiles();
        for (File file1 : a) {
            if (file1.isDirectory()) {
                getFiles(new File(file1.getAbsolutePath()));
            } else if (file1.isFile()) {
                String substring = file1.getAbsolutePath().substring(file1.getAbsolutePath().lastIndexOf("/") + 1);
                if (substring.length()==32){
                    list.add(file1.getAbsolutePath());
                }
            }
        }
        return list;
    }



    *//**
     *  根据 uuid 返回 uuid文件名 对应的 绝对路径
     * @param uuid
     * @return
     *//*
    public String getRealDir(String uuid){
        String dir = null;
        List <String>  list = getFiles(new File(PATH));
        List <String>  list2 = new ArrayList<>();
        for (String s : list) {
            String substring = s.substring(s.lastIndexOf("/") + 1);

            if(substring.equals(uuid)){
                dir = s;
            }else{

            }

        }
        // Optional<String> first = list.stream().filter(p -> p.equals(uuid)).findFirst();
*//*
        for (String s : list2) {

            if(s.equals(uuid)){
                string =
            }

        }*//*
        return dir;
    }*/

}
