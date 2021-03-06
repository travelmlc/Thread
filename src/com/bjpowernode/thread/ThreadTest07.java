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
 * 不需要，因为doOther上面没有synchronized关键字，表示该方法没有被锁，
 *  执行的时候不需要获取对象的对象锁
 */
public class ThreadTest07 {

    public static void main(String[] args) {
        MyClass mc = new MyClass();

        Thread t1 = new MyThread(mc);
        Thread t2 = new MyThread(mc);

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

class MyThread extends Thread{
    private MyClass mc;

    public MyThread(MyClass mc) {
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

class MyClass {

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

    public void doOther(){
        System.out.println("doOther before");
        System.out.println("doOther after");
    }
}
