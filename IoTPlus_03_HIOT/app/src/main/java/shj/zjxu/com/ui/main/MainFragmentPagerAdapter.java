package shj.zjxu.com.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import shj.zjxu.com.ui.main.equipment.EquipmentFragment;
import shj.zjxu.com.ui.main.message.MessageFragment;
import shj.zjxu.com.ui.main.mine.MineFragment;
import shj.zjxu.com.ui.main.scene.SceneFragment;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    public MainFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment  = new MessageFragment();
                break;
            case 1:
                fragment = new EquipmentFragment();
                break;
            case 2:
                fragment = new SceneFragment();
                break;
            case 3:
                fragment = new MineFragment();
                break;
        }
        return fragment;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 4;
    }
}
