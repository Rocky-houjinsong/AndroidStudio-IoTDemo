package com.shj.iotplus_00.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.shj.iotplus_00.R;

public class MyFragment extends Fragment {
    private UpdateActivityListener updateActivityListener;


    public void setUpdateTextViewListener(UpdateActivityListener updateActivityListener) {
        this.updateActivityListener = updateActivityListener;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private static final String ARG_PARAM = "param_key";
    private String   mParam;
    private Activity mActivity;
    int i = 0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;// Fragment中获取MainActivity对象方式
        mParam = getArguments().getString(ARG_PARAM);  // 获取Activity传递的数据
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.my_fragment, container, false);
        /*来自Activity的数据，更新到TextView上*/
        TextView view = root.findViewById(R.id.text);
        view.setText(mParam);
        /*Fragment中的button*/
        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                updateActivityListener.update("我来自Fragment:" + i);
            }
        });
        return root;
    }

    public static MyFragment newInstance(String strParam) {
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, strParam);
        myFragment.setArguments(bundle); // 绑定从Activity传入的数据
        return myFragment;
    }
}

