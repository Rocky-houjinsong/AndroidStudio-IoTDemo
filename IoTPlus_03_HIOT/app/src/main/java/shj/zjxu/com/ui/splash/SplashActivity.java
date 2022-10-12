package shj.zjxu.com.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import shj.zjxu.com.R;
import shj.zjxu.com.ui.login.LoginActivity;

public class SplashActivity  extends AppCompatActivity {
    private static final long LOADING_TIME = 3000;
    private Timer timer;
    private Unbinder unbinder;

    @BindView(R.id.splash_loading_item)
    ImageView animIv;
    @OnClick(R.id.skip_button)
    void skip(){skipToMain();}

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        unbinder = ButterKnife.bind(this);
        startAnim();
        keepLoading();
    }

    private void keepLoading() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        skipToMain();
                    }
                });
            }
        },LOADING_TIME);
    }

    private void startAnim(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.splash_loading_anim);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.INFINITE);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animIv.startAnimation(animation);
    }

    private  void  skipToMain(){
        timer.cancel();
        animIv.clearAnimation();
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
