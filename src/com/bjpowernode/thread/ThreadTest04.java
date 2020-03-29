package com.bjpowernode.thread;

/**
 * ClassName:ThreadTest04
 * Package:com.bjpowernode.thread
 * Description:
 *
 * @Date:2020/3/28 22:10
 * @Author:mlc17607163664@163.com
 *
 * 线程的唤醒
 */
public class ThreadTest04 {

    public static void main(String[] args) {
        Thread t = new Thread(new Test01());
        t.setName("t");
        t.start();

        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //终止t线程的休眠，interrupt靠的是异常处理机制
        t.interrupt();
    }
}

class Test01 implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "开始");
            Thread.sleep(1000 * 60 * 60 * 24 * 365);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束");
    }
}
