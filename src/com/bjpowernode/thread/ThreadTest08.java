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
 * 需要，因为doOther方法上含有synchronized，表示锁的是this，也就是mc,
 * 当t1拿走了对象锁，t2找不到对象锁，只能等待
 */
public class ThreadTest08 {

    public static void main(String[] args) {
        MyClass2 mc = new MyClass2();

        Thread t1 = new MyThread2(mc);
        Thread t2 = new MyThread2(mc);

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

class MyThread2 extends Thread{
    private MyClass2 mc;

    public MyThread2(MyClass2 mc) {
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

class MyClass2 {

    //synchronized出现在实例对象上，锁的是this，也就是mc
    public synchronized void doSome(){
        System.out.println("doSome before");
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("doSome after");
    }

    public synchronized void doOther(){
        System.out.println("doOther before");
        System.out.println("doOther after");
    }
}
