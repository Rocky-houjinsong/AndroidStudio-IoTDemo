package com.shj.iotplus_00;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shj.iotplus_00.bean.User;
import com.shj.iotplus_00.component.MyIntentService;
import com.shj.iotplus_00.fragment.MyFragment;
import com.shj.iotplus_00.fragment.UpdateActivityListener;
import com.shj.iotplus_00.login.LoginActivity;
import com.shj.iotplus_00.multithread.MultiThreadStudy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * 启动多线程
 * 启动IntentService
 * Fragment动态添加
 * Intent跳转传数据
 * JSONObject解析
 * Gson解析
 * 本地/全局广播的注册
 */
public class MainActivity extends AppCompatActivity implements UpdateActivityListener {

    @Override
    public void update(String str) {
        // 从Fragment传递过来的数据，更新Activity
        textView.setText(str);
    }

    private TextView            textView;
    private Button              button;
    private MyBroadcastReceiver myBroadcastReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);
        textView = findViewById(R.id.textview);

        /*启动多线程*/
        new MultiThreadStudy();

        /*启动IntentService*/
        Intent intentService = new Intent(MainActivity.this, MyIntentService.class);
        intentService.putExtra("data", "启动IntentService执行计数：");
        startService(intentService);

        /*Fragment动态添加*/
        MyFragment myFragment = MyFragment.newInstance("Acitivity向Fragment传递的数据");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, myFragment);
        transaction.commit();
        // 接下来添加监听
        myFragment.setUpdateTextViewListener(this);

        /*Intent传递数据*/
        button = findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("user_name", "zhao99");
                startActivity(intent);
                finish();
            }
        });


        /*-------Json字符串及数组解析start-------*/
        /* JSONObject解析 */
        String jsonString = "{\"name\":\"张三\",\"age\":24}";
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            String name = jsonObject.getString("name");
            Log.i("zzw", "使用JSONObject获取name为：" + name);
            JSONArray jsonArray = jsonObject.getJSONArray("hobby");
        } catch (JSONException e) {

        }
        /* Gson框架解析 */
        Gson gson = new Gson();
        User user = gson.fromJson(jsonString, User.class); // 反序列化
        Log.i("zzw", "使用Gson获取name为：" + user.getName());
        String str = gson.toJson(user); // 序列化，str内容同jsonString
        /*-------Json字符串及数组解析start-------end*/


        /* 本地广播的使用 */
        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyBroadcastReceiver.MY_BR_ACTION1);  // 此广播可以接收的消息Action

        registerReceiver(myBroadcastReceiver, intentFilter); // 注册全局动态广播
//        localBroadcastManager = LocalBroadcastManager.getInstance(this);
//        localBroadcastManager.registerReceiver(myBroadcastReceiver, intentFilter); // 注册本地广播（不能跨进程接收消息）
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 注销广播，避免内存泄漏
//        localBroadcastManager.unregisterReceiver(myBroadcastReceiver);
        unregisterReceiver(myBroadcastReceiver);
    }

    /**
     * 自定义广播组件
     */
    public class MyBroadcastReceiver extends BroadcastReceiver {
        public static final String MY_BR_ACTION1 = "com.zzw.mybroadcast.action1";


        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("zzw", "收到广播消息");
            String action = intent.getAction();
            if (action.equals(MY_BR_ACTION1)) {
                Toast.makeText(MainActivity.this, intent.getStringExtra("intent_service_data"), Toast.LENGTH_SHORT).show();
            }

        }
    }

}
