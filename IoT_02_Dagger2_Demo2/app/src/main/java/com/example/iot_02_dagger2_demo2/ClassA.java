package com.example.iot_02_dagger2_demo2;

import android.util.Log;

import javax.inject.Inject;

public class ClassA {
    ClassB classB;
    ClassC classC;
    ClassD classD;

    @Inject
    public ClassA(ClassB classB, ClassC classC, ClassD classD) {
        this.classB = classB;
        this.classC = classC;
        this.classD = classD;
    }



    public void todo(){
        Log.d("ClassA","ClassA todo =======2");
    }
}
