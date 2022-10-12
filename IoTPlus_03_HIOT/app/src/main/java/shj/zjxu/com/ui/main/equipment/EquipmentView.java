package shj.zjxu.com.ui.main.equipment;

import java.util.List;

import shj.zjxu.com.base.BaseView;
import shj.zjxu.com.entity.HolderDeviceEntity;

public interface EquipmentView extends BaseView {
    void showTost(String msg);  //Toast提示结果
    void refreshData(List<HolderDeviceEntity> data);  //刷新列表
    void stopRefresh();    //停止刷新

    void onPermissionGranted();  //系统权限动态申请结果回调
}
