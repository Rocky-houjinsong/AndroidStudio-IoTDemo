package shj.zjxu.com.injection.module;

import android.app.Activity;

import shj.zjxu.com.base.BaseActivity;
import shj.zjxu.com.base.BasePresenter;
import dagger.Module;
import dagger.Provides;
import shj.zjxu.com.injection.PerActivity;
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity(){
        return this.activity;
    }

}
