package com.shj.iotplus_00.login;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.shj.iotplus_00.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEt;
    private EditText passwordEt;
    private Button loginButton;

    private static final String URL_SERVER = "http://114.115.179.78:8888/hiot/auth/login?";

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String userName = getIntent().getStringExtra("user_name");
        Log.i("zzw", "intent接收数据：" + userName);

        usernameEt = findViewById(R.id.username_et);
        usernameEt.setText(userName); // 填充
        passwordEt = findViewById(R.id.password_et);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEt.getText().toString();
                String password = passwordEt.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                login(username, password);
            }
        });


    }

    private void login(final String username, final String password) {
        new Thread() {
            @Override
            public void run() {
                InputStream inputStream = null;
                InputStreamReader inputStreamReader = null;
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(URL_SERVER);
                    URLConnection urlConnection = url.openConnection();
                    HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                    /*请求参数设置*/
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setRequestMethod("POST");

                    /*请求体中提交的数据拼接*/
                    String params = "username=" + username + "&password=" + password + "&loginCode=app";
                    /*向服务器提交数据*/
                    httpURLConnection.getOutputStream().write(params.getBytes("UTF-8"));

                    /*从服务器获取输入流*/
                    inputStream = httpURLConnection.getInputStream();
                    /*数据流转变成字符流*/
                    inputStreamReader = new InputStreamReader(inputStream);
                    bufferedReader = new BufferedReader(inputStreamReader);
                    /*逐行读取字符流，转变成String字符串*/
                    String line = null;
                    StringBuffer stringBuffer = new StringBuffer();
                    while ((line = bufferedReader.readLine()) != null) {
                        /*每行字符串，拼接到StringBuffer*/
                        String lineStr = new String(line.getBytes(), "UTF-8");
                        stringBuffer.append(lineStr);
                    }
                    resoveLoginResult(stringBuffer.toString());
                } catch (Exception e) {

                } finally {
                    try {
                        inputStream.close();
                        inputStreamReader.close();
                        bufferedReader.close();
                    } catch (Exception exc) {
                    }
                }

            }
        }.start();
    }


    private void resoveLoginResult(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            final int status = jsonObject.getInt("status");
            final String msg = jsonObject.getString("msg");
            /*线程调度到主线程*/
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                    if (status == 1) {
                        startActivity(new Intent(LoginActivity.this, LoginSuccessActivity.class));
                        finish();
                    }
                }
            });
        } catch (Exception e) {
        }
    }
}
