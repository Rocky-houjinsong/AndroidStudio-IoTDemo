package shj.zjxu.com.injection.component;


import dagger.Component;

import shj.zjxu.com.injection.PerActivity;
import shj.zjxu.com.injection.module.ActivityModule;
import shj.zjxu.com.ui.login.LoginActivity;
import shj.zjxu.com.ui.main.MainActivity;
import shj.zjxu.com.ui.main.equipment.EquipmentFragment;
import shj.zjxu.com.ui.main.message.MessageFragment;

import shj.zjxu.com.ui.main.mine.MineFragment;
import shj.zjxu.com.ui.main.scene.SceneFragment;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
    void ingect(LoginActivity loginActivity);
    void inject(MessageFragment messageFragment);

    void inject(EquipmentFragment equipmentFragment);

    void inject(MineFragment mineFragment);

    void inject(SceneFragment sceneFragment);




}




















