package shj.zjxu.com.ui.main;


import android.app.Activity;

import javax.inject.Inject;

import rx.Observable;
import shj.zjxu.com.base.BasePresenter;
import shj.zjxu.com.entity.LoginEntity;
import shj.zjxu.com.http.HttpResultFunc;
import shj.zjxu.com.http.HttpService;
import shj.zjxu.com.http.ProgressDialogSubscriber;
import shj.zjxu.com.http.UserPreferencesHelper;
import shj.zjxu.com.ui.main.mine.MineView;
public class MainPresenter extends BasePresenter<MainView> {
    @Inject
    HttpService           service; //发送网络请求， 从Application Component
    @Inject
    Activity              activity;  // Activity对象， 从 ActitivityComponent注入的依赖
    @Inject
    UserPreferencesHelper preferencesHelper;  // 用户数据缓存，从 Application注入的依赖
    @Inject
    public MainPresenter() {
    }

    public void login(final String username,final String password,String loginCode){
        Observable<LoginEntity> observable = service.login(username,password,loginCode).map(new HttpResultFunc<LoginEntity>());
        toSubscribe(observable,new ProgressDialogSubscriber<LoginEntity>(activity){
            @Override
            public void onNext(LoginEntity loginEntity){
               getView().showToast(loginEntity.getUuid());
               //以下部分用来用户缓存
               //登陆成功，保存Token和UUID
                preferencesHelper.putTokenValue(loginEntity.getTokenValue());
                preferencesHelper.putUuid(loginEntity.getUuid());
                //保存登陆的用户名和密码
                preferencesHelper.putUserName(username);
                preferencesHelper.getPassword(password);
            }
        });
    }
}
