package com.bjpowernode.thread02;

/**
 * ClassName:Thread05
 * Package:com.bjpowernode.thread02
 * Description:
 *
 * @Date:2020/3/30 11:10
 * @Author:mlc17607163664@163.com
 *
 * 两个线程交替打印数字
 */
public class Thread05 {
    public static void main(String[] args) throws InterruptedException {

        Num count = new Num(1);
        Thread t1 = new Thread(new GS(count));
        Thread t2 = new Thread(new OS(count));
        t1.setName("奇数线程");
        t2.setName("偶数线程");
        t1.start();
        Thread.sleep(1000);
        t2.start();
    }
}

class Num{
    private int count;

    public Num(int count) {
        this.count = count;
    }

    public void gs() {
        //打印奇数
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + "--->" + (count++));
            this.notifyAll();
            try {
                Thread.sleep(1000);
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void os() {
        //打印偶数
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + "--->" + (count++));
            this.notifyAll();
            try {
                Thread.sleep(1000);
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class GS implements Runnable {
    private Num count;

    public GS(Num count) {
        this.count = count;
    }

    @Override
    public void run() {
        while (true){
            count.gs();
        }
    }
}

class OS implements Runnable {
    private Num count;

    public OS(Num count) {
        this.count = count;
    }

    @Override
    public void run() {
        while (true){
            count.os();
        }
    }
}

