package com.shj.as_base_eoe_icontextview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * 继承方式 定制控件
 * 控件类继承自TextView
 * 在控件类的构造方法中读取控件的属性值
 * 在onDraw方法中 重新确定图像和文本的位置
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}