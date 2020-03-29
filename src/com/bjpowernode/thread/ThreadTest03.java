package com.bjpowernode.thread;

/**
 * ClassName:ThreadTest03
 * Package:com.bjpowernode.thread
 * Description:
 *
 * @Date:2020/3/28 22:00
 * @Author:mlc17607163664@163.com
 */
public class ThreadTest03 {

    public static void main(String[] args) {

        Thread t = new Person();
        t.setName("t");
        t.start();

        //调用sleep方法
        try {
            //问题：这行代码会让t线程休眠5秒吗？
            //不会，因为sleep是静态方法，底层用的是Thread.sleep调用

            t.sleep(1000 * 5);//这行代码的作用是让当前线程休眠，main方法会休眠5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程休眠5秒");
    }
}

class Person extends Thread{

    public void run(){
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "-->" + i);
        }
    }
}
