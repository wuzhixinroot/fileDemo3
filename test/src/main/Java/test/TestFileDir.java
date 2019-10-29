package test;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TestFileDir {
    private static final String PATH="/fileserver/";
    List <String> list= new ArrayList<>();
    @Test
    public void test(){
        File file = new File(PATH);

        List<String> files1 = getFiles(file);

        for (String s : files1) {
            System.out.println(s);
        }



    }

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
}
