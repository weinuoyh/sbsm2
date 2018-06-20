package com.sxt.Test;

/**
 * Created by IntelliJ IDEA.
 * User: shaxueting
 * Date: 2018/6/15
 * Time: 13:03
 * 描述:  com.sxt.Test
 */
public class testJava {
    public static void main(String[] args) {
        String s1 = "helloworld";
        String s2 = new String("helloworld");
        final String s3 = "hello";
        String s4 = s3 + "world";
        String s5 = new String("hello") + "world";
        String s6 = "hello" + "world";
        System.out.println(s1 == s2);
        System.out.println(s1 == s4);
        System.out.println(s1 == s5);
        System.out.println(s1 == s6);
        Integer a = 1;
        Integer b = 1;
        Integer c = 200;
        Integer d = 200;
        System.out.println(a == b);
        System.out.println(c == d);
        System.out.println(c.equals(d));


        int n = 1;
        for (int i = 1; i <= 9; i++) {
            n = (n + 1) * 2;
            System.out.println(n);
        }
        int nb = 3070;
        for (int i = 1; i <= 9; i++) {
            nb = nb / 2 - 1;
            System.out.println(nb);
        }
        int num = dg(10);

    }

    static int dg(int i) {
        if(i==10){return 1;}
        return (dg(i+1)+1)*2;
    }
}
