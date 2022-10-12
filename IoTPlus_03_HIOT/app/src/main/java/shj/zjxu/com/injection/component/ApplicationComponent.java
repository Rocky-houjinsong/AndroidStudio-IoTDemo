package shj.zjxu.com.injection.component;
import android.app.Application;
import android.content.Context;
import javax.inject.Singleton;
import dagger.Component;
import shj.zjxu.com.App;
import shj.zjxu.com.http.HttpService;
import shj.zjxu.com.http.UserPreferencesHelper;
import shj.zjxu.com.injection.module.ApplicationModule;

@Singleton//单例
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(App application);
    Application application();//将Application开放给其他Component使用
    Context context();//将Context开放给其他Component使用
    HttpService httpService();//将HttpService开放给其他Component使用
    UserPreferencesHelper userPreferencesHelper();//将UserPreferencesHelper开放给其他Component使用
}

