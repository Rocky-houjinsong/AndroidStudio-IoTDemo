package com.example.iot_01_dagger2_demo;

import android.util.Log;

public class ClassA {
    
    private ClassB classB;
    private ClassC classC;
    
    public ClassA(ClassB classB, ClassC classC) {
        this.classB = classB;
        this.classC = classC;
    }

    private  ClassD classD;

    public ClassA(ClassB classB, ClassC classC, ClassD classD) {
        this.classB = classB;
        this.classC = classC;
        this.classD = classD;
    }

    public void todo() {
        Log.d("ClassA","ClassA todo ========");
    }
}
