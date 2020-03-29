package com.bjpowernode.thread02;

/**
 * ClassName:Thread01
 * Package:com.bjpowernode.thread02
 * Description:
 *
 * @Date:2020/3/29 21:10
 * @Author:mlc17607163664@163.com
 *
 * 守护线程
 */
public class Thread01 {

    public static void main(String[] args) {
        Thread thread = new MyThread();
        thread.setName("备份线程");

        //启动线程之前，将备份线程设置为守护线程
        thread.setDaemon(true);

        thread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "主线程-->" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyThread extends Thread{

    @Override
    public void run() {
        int i = 0;
        while (true){
            System.out.println(Thread.currentThread().getName() + "-->" + (i++));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}