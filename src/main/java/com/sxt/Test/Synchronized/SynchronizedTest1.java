package com.sxt.Test.Synchronized;

/**
 * Created by IntelliJ IDEA.
 * User: shaxueting
 * Date: 2018/6/19
 * Time: 13:59
 * 描述:  com.sxt.Test.Synchronized
 * 线程2和线程1的method1分开执行完成
 */
public class SynchronizedTest1 {
    public  void method1(){
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }

    public  void method2(){
        System.out.println("Method 2 start");
        try {
            System.out.println("Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }

    public static void main(String[] args) {
        final SynchronizedTest1 test = new SynchronizedTest1();
        final SynchronizedTest1 test2 = new SynchronizedTest1();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test2.method2();
            }
        }).start();
    }

}
