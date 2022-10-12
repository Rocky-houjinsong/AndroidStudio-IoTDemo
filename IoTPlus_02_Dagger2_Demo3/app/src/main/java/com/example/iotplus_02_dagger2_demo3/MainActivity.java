package com.example.iotplus_02_dagger2_demo3;

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

        DaggerDemoComponent
                .builder()
                .demoModule(new DemoModule())
                .build()
                .inject(this);



        classA.todo();

    }
}