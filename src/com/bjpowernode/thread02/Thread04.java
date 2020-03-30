package com.bjpowernode.thread02;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:Thread04
 * Package:com.bjpowernode.thread02
 * Description:
 *
 * @Date:2020/3/30 10:24
 * @Author:mlc17607163664@163.com
 * 1、使用wait和notify方法实现“生产者和消费者模式”
 *
 * 2、什么是“生产者和消费者模式”？
 *      生产者负责生产，消费者负责消费
 *      生产线程和消费线程要达到均衡
 *      这是一种特殊的业务需求，在这种特殊情况下要使用wait和notify方法
 *
 * 3、wait和notify方法不是线程对象上的方法，是普通java对象都有的方法
 *
 * 4、wait和notify方法建立在线程同步的基础上，因为多线程要同时操作一个仓库，有线程安全问题。
 *
 * 5、wait方法的作用：o.wait()让正在o对象上活动的线程t进入等待状态，并且释放t线程之前占用的o对象的锁。
 *
 * 6、notify方法的作用：o.notify()让正在o对象上等待的线程唤醒，只是通知，不会释放o对象上之前占用的锁。
 *
 */
public class Thread04 {

    public static void main(String[] args) {
        List list = new ArrayList();
        Thread t1 = new Thread(new Product(list));
        Thread t2 = new Thread(new Consumer(list));

        t1.setName("生产者线程");
        t2.setName("消费者线程");

        t1.start();
        t2.start();
    }
}

//生产者线程
class Product implements Runnable{

    private List list;

    public Product(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true){
            synchronized (list){
                if(list.size() > 0){
                    //当仓库已经满了，生产者线程进入等待状态
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //程序能执行到这，说明list.size()=0，仓库以空，可以生产
                Object obj = new Object();
                list.add(obj);
                System.out.println(Thread.currentThread().getName() + "--->" + obj);
                //当生产完成时，生产者释放对象锁，唤醒list上正在等待的线程
                //虽然唤醒之后，生产线程可能会继承抢到锁，但是wait方法又让该方法进入等待了
                list.notifyAll();
            }
        }
    }
}

//消费者线程
class Consumer implements Runnable{

    private List list;

    public Consumer(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true){
            synchronized (list){
                //当仓库以空，消费者线程进入等待状态
                if(list.size() == 0){
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //程序能执行到这，说明list.size()>0，仓库以满，可以消费
                Object obj = list.remove(0);
                System.out.println(Thread.currentThread().getName() + "-->" + obj);
                //当生产完成时，唤醒list上正在等待的线程
                list.notifyAll();
            }
        }
    }
}