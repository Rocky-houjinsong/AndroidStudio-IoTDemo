package shj.zjxu.com.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import shj.zjxu.com.R;
import shj.zjxu.com.base.BaseActivity;
import shj.zjxu.com.ui.main.MainActivity;

public class LoginActivity  extends BaseActivity<LoginView,LoginPresenter> implements LoginView {
    @Inject
    LoginPresenter loginPresenter;
    private Unbinder unbinder;

    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_pwd)
    EditText etUserPwd;

    @OnClick(R.id.bt_login)
    void login(){
        String username = etUserName.getText().toString();
        String password = etUserPwd.getText().toString();
        loginPresenter.login(username,password);
    }

    @OnClick(R.id.forget_password)
    void forgetPassword(){
        //TODD：忘记密码
        Toast.makeText(this,"忘记密码",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.link_signup)
    void linkSignup(){
//        Intent intent = new Intent(this,RegisterActivity.class);
//        startActivity(intent);
        finish();
    }

    @Override
    protected void injectDependencies() {
        getmActivityComponent().ingect(this);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return loginPresenter;
    }

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Unbinder unbiner 绑定返回解绑对象
        unbinder = ButterKnife.bind(this);
        //若保存用户名密码，就是已经登陆过后，会自动登陆
        loginPresenter.autoLogin();
        //TODD 临时测试使用
        etUserName.setText("zhao99");
        etUserPwd.setText("zzw0515");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //使用完解绑ButterKnife，避免内存泄露
        unbinder.unbind();
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
