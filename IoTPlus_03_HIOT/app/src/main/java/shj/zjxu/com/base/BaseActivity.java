package shj.zjxu.com.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import shj.zjxu.com.App;
import shj.zjxu.com.injection.component.ApplicationComponent;

import shj.zjxu.com.injection.component.ActivityComponent;


import shj.zjxu.com.injection.component.DaggerActivityComponent;
import shj.zjxu.com.injection.module.ActivityModule;

public abstract class BaseActivity <V extends BaseView,P extends BasePresenter> extends AppCompatActivity implements  BaseView {
private  P mvpPresenter;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        mvpPresenter = createPresenter();
        if (mvpPresenter != null){
            mvpPresenter.setView((V)this);
        }
    }

    protected abstract void injectDependencies();
    protected abstract P createPresenter();

    private ActivityComponent mActivityComponent;
    public ActivityComponent getmActivityComponent(){
        if (mActivityComponent == null){
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(getActivityModule())
                    .applicationComponent(getApplicationComponent())
                    .build();
        }
           return mActivityComponent;
    }

    protected ApplicationComponent getApplicationComponent(){
        return ((App)getApplication()).component();
    }
    private ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null){
            mvpPresenter.detachView();
        }
    }
}
