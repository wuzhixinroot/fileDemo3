package com.wuzhixin.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetAbsoluteDirByuuidUtil {

    private static final String PATH="/fileserver/";
    private  static final List <String> list= new ArrayList<>();

    /**
     * 根据文件目录 返回 含有uuid 的 绝对路径 集合
     * @param file
     * @return
     */
    public static List<String> getFiles(File file) {
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



    /**
     *  根据 uuid 返回 uuid文件名 对应的 绝对路径
     * @param uuid
     * @return
     */
    public static String getRealDir(String uuid){
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
/*
        for (String s : list2) {

            if(s.equals(uuid)){
                string =
            }

        }*/
        return dir;
    }
}
