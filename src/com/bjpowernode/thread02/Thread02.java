package com.bjpowernode.thread02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ClassName:Thread02
 * Package:com.bjpowernode.thread02
 * Description:
 *
 * @Date:2020/3/29 21:19
 * @Author:mlc17607163664@163.com
 *
 * 定时器：间隔特定时间，执行特定的操作
 */
public class Thread02 {

    public static void main(String[] args) throws ParseException {
        //创建定时器
        Timer timer = new Timer();

        //指定定时任务
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2020-03-29 21:46:00");
        /*
            task:定时任务
            date:第一次执行时间
            period:间隔时间
         */
        //timer.schedule(new LogTime(),date,1000 * 10);

        timer.schedule(new TimerTask() {
            //匿名内部类
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = sdf.format(new Date());
                System.out.println(time + "完成一次数据备份");
            }
        }, date, 1000 * 10);
    }
}

//编写一个定时任务类，假设这是记录日志的定时任务
/*
class LogTime extends TimerTask{

    @Override
    public void run() {
        //编写需要执行的任务
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        System.out.println(time + "完成一次数据备份");
    }
}*/
