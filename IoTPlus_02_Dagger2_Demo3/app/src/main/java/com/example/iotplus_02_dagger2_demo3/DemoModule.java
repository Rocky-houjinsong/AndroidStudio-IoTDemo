package com.example.iotplus_02_dagger2_demo3;

import dagger.Module;
import dagger.Provides;
@Module
public class DemoModule {
    @Provides
    ClassB provideClassB(){
        return new ClassB();
    }
    @Provides
    ClassC provideClassC(){
        return new ClassC();
    }
}
