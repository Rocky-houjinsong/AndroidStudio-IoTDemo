package com.shj.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClassA extends Activity {
    Button button;
    private TextView textView;
    private  int count = 0;
    private static final String TAG = "宋厚锦";

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //获得刚才发送的Message对象，然后在这里进行UI操作
            Log.i(TAG,"------------> msg.what = " + msg.what);
            textView.setText("计数:宋厚锦"+count);
        }
    };
    private void initData() {

        //开启一个线程模拟处理耗时的操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                //通过Handler发送一个消息切换回主线程（mHandler所在的线程）
                mHandler.sendEmptyMessage(count);
                count++;
            }
        }).start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView  = findViewById(R.id.main_text);
        findViewById(R.id.btn_dowmload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
    }

}