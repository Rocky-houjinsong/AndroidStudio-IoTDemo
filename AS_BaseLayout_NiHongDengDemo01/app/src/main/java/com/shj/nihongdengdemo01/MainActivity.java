package com.shj.nihongdengdemo01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

/**
 * 霓虹灯布局 核心点在于 对颜色的处理上；
 * 对 布局改变需要通过Handler交由UI线程处理；
 * 简单了解如何实现即可；
 */
public class MainActivity extends Activity implements Runnable{
    //5个 TextView的颜色值；
    private int[] colors = new int[]{0xFFFF000,0xFF00FF00,0xFF0000FF,0xFFFFF0F,0xFF00FFFF};
    //每一次颜色的下一个颜色的索引，最有一个颜色的下一个颜色是第一个颜色，相当于循环链表
    private int[] nextColorPointers = new int[]{1,2,3,4,0};
    private View[] views;//保存5个TextView
    private int currentColorPointer = 0; //当前颜色的索引
    private Handler handler;

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        int nextColorPointer = currentColorPointer;
        for (int i = views.length-1;i>=0;i--){
            //设置当前TextView的背景颜色
            views[i].setBackgroundColor(colors[nextColorPointers[nextColorPointer]]);
            //获得下一个TextView的背景颜色的索引（指针）
            nextColorPointer  = nextColorPointers[nextColorPointer];
        }
        currentColorPointer++;
        if (currentColorPointer ==5)
            currentColorPointer=0;
        handler.postDelayed(this,300); //每300毫秒循环一次
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化 views数组
        views = new View[]{findViewById(R.id.textview01),findViewById(R.id.textview02),
                findViewById(R.id.textview03),findViewById(R.id.textview04),findViewById(R.id.textview05)};
        handler = new Handler();
        handler.postDelayed(this,300); //每300毫秒执行一次
    }


}