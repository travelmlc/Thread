package com.bjpowernode.thread02;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;     //JUC包下，属于java的并发包，属于新特性

/**
 * ClassName:Thread03
 * Package:com.bjpowernode.thread02
 * Description:
 *
 * @Date:2020/3/29 21:50
 * @Author:mlc17607163664@163.com
 *
 * jdk1.8之后：实现线程的第三种方式
 *      实现Callable接口:
 *         优点：可以获取到线程的执行结合
 *         缺点：效率低，在获取t线程的执行结果时，当前线程需要等待
 */
public class Thread03 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1、创建一个“未来任务类”对象,参数非常重要，需要给一个Callable接口的实现类对象
        FutureTask task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {     //call方法相当于run方法，只不过这个有返回值
                System.out.println("call before");
                Thread.sleep(1000 * 10);
                System.out.println("call after");
                return "erg";
            }
        });

        //2、创建一个线程对象
        Thread thread = new Thread(task);

        //3、启动线程
        thread.start();

        //4、这是在主线程当中，怎么获取t线程的返回结果？
        /*
            main方法执行到这里必须要等待get()方法的结束，而get方法可能需要很久。
            因为get方法视为拿另一个线程的执行结果，而另一个线程的执行时需要时间的。
         */
        task.get();
        System.out.println("hello");
    }
}
