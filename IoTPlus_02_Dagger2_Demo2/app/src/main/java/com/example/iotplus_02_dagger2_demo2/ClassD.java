package com.example.iotplus_02_dagger2_demo2;
import android.util.Log;

import javax.inject.Inject;
public class ClassD {
    @Inject
    public ClassD() {
        Log.d("ClassD","ClassD构造函数调用了");
    }
}
