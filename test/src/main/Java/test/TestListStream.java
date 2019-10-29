package test;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestListStream {

    private static final String PATH="/fileserver/";
    List <String> list= new ArrayList<>();

    @Test
    public void testStream(){

        List<String> list = getFiles(new File(PATH));
        //list.forEach(System.out::println);
                                          //8590abb593c34112abe8a966e947774f

        List <String>  list2 = new ArrayList<>();

        for (String s : list) {

            list2.add(s.substring(s.lastIndexOf("/")+1));
        }

        list2.stream().filter(p ->





        p.equals("8590abb593c34112abe8a966e947774f"))
                .forEach(System.out::println);
//                .collect(Collectors.toList());


      //  System.out.println(first);
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
