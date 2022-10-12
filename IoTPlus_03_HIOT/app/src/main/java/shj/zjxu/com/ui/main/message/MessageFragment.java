package shj.zjxu.com.ui.main.message;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import shj.zjxu.com.R;
import shj.zjxu.com.base.BaseActivity;
import shj.zjxu.com.base.BaseFragment;

public class MessageFragment extends BaseFragment<MessageView,MessagePresenter> implements MessageView {
@Inject
    MessagePresenter messagePresenter;

    @Override
    protected MessagePresenter createPresenter() {
        return messagePresenter;

    }

    @Override
    protected void injectDependencies(){
        super.injectDependencies();
        ((BaseActivity)getActivity()).getmActivityComponent().inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message,container,false);
        return  view;
    }
}
