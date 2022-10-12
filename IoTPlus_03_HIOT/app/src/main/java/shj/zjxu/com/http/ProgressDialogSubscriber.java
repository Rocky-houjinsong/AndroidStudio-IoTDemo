package shj.zjxu.com.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Observable;
import rx.Subscriber;

/**
 * 网络请求回调结果同步进度条处理
 */
public abstract class ProgressDialogSubscriber<T> extends Subscriber<T> {

    private  static  final String TAG = "Progress";
    private  final Context mContext;
    private ProgressDialog mDialog;

    public ProgressDialogSubscriber(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * This method is invoked when the Subscriber and Observable have been connected but the Observable has
     * not yet begun to emit items or send notifications to the Subscriber. Override this method to add any
     * useful initialization to your subscription, for instance to initiate backpressure.
     */
    @Override
    public void onStart() {
        super.onStart();
        showProgressDialog();
    }

    private void showProgressDialog() {
        if (mDialog == null)
        {
            mDialog = new ProgressDialog(mContext);
            mDialog.setCancelable(true);
            mDialog.setMessage("请稍后.....");
            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    //取消订阅，取消请求
                    ProgressDialogSubscriber.this.unsubscribe();
                }
            });
        }
        if (mDialog != null && mDialog.isShowing())
            mDialog.show();
    }

    /**
     * Notifies the Observer that the {@link Observable} has finished sending push-based notifications.
     * <p>
     * The {@link Observable} will not call this method if it calls {@link #onError}.
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    /**
     * Notifies the Observer that the {@link Observable} has experienced an error condition.
     * <p>
     * If the {@link Observable} calls this method, it will not thereafter call {@link #onNext} or
     * {@link #onCompleted}.
     *
     * @param e the exception encountered by the Observable
     */
    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException)
            Toast.makeText(mContext,"网络中断,请检查您的网络状态",Toast.LENGTH_SHORT).show();
        else if (e instanceof ConnectException)
            Toast.makeText(mContext,"网络中断,请检查您的网络状态",Toast.LENGTH_SHORT).show();
        else
        {
            Toast.makeText(mContext,"error"+e.getMessage(),Toast.LENGTH_SHORT).show();
            Log.d(TAG,"error"+e.getMessage());
        }
        dismissProgressDialog();
    }

    private void dismissProgressDialog(){
        if (mDialog != null && mDialog.isShowing())
        {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}
