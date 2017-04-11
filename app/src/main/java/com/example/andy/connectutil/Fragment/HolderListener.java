package com.example.andy.connectutil.Fragment;

/**
 * Created by 95815 on 2017/3/10.
 * MainActivity实现该接口，将接口实现传到FragmentHolder中，用于回调界面视图变化。
 * mvc模式
 *
 */

public interface HolderListener {

    void startWifiConnection(String str);

     void setFraagment_State(String str);

}
