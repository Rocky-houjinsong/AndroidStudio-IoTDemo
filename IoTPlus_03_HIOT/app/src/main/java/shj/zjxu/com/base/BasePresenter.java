package shj.zjxu.com.base;


import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BasePresenter <T extends  BaseView> {
    public BasePresenter() {
    }

    private  T mView;

    public  void setView (T mvpView){this.mView = mvpView;}
    public  T getView (){return mView;}

    public boolean isViewAttached(){return mView != null;}

    public void detachView(){
        if (mView != null){
            this.mView = null;
        }
    }

    //公共部分

    public <R> void toSubscribe(Observable<R> observable,Subscriber<R> subscriber){
        //Schedulers线程调度器
        observable.subscribeOn(Schedulers.io())  //IO线程池
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())   //主线程
                .subscribe(subscriber);    //订阅到ProgressDialogSubscriber观察者
    }
}
