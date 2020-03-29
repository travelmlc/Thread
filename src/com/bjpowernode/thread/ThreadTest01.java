package com.bjpowernode.thread;

/**
 * ClassName:ThreadTest01
 * Package:com.bjpowernode.thread
 * Description:
 *
 * @Date:2020/3/28 17:20
 * @Author:mlc17607163664@163.com
 */
public class ThreadTest01 {

    public static void main(String[] args) {
        //因为A类继承了Thread类，所以A类就是一个线程类
        A a = new A();

        //开启线程
        /*
            start方法的作用是：启动一个分支线程，在JVM中开辟一个新的空间，这段代码任务完后，瞬间就结束了
            这段代码的任务只是为了开辟一个新的栈空间，只要新的栈空间开出来，start方法就结束了，线程就启动成功了。
            启动成功的线程会自动调用run方法，并且run方法在分支栈的底部(压栈)
            run方法在分支栈的栈底部，main方法在主方法的栈底部，run和main是平级的。
         */
        a.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("主线程" + i);
        }
    }
}

class A extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("分支线程" + i);
        }
    }
}
