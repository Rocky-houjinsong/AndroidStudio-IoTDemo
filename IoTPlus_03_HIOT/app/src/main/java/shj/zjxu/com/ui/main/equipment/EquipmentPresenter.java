package shj.zjxu.com.ui.main.equipment;

import android.app.Activity;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import shj.zjxu.com.base.BasePresenter;
import shj.zjxu.com.entity.HolderDeviceEntity;
import shj.zjxu.com.http.HttpResult;
import shj.zjxu.com.http.HttpService;
import shj.zjxu.com.http.ProgressDialogSubscriber;
import shj.zjxu.com.http.UserPreferencesHelper;

public class EquipmentPresenter extends BasePresenter<EquipmentView> {

    @Inject
    HttpService httpService;
    @Inject
    Activity activity;
    @Inject
    UserPreferencesHelper preferencesHelper;



    @Inject
    public EquipmentPresenter(){
    }

    public  void getDeviceList() {
        Observable<HttpResult<List<HolderDeviceEntity>>> observable =
                httpService.getDeviceList(1, preferencesHelper.getTokenValue());

         toSubscribe (observable,
                new ProgressDialogSubscriber<HttpResult<List<HolderDeviceEntity>>>(activity) {
                    @Override
                    public void onNext(HttpResult<List<HolderDeviceEntity>> result) {
                        getView().stopRefresh();
                        if (result != null) {
                            if (result.getStatus() == 1) {
                                //查询成功提示语，可注释
                                //getView().Toast(result.getMsg());
                                //获取绑定的设备列表
                                getView().refreshData(result.getData());
                            } else {
                                getView().showTost(result.getMsg());
                            }
                        } else {
                            getView().showTost("失败result ==null");
                        }
                    }

                    public void onError(Throwable e) {
                        super.onError(e);
                        getView().stopRefresh();
                    }
                });
    }
}
