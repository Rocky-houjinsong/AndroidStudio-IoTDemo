package com.example.iotplus_02_dagger2_demo3;
import android.util.Log;

import javax.inject.Inject;
public class ClassB {
    @Inject
    public ClassB() {
        Log.d("ClassB","ClassB构造函数调用了");
    }
}
