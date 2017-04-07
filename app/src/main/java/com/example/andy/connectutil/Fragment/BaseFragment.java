package com.example.andy.connectutil.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 95815 .
 * Date:2017/3/30.
 * Writter: waiwen .
 * E-mail:iwaiwen@163.com .
 */

public abstract class BaseFragment extends Fragment  {

    public static final String Fragment_Tag_State ="BaseFragment";
    private View rootview;
    protected   HolderListener holderListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //    return super.onCreateView(inflater, container, savedInstanceState);
        if (rootview != null) {
            return rootview;
        } else {
            rootview = inflater.inflate(getFragmentLayoutId(), container, false);

        }
        initView(rootview);
        initData();
        showLog("  onCreateView");
        return rootview;
    }





    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            holderListener = (HolderListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(getClass().getName()+"未继承接口");
        }
        showLog(":   onAttch");
    }

    /**
     * @return 获取该Fragment关联的View；
     */
    public abstract int getFragmentLayoutId();

    /**
     * @param view
     */
    public abstract void initView(View view);

    public abstract void initData();

    /**
     * @returndang 当前的fragmentTag
     */
    protected abstract String getState();
    public void   showLog(String str){
        Log.d("waiwen",getClass().getName()+str);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLog(":  onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLog(":  onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        showLog("    :onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        showLog(":   onResume"+getState());
        holderListener.setFraagment_State(getState());
    }

    @Override
    public void onStop() {
        super.onStop();
        showLog(":   onStop");
        holderListener.mainViewUpdate("主界面");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        showLog(":   onDestroyView");

    }

    public void onDestroy(){

        super.onDestroy();
        showLog(":   onDestroy");


    }

    @Override
    public void onDetach() {
        super.onDetach();
        showLog(":     onDetach");

    }


}
