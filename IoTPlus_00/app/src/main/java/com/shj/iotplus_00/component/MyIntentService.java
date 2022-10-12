package com.shj.iotplus_00.component;


import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.shj.iotplus_00.MainActivity;

/**
 * IntentService后台服务
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    public MyIntentService(String name) {
        super(name);

    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        /*耗时任务，此处逻辑，执行在后台服务的子线程中*/
        String data = intent.getStringExtra("data"); // 从MainActivity传过来的数据
        int i = 0;
        for (; i < Math.random() * 20; ) {
            try {
                i++;
                Log.i("zzw", data + i);
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
        String result = "IntentService处理后的结果为：" + i; // IntentService使用子线程处理的最终结果
        /*IntentService中处理结果，通过本地广播发送出去*/
        Intent intentBroadCast = new Intent();
        intentBroadCast.setAction(MainActivity.MyBroadcastReceiver.MY_BR_ACTION1);
        intentBroadCast.putExtra("intent_service_data", result);
        sendBroadcast(intentBroadCast);// 发送动态全局广播
//        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
//        localBroadcastManager.sendBroadcast(intentBroadCast);
    }
}
