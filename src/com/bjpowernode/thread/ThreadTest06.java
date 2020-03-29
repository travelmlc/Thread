package com.bjpowernode.thread;

/**
 * ClassName:ThreadTest06
 * Package:com.bjpowernode.thread
 * Description:
 *
 * @Date:2020/3/29 13:35
 * @Author:mlc17607163664@163.com
 *
 * static void yield();让位方法
 * 暂停当前正在执行的线程对象，并执行其他线程
 * yield()方法不是阻塞方法，让当前线程让位，让给其他线程使用
 * 注意：在回到就绪之后，有可能还会再次抢到
 *
 * void join();     合并线程
 * getPriority();   获取线程的优先级
 */
public class ThreadTest06 {

    public static void main(String[] args) {

        /*System.out.println("最高优先级" + Thread.MAX_PRIORITY);
        System.out.println("最低优先级" + Thread.MIN_PRIORITY);
        System.out.println("默认优先级" + Thread.NORM_PRIORITY);*/

        Thread thread = new Thread(new MyRunnable());
        //System.out.println(thread.getName() + "线程的默认优先级是：" + thread.getPriority());

        thread.setName("t");
        thread.start();

        for (int i = 1; i <= 1000; i++) {
            System.out.println(Thread.currentThread().getName() + "--->" + i);
        }
    }
}

class MyRunnable implements Runnable{

    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            //每100个让位1次
            if(i % 100 == 0){
                Thread.yield();
            }
            System.out.println(Thread.currentThread().getName() + "-->" + i);
        }
    }
}
