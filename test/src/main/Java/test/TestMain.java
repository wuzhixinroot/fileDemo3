package test;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.jupiter.api.Test;

public class TestMain {

    public static void main(String[] args) {
      A a = new B();
    }
}

class A{
    int a = 3;
    public A(){
        if(a==5){
            printA();
        }
    }
    public void printA(){
        System.out.println(a);
    }
}
class B extends A{
    int a = 5;
    public B(){
        printA();
    }

    public void printA(){
        System.out.println(a);
    }
}
