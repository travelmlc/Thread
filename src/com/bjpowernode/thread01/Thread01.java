package com.bjpowernode.thread01;

/**
 * ClassName:Thread01
 * Package:com.bjpowernode.thread01
 * Description:
 *
 * @Date:2020/3/29 20:41
 * @Author:mlc17607163664@163.com
 *
 * 死锁:不出现异常，也不出现错误，程序锁死，这种错误最难调试
 * synchronized在开发中最好不要嵌套使用，一不小心就会出现死锁
 */
public class Thread01 {

    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();

        //t1,t2两个线程共享o1,o2
        Thread t1 = new MyThread1(o1,o2);
        Thread t2 = new MyThread2(o1,o2);
        t1.setName("t1");
        t1.setName("t2");

        t1.start();
        t2.start();
    }
}

class MyThread1 extends Thread{

    Object o1;
    Object o2;

    public MyThread1(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public void run() {
        synchronized (o1){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o2){

            }
        }
    }
}

class MyThread2 extends Thread{

    Object o1;
    Object o2;

    public MyThread2(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public void run() {
        synchronized (o2){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o1){

            }
        }
    }
}



