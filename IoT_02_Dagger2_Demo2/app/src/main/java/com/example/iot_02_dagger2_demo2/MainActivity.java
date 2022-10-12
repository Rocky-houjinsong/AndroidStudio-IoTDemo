package com.example.iot_02_dagger2_demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    ClassA classA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //下面注释的代码 执行会失败， classA 对象 的空的， 需要一下步骤 来执行
        /*ClassB classB = new ClassB();
        ClassC classC = new ClassC();

        ClassA classA = new ClassA(classB,classC);
        classA.todo();

        ClassD classD = new ClassD();
        ClassA classA1 = new ClassA(classB,classC,classD);*/

        DaggerDemoComponent.create().inject(this);
        classA.todo();

    }
}