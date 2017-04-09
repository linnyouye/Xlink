package com.example.andy.connectutil.Fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.andy.connectutil.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 95815 on 2017/3/10.
 * 该类用于管理Fragment,以及回调界面视图变化
 *
 * 目前网上有更好的fragment管理实现方法，将可以考虑替换，
 *
 */

public class FragmentHolder {

    private String Fragment_State = "Main";
    HolderListener holderListener;

    private FragmentManager fragmentManager;
    private List<Fragment> fragmentList;

    public static int fragmentLayoutId = R.id.fragment_layout;

    public FragmentHolder(HolderListener holderListener,FragmentManager fragmentManager) {
        this.holderListener=holderListener;
        this.fragmentManager = fragmentManager;
        fragmentList = new ArrayList<>();
    }

    /**
     * @return 获得当前的状态
     */
    public String getFragment_State() {
        return Fragment_State;
    }

    /**
     * @param fragment_State 设置当前的状态,私有方法
     */
    private void setFragment_State(String fragment_State) {
        Fragment_State = fragment_State;
        holderListener.setFraagment_State(fragment_State);
    }


    /**
     * @param fragment 要添加的fragment
     * @param tag      fragment的tag
     * @param isBackToStack  是否添加到回退栈中
     */
    public void addFragment(Fragment fragment, String tag, boolean isBackToStack) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.animator_fragment_enter,R.anim.animator_fragment_exit,
                R.anim.animator_fragment_enter,R.anim.animator_fragment_exit
        ).add(fragmentLayoutId,fragment,tag);
        if(isBackToStack){
            transaction.addToBackStack(tag);
        }
        transaction.commit();

    }

    /**
     * @param fragment 要替换的fragment
     * @param tag
     * @param isBackToStack   是否添加到回退栈中
     */
    public void replaceFragment(Fragment fragment, String tag, boolean isBackToStack){

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.animator_fragment_enter,R.anim.animator_fragment_exit,
                R.anim.animator_fragment_enter,R.anim.animator_fragment_exit
        ).replace(fragmentLayoutId,fragment,tag);
        if(isBackToStack){
            transaction.addToBackStack(tag);
        }
        transaction.commit();

    }


    /**
     * @param tag 根据tag值来删除fragment
     */
    public void removeFragmentByTag(String tag){

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if( fragment!= null && fragment.isAdded()){
            transaction.remove(fragment).commit();
        }
    }

    /**
     * 删除所有的fragment，且事务不添加到回退栈
     */
    public void removeAllFragment(){
        fragmentList = fragmentManager.getFragments();
        for(Fragment fragment:fragmentList){
            if(fragment !=null){
                fragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss();
                //同时把栈清空
               fragmentManager.popBackStackImmediate();
            }
        }

    }

    /**
     * @param fragmentLayoutId 修改layoutId
     */
    public void setFragmentLayoutId(int fragmentLayoutId) {
        this.fragmentLayoutId = fragmentLayoutId;
    }


    /**
     * @return 获取到fragmentManager
     */
    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }


//删除除了自己之外所有的fragment
    public void removeOrhterFragment(){
        fragmentList = fragmentManager.getFragments();
        for(int i=0;i<fragmentList.size()-1;i++){
            Fragment fragment=fragmentList.get(i);
            if(fragment !=null){
                fragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss();
                //同时把栈清空
                fragmentManager.popBackStackImmediate();
            }
        }

    }















//    private HolderListener holderListener;
//    private FragmentManager manager;
//
//    private int state = 0;
//    public static final int MAIN_PAGE = 0;
//    public  static final int SELECT_FRAGMENT = 1;
//    public static final int WIFI_CONNECTION_FRAGMENT = 2;
//
//
//    /**
//     * @param state 记录当前处于哪一个Fragment，需要改进，目前能记录的状态为主页面，选择设备页面，
//     */
//    public void setState(int state) {
//        this.state = state;
//        if(state == MAIN_PAGE){
//            holderListener.setMainPage("主界面", View.INVISIBLE);
//        }else if(state == SELECT_FRAGMENT){
//            holderListener.setMainPage("选择设备列表", View.VISIBLE);
//        }else if(state == WIFI_CONNECTION_FRAGMENT){
//            holderListener.setMainPage("设备配网", View.VISIBLE);
//        }
//
//    }
//    public void onBackupStatus(){
//
//
//    }
//
//
//    public int getState() {
//        return state;
//    }
//
//    /**
//     * @param holderListener 主活动实例化该类和获得的FragmentManager时将接口实现类传递进来
//     * @param manager
//     */
//    public FragmentHolder(HolderListener holderListener, FragmentManager manager) {
//        super();
//        this.holderListener = holderListener;
//        this.manager = manager;
//    }
//
//    /**
//     * @param fragment 添加fragment
//     */
//    public void addFragment(Fragment fragment,String tag) {
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.add(R.id.fragment_layout,fragment,tag);
//            transaction.commit();
//    }
//
//    /**
//     * @param tag 移除fragment
//     */
//    public void removeFragmentByTag(String tag) {
//            FragmentTransaction transaction = manager.beginTransaction();
//            Fragment fragment = manager.findFragmentByTag(tag);
//        if(fragment != null){
//            transaction.remove(fragment);
//            transaction.commit();
//            setState(0);}
//    }
//
//    /**
//     * @param frament 替换fragment
//     */
//    public void replaceFragment(Fragment frament,String tag) {
//
//        FragmentTransaction transaction = manager.beginTransaction();
//            transaction.replace(R.id.fragment_layout, frament,tag);
//            transaction.addToBackStack(null);
//            transaction.commit();
//    }



}
