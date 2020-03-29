package com.bjpowernode.thread;

/**
 * ClassName:ThreadTest07
 * Package:com.bjpowernode.thread
 * Description:
 *
 * @Date:2020/3/29 14:10
 * @Author:mlc17607163664@163.com
 *
 * 问题：doOther的执行需不需要等待doSome？
 * 需要，因为synchronized出现在静态方法上是找类锁，类锁永远只有一把
 */
public class ThreadTest10 {

    public static void main(String[] args) {
        MyClass4 mc1 = new MyClass4();
        MyClass4 mc2 = new MyClass4();

        Thread t1 = new MyThread4(mc1);
        Thread t2 = new MyThread4(mc2);

        t1.setName("t1");
        t2.setName("t2");

        t1.start();
        try {
            //这个睡眠的作用是为了保证t1线程先执行
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}

class MyThread4 extends Thread{
    private MyClass4 mc;

    public MyThread4(MyClass4 mc) {
        this.mc = mc;
    }

    @Override
    public void run() {
        if("t1".equals(Thread.currentThread().getName())){
            mc.doSome();
        }
        if("t2".equals(Thread.currentThread().getName())){
            mc.doOther();
        }
    }
}

class MyClass4 {

    //synchronized出现在静态方法上是找类锁，而类锁永远只有一把
    public synchronized static void doSome(){
        System.out.println("doSome before");
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("doSome after");
    }

    public static synchronized void doOther(){
        System.out.println("doOther before");
        System.out.println("doOther after");
    }
}
