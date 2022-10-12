package com.example.iotplus_02_dagger2_demo3;
import dagger.Component;
@Component(modules = DemoModule.class)
public interface DemoComponent {
    void inject(MainActivity activity);
}
