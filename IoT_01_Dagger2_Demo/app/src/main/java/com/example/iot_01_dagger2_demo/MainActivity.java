package com.example.iot_01_dagger2_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
/*
通过这个 传统的new 显式 构造实例的方法 ，会 发生一份问题 ，高耦合；需要重写 很多的构造方法；
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClassB classB = new ClassB();
        ClassC classC = new ClassC();

        ClassA classA = new ClassA(classB,classC);
        classA.todo();

        ClassD classD = new ClassD();
        ClassA classA1 = new ClassA(classB,classC,classD);
        classA1.todo();
    }
}