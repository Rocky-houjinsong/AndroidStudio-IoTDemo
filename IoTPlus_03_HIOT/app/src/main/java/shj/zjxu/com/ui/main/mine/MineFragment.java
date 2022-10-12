package shj.zjxu.com.ui.main.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import shj.zjxu.com.R;
import shj.zjxu.com.base.BaseActivity;
import shj.zjxu.com.base.BaseFragment;
import shj.zjxu.com.ui.main.scene.ScenePresenter;

public class MineFragment extends BaseFragment<MineView,MinePresenter> implements MineView{
    @Inject
    public MineFragment() {
    }
    @Inject
    MinePresenter minePresenter;
    @Override
    protected MinePresenter createPresenter() {
        return minePresenter;
    }

    @Override
    protected void injectDependencies() {
        super.injectDependencies();
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine,container,false);
        return view;
    }
}
