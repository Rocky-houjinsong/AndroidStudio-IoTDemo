package com.shj.wujiaokongjianbaifang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * 实现屏幕四角 和 居中 摆放控件的布局
 * 分而治之， 将屏幕在垂直方向分为3部分，
 * 然后，再讲上下2部分再各分为左右两部分
 * 保证屏幕怎么旋转，控件的相对位置是不变的；
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}