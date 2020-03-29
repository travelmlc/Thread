package com.bjpowernode.thread;

/**
 * ClassName:ThreadTest05
 * Package:com.bjpowernode.thread
 * Description:
 *
 * @Date:2020/3/28 22:22
 * @Author:mlc17607163664@163.com
 *
 * 如何合理的终止线程的执行
 */
public class ThreadTest05 {

    public static void main(String[] args) {
        Test02 test02 = new Test02();
        Thread thread = new Thread(test02);
        thread.setName("yyy");
        thread.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //终止线程
        test02.run = false;
    }
}

class Test02 implements Runnable{

    //打一个标记
    boolean run = true;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if(run){
                System.out.println(Thread.currentThread().getName() + "--->" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                return;
            }
        }
    }
}
