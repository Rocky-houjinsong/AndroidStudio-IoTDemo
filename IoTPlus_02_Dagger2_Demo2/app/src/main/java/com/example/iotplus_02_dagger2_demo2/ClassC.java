package com.example.iotplus_02_dagger2_demo2;

import android.util.Log;

import javax.inject.Inject;
public class ClassC {
    @Inject
    public ClassC() {
        Log.d("ClassC","ClassC构造函数调用了");
    }
}
