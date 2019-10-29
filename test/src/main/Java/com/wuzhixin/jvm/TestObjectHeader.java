package com.wuzhixin.jvm;

import org.openjdk.jol.info.ClassLayout;

import javax.xml.transform.Source;

public class TestObjectHeader {

  static   A a = new  A();

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }

    public static void print(){
        synchronized (a){

            System.out.println("xxxxx");
        }
    }


}





class A{
    private short aShort ;
}
