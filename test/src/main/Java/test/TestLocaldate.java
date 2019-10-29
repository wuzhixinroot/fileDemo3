package test;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestLocaldate {

    private static final String PATH="/fileserver/";
    @Test
    public void testLocalDate(){

        LocalDate LocalDate = java.time.LocalDate.now();
        System.out.println(LocalDate);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd"); //转换时间格式

        String format = LocalDate.now().format(dateTimeFormatter);// 得到当前日期时间

        System.out.println(format);
    }

    @Test
    public void testFile(){
        String filePath = "/fileserver"; // 本人电脑为mac 存放在 /fileserver目录下

        File fileroot = new File(filePath);

        for (File listFile : fileroot.listFiles()) {

            System.out.println(listFile);


        }
    }

    @Test
    public void testdir() {
        File file = new File(PATH);

        File[] files = file.listFiles();

        for (File file1 : files) {

            String dir = file1.toString().substring(file1.toString().lastIndexOf("/")+1);
            System.out.println(dir);
        }

    }

    @Test
    public void testexists(){
        System.out.println("ssss");

       // boolean wuzhixin = isExists("wuzhixn");


        //++++++++++++++++++++++

        //如果不存在目录 ，创建目录
        if(!isExists("22222")){
            File filedir = new File(PATH+"22222");

            boolean mkdir = filedir.mkdir();


        }



        //"======================="
        boolean wfw = isExists("fwfw");
      //  System.out.println(wuzhixin);

        System.out.println(wfw);
    }

    public boolean  isExists(String path){
        boolean flag = false;
        File file = new File(PATH);
        File[] files = file.listFiles();

        for (File file1 : files) {
            String dir =file1.getName();
            System.out.println(dir);
            if(path.equals(dir)){
                flag = true;
            }
        }
        return flag;
    }
}
