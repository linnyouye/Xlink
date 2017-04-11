package com.example.andy.connectutil.Fragment.DeviceFragment;

import android.os.Bundle;
import android.view.View;

import com.example.andy.connectutil.Fragment.BaseFragment;
import com.example.andy.connectutil.R;

import butterknife.ButterKnife;

/**
 * Created by 95815 .
 * Date:2017/4/10.
 * Writter: waiwen .
 * E-mail:iwaiwen@163.com .
 */

public class ControlFanLedFragment extends BaseFragment {

   public static final String TAG = "ControlFanLedFragment";


    public static ControlFanLedFragment newInstance() {

        Bundle args = new Bundle();
        ControlFanLedFragment fragment = new ControlFanLedFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_view_controlfanled;
    }

    @Override
    public void initView(View view) {
            ButterKnife.bind(this,view);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public String setState() {
        return this.TAG;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
