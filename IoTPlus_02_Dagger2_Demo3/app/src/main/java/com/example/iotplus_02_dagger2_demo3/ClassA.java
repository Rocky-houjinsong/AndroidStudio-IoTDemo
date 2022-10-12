package com.example.iotplus_02_dagger2_demo3;
import android.util.Log;

import  javax.inject.Inject;
public class ClassA {
    private ClassB classB;
    private ClassC classC;
    private ClassD classD;

    @Inject
    public ClassA(ClassB classB, ClassC classC, ClassD classD) {
        Log.d("ClassA","ClassA构造函数调用了");
        this.classB = classB;
        this.classC = classC;
        this.classD = classD;
    }


    public void todo(){
        Log.d("ClassA","ClassA ======");
    }
}
