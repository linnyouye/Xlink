package com.example.andy.connectutil.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.andy.connectutil.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 95815 .
 * Date:2017/4/7.
 * Writter: waiwen .
 * E-mail:iwaiwen@163.com .
 */

public class FragmentHoldertest {



    private String Fragment_State = "Main";
  HolderListener holderListener;

    private FragmentManager fragmentManager;
    private List<Fragment> fragmentList;

   public static int fragmentLayoutId = R.id.fragment_layout;

    public FragmentHoldertest(HolderListener holderListener, FragmentManager fragmentManager) {
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

}
