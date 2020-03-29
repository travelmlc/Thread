package com.bjpowernode.thread;

/**
 * ClassName:ThreadTest02
 * Package:com.bjpowernode.thread
 * Description:
 *
 * @Date:2020/3/28 17:49
 * @Author:mlc17607163664@163.com
 */
public class ThreadTest02 {

    public static void main(String[] args) {

        /*Thread t = new Thread(new Runnable() {
            //匿名内部类
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("分支线程" + i);
                }
            }
        });*/

        //lambda表达式
        Thread t = new Thread(()->{
                for (int i = 0; i < 100; i++) {
                    System.out.println("分支线程" + i);
                }
        });

        t.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("主线程" + i);
        }
    }
}
