package shj.zjxu.com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import shj.zjxu.com.ui.main.MainFragmentPagerAdapter;

public class NoSlideViewPager extends ViewPager {

    public NoSlideViewPager(@NonNull @org.jetbrains.annotations.NotNull Context context) {
        super(context);
    }

    public NoSlideViewPager(@NonNull @org.jetbrains.annotations.NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    /*
    ViewPager是否可滑动开关
     */
    private boolean isSlidable = false;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.isSlidable && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.isSlidable && super.onInterceptTouchEvent(ev);
    }
    public void  setSlidable(boolean slidable){isSlidable = slidable;}


}
