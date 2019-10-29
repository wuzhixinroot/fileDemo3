package test;

public class Test2 {
    public static void main(String[] args) {

      /*  String str1="hello";
        String str2 = " i love java";

        System.out.println(new AB(str1,str2));
        System.out.println(str2);*/
        Object  O1 = 3;
        Object o2 = new Integer(3);
        Object o3 = new Integer[3];
        Object o4 = new int[3];
        Object o5 = new Object();
        Object o6 = new Object[3];

        float a =10.0f;
        double b = 10.0;
        int c = 10 ;
        Double d = b;
        Float e =a;
        System.out.println(a==b);
        System.out.println(a==d);
        System.out.println(a==c);
        System.out.println(e==b);
        System.out.println(e.equals(d));


    }




}

class AB{
    String s1;
    String s2;

    AB(String str1,String str2){
        s1=str1;
        s2=str2;
        str2+=str1;
    }

    public String toString(){
        return s1+s2;
    }
}
