package com.example.iot_02_dagger2_demo2;
import dagger.Component;
@Component
public interface DemoComponent {
    void inject(MainActivity activity);
}
