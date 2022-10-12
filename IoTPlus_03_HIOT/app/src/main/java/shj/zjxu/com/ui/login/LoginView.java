package shj.zjxu.com.ui.login;

import shj.zjxu.com.base.BaseView;

public interface LoginView extends BaseView {
    void showToast(String msg); //显示登陆信息
    void loginSuccess();        // 登陆成功后的处理，如跳转主界面
}
