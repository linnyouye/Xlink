package com.example.andy.connectutil.Fragment;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.FrameStats;

import com.example.andy.connectutil.Activity.MainActivity;
import com.example.andy.connectutil.R;
import com.example.andy.connectutil.andy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by 95815 on 2017/3/10.
 * 该类用于管理Fragment,以及回调界面视图变化
 * <p>
 * 目前网上有更好的fragment管理实现方法，将可以考虑替换，
 */

public class FragmentHolder {
    public Stack<String> fragmentstack;
    private String Fragment_State = "Main";
    HolderListener holderListener;

    MainActivity mainActivity;
    private Context context;
    private FragmentManager fragmentManager;
    private List<Fragment> fragmentList;

    public static int fragmentLayoutId = R.id.fragment_layout;

    public FragmentHolder(Context context,HolderListener holderListener, FragmentManager fragmentManager) {
        this.holderListener = holderListener;
        this.fragmentManager = fragmentManager;
        fragmentList = new ArrayList<>();
        this.context = context;
        mainActivity=(MainActivity)context;
        fragmentstack=new Stack<>();
        fragmentstack.push("Main");
        Fragment_State=fragmentstack.peek();
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
     * @param fragment      要添加的fragment
     * @param tag           fragment的tag
     * @param isBackToStack 是否添加到回退栈中
     */
    public void addFragment(Fragment fragment, String tag, boolean isBackToStack) {
        fragmentstack.push(tag);
        Fragment_State=tag;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.animator_fragment_enter, R.anim.animator_fragment_exit,
                R.anim.animator_fragment_enter, R.anim.animator_fragment_exit
        ).add(fragmentLayoutId, fragment, tag);
        if (isBackToStack) {
            transaction.addToBackStack(tag);
            mainActivity.notifybackup();
        }
        transaction.commit();

    }

    /**
     * @param fragment      要替换的fragment
     * @param tag
     * @param isBackToStack 是否添加到回退栈中
     */
    public void replaceFragment(Fragment fragment, String tag, boolean isBackToStack) {
        fragmentstack.push(tag);
        Fragment_State=fragmentstack.peek();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.animator_fragment_enter, R.anim.animator_fragment_exit,
                R.anim.animator_fragment_enter, R.anim.animator_fragment_exit
        ).replace(fragmentLayoutId, fragment, tag);
        if (isBackToStack) {
            transaction.addToBackStack(tag);
        }
        transaction.commit();
        mainActivity.notifybackup();
    }


    /**
     * @param tag 根据tag值来删除fragment
     */
    public void removeFragmentByTag(String tag) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null && fragment.isAdded()) {
            transaction.remove(fragment).commit();
        }
    }

    /**
     * 删除所有的fragment，且事务不添加到回退栈
     */
    public void removeAllFragment() {
  //      fragmentList = fragmentManager.getFragments();
//        for (Fragment fragment : fragmentList) {
//            if (fragment != null) {
//                fragmentManager.beginTransaction().remove(fragment).commit();
//                //同时把栈清空
//              //  fragmentManager.popBackStackImmediate();
//            }
//        }
fragmentManager.popBackStackImmediate(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        while (!fragmentstack.peek().equals("Main"))
            fragmentstack.pop();
        Fragment_State=fragmentstack.peek();
        mainActivity.notifybackup();

    }
    public boolean removeOne()
    {
        if(!fragmentstack.isEmpty())
        {
            fragmentstack.pop();
            Fragment_State=fragmentstack.peek();
        }
        mainActivity.notifybackup();
        return fragmentManager.popBackStackImmediate();
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
    public void removeOthterFragment(){
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


}
