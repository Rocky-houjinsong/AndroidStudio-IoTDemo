package com.shj.as_base_eoe_labeledittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * 组合方式 定制控件
 * 基本实现：
 * 1. 在控件类中定义 TextView 和 EditText类型的变量
 * 2. 在控件类的构造方法中读取 控件的属性
 * 3. 根据控件属性值设置TextView 和 EditText的值，一直相对位置
 * 编写控件类时 ，应将可能变化的值通过 属性设置，
 * 用户可能调整的控件属性不要固化到控件类中
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}