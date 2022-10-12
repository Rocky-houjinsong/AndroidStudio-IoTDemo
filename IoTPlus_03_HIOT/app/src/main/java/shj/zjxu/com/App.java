package shj.zjxu.com;

import android.app.Application;

import shj.zjxu.com.injection.component.ApplicationComponent;


import shj.zjxu.com.injection.component.DaggerApplicationComponent;
import shj.zjxu.com.injection.module.ApplicationModule;


public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();

    }

    private void initInjector() {
       component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
               .build();
        component.inject(this);
    }

    public ApplicationComponent component() {
        return component;
    }

}
