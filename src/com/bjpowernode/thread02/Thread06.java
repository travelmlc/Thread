package com.bjpowernode.thread02;

/**
 * ClassName:Thread06
 * Package:com.bjpowernode.thread02
 * Description:
 *
 * @Date:2020/3/30 12:35
 * @Author:mlc17607163664@163.com
 *
 * 交替打印奇数偶数
 */
public class Thread06 {
    public static void main(String[] args) throws InterruptedException {
        Count count = new Count();
        Thread t1 = new Thread(new PrintOdd(count));
        Thread t2 = new Thread(new PrintEven(count));
        t1.setName("奇数");
        t2.setName("偶数");
        t1.start();
        Thread.sleep(1000);
        t2.start();
    }
}

class Count{
     int count = 1;
}

class PrintOdd implements Runnable{

    private Count count;

    public PrintOdd(Count count) {
        this.count = count;
    }

    @Override
    public void run() {
        while (true){
            synchronized (count){
                //如果为偶数就让该线程进行等待
                if(count.count % 2 == 0){
                    try {
                        Thread.sleep(1000);
                        count.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //打印奇数
                System.out.println(Thread.currentThread().getName() + "-->" + (count.count++));
                count.notifyAll();
            }
        }
    }
}

class PrintEven implements Runnable{

    private Count count;

    public PrintEven(Count count) {
        this.count = count;
    }

    @Override
    public void run() {
        while (true){
            synchronized (count){
                //如果为奇数就让该线程进行等待
                if(count.count % 2 != 0){
                    try {
                        Thread.sleep(1000);
                        count.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //打印偶数
                System.out.println(Thread.currentThread().getName() + "-->" + (count.count++));
                count.notifyAll();
            }
        }
    }
}
