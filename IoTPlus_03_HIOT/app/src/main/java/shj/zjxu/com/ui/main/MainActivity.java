package shj.zjxu.com.ui.main;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import javax.inject.Inject;

import shj.zjxu.com.R;
import shj.zjxu.com.base.BaseActivity;
import shj.zjxu.com.ui.main.mine.MinePresenter;
import shj.zjxu.com.ui.main.mine.MineView;
import shj.zjxu.com.view.NoSlideViewPager;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {
     @Inject
   MainPresenter mainPresenter;
   private NoSlideViewPager viewPager;
   private RadioGroup main_raido_group;
   private RadioButton main_radio_message;
    private RadioButton main_radio_equipment;
    private RadioButton main_radio_scene;
    private RadioButton main_radio_mine;

//  @Override
//   protected MinePresenter createPresenter() {
//        return minePresenter;
//    }

   protected MainPresenter createPresenter(){return mainPresenter;}
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.main_vp_select);
        main_raido_group = findViewById(R.id.main_radio_group);
        main_radio_equipment = findViewById(R.id.main_radio_equipment);
        main_radio_message = findViewById(R.id.main_radio_message);
        main_radio_mine = findViewById(R.id.main_radio_mine);
        main_radio_scene = findViewById(R.id.main_radio_scene);

      //  mainPresenter.login("zhao99","zzw0515","app");
        MainFragmentPagerAdapter pagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setSlidable(false);
        main_radio_message.setChecked(true);
        main_raido_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_radio_message:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.main_radio_equipment:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.main_radio_scene:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.main_radio_mine:
                        viewPager.setCurrentItem(3);
                        break;
                }
            }
        });

    }

    @Override
    protected void injectDependencies() {
        getmActivityComponent().inject(this);
    }

    @Override
    public void showToast(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

}
