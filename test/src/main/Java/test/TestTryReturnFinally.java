package test;

public class TestTryReturnFinally {

    public static String test(){
        String a = "123";
        try {
            return a;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            a = "456";
            System.out.println("finally 运行");
        }
        return "null";
    }
    public static void main(String[] args) {
        System.out.println(test());
    }
}
