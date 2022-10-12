package com.shj.iotplus_00.multithread;


import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadStudy {
    private Handler mHandler;
    private Handler workerHandler = new Handler();

    public MultiThreadStudy() {
        run();
    }

    private void run() {
        /*Handler的第一种用法：在所处线程中添加耗时任务*/
        workerHandler.post(new Runnable() {// 添加第1个任务并添加到任务队列中
            @Override
            public void run() {
                // 由于MultiThreadStudy实例化为主线程中，故而此处代码执行的环境是在主线程中。
            }
        });
        workerHandler.post(new Runnable() {// 添加第2个任务并添加到任务队列中
            @Override
            public void run() {
                // 由于MultiThreadStudy实例化为主线程中，故而此处代码执行的环境是在主线程中。
            }
        });

        /* ------两个子线程间的通信，借助Handler------start */
        /*线程1 - 继承Thread类，实现Runnable接口*/
        new Thread(new Runnable() {// 匿名内部类
            @Override
            public void run() {
                Looper.prepare();
                mHandler = new Handler(Looper.myLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        String content = (String) msg.obj;
                        Log.i("zzw", "线程2发送过来的消息：" + content);
                    }
                };
                Looper.loop();
            }
        }).start(); // 起一个新线程，自动执行run方法中的代码

        /*线程2 - 继承Thread类，覆写run方法*/
        new Thread() {
            @Override
            public void run() {
                int i = 0;
                for (; i < 10; ) {
                    try {
                        Thread.sleep(1000);
                        /*先运算再赋值*/
                        i++;
                        /*先赋值再运算*/
                        //++i;
                        Message msg = mHandler.obtainMessage(); // 通过静态单链表来全局完成消息的复用
                        msg.obj = "计数：" + i;
                        mHandler.sendMessage(msg);
                    } catch (Exception e) {
                    }
                }
            }
        }.start();
        /* ------两个子线程间的通信，借助Handler------end */

        /* 线程池的创建，以FixedThreadPool为例 */
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // 线程池中执行的耗时任务
            }
        });


        /* ------HandlerThread的创建和使用------start */
        /*1.创建HandlerThread，并启动*/
        HandlerThread handlerThread = new HandlerThread("my_work_thread");
        handlerThread.start();
        /*2.创建Handler，并关联工作线程的Looper*/
        Handler handler = new Handler(handlerThread.getLooper());
        /*3.添加任务到工作线程中执行*/
        handler.post(new Runnable() {
            @Override
            public void run() {
                /*执行耗时任务1，比如发起网络请求*/
            }
        });
        handler.post(new Runnable() {
            @Override
            public void run() {
                /*执行耗时任务2，比如IO处理*/
            }
        });
        /* ------HandlerThread的使用------end */


    }


}
