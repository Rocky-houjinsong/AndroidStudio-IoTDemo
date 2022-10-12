package com.shj.httpget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    private TextView mean;
    private EditText in;
    private Button   sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void initView() {
        mean = (TextView) findViewById(R.id.mean);
        in = (EditText) findViewById(R.id.in);
        sure = (Button) findViewById(R.id.button01);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用异步任务来完成读取任务操作----读取互联网数据是一个耗时的IO操作
                new AsyncTask<String, Void, Void>() {
                    //这里需要捕获异常，获取互联网的连接，openConnection也需要捕获异常；获取当前网络的输入流
                    //包装成简洁的数据 ，Reader，转化成 InputStreamReader，指定字符集
                    //读取一行字符串
                    @Override
                    protected Void doInBackground(String... param) {
                        try {
                            URL url = new URL(param[0]);
                            URLConnection connection = url.openConnection();
                            InputStream inputStream = connection.getInputStream();
                            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            String line;
                            while ((line = bufferedReader.readLine()) != null) {
                                System.out.println(line);
                            }
                            bufferedReader.close();
                            inputStreamReader.close();
                            ;
                            inputStream.close();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute("http://fanyi.youdao.com/openapi.do?keyfrom=fanyi&key=3FPML96AEGmoeNucLsGZQgiNX2V0eEkX&type=data&doctype=xml&version=1.1&q=hello");
            }
        });
    }
}