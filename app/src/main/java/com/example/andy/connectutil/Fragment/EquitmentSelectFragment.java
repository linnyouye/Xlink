package com.example.andy.connectutil.Fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.andy.connectutil.R;
import com.example.andy.connectutil.entity.Net.Content;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 95815 on 2017/3/10.
 */

public class EquitmentSelectFragment extends BaseFragment implements View.OnClickListener {


    public static final String Fragment_Tag_State = "EquitmentSelectFragment";


    @Bind(R.id.select_btn_fanled)
    Button btn_fanLed;
    @Bind(R.id.select_btn_open)
    Button btn_open;
    @Bind(R.id.select_btn_led)
    Button btn_LED;
    @Bind(R.id.select_btn_bath)
        Button btn_bath;


    @Override
    protected String getState() {
        return Fragment_Tag_State;
    }

    public static EquitmentSelectFragment newInstance() {

        Bundle args = new Bundle();
        EquitmentSelectFragment fragment = new EquitmentSelectFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getFragmentLayoutId() {
        return R.layout.view_equitment_select;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this,view);
    }



    @Override
    public void initData() {
        //  holderListener.setMainPage(getString(R.string.select_equitment),View.VISIBLE);
    }




@OnClick({R.id.select_btn_bath,R.id.select_btn_open,R.id.select_btn_led,R.id.select_btn_fanled})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_btn_fanled:
                holderListener.startWifiConnection(Content.FanLIght_ID);
                break;
            case R.id.select_btn_open:
                holderListener.startWifiConnection(Content.Light_ID);
                break;
            case R.id.select_btn_led:
                holderListener.startWifiConnection(Content.LEDLIght_ID);
                break;
            case R.id.select_btn_bath:
                holderListener.startWifiConnection(Content.BathBully_ID);
                break;

        }
    }

}
