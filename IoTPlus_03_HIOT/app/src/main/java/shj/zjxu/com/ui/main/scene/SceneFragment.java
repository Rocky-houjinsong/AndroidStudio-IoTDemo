package shj.zjxu.com.ui.main.scene;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import shj.zjxu.com.R;
import shj.zjxu.com.base.BaseActivity;
import shj.zjxu.com.base.BaseFragment;

public class SceneFragment extends BaseFragment<SceneView,ScenePresenter> implements SceneView {
    @Inject
    ScenePresenter scenePresenter;

    @Override
    protected ScenePresenter createPresenter() {
        return scenePresenter;
    }

    @Override
    protected void injectDependencies() {
        ((BaseActivity)getActivity()).getmActivityComponent().inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scene,container,false);
        return view;
    }
}
