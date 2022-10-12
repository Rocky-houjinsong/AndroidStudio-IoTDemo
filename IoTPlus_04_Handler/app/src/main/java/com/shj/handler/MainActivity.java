package com.shj.handler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class MainActivity extends Activity {
    private TextView textView;
    private  int count = 0;
    public Handler mHandler;
    class LooperThread extends Thread {

        public void run() {
            Looper.prepare();
            mHandler = new Handler(Looper.myLooper()) {
                public void handleMessage(Message msg) {
                    // process incoming messages here
                    super.handleMessage(msg);
                    int i = (Integer) msg.obj;
                    textView.setText("计数"+i);
                }
            };

            Looper.loop();
        }
    }
    private void initData() {
        // 第一种方法
        new Thread(){
            @Override
            public void run(){
                try{
                    int i = 0;
                    for (;;){
                        Thread.currentThread().sleep(1000);
                        ++i;
                        Log.i("123",""+i);
                        Message msg =mHandler.obtainMessage();
                        msg.obj = i;
                        Log.i("计数:",Thread.currentThread().getName()+"宋厚锦:");
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("计数:",Thread.currentThread().getName()+"宋厚锦:");
                                textView.setText("计数:宋厚锦"+msg.obj);
                            }
                        });
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            /*    MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            int i = 0;
                            for (;;){
                                Thread.currentThread().sleep(1000);
                                ++i;
                                Log.i("123",""+i);
                                Message msg =mHandler.obtainMessage();
                                msg.obj = i;
                                mHandler.sendMessage(msg);
                                Log.i("计数:",Thread.currentThread().getName()+"宋厚锦:");
                                textView.setText("计数:宋厚锦"+i);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });*/
            }
        }.start();

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView  = findViewById(R.id.main_text);
        findViewById(R.id.btn_dowmload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LooperThread looperThread = new LooperThread();
                looperThread.start();
                initData();
            }
        });
    }
}


