package com.example.andy.connectutil.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.andy.connectutil.Activity.LEDLightHelper;
import com.example.andy.connectutil.R;
import com.example.andy.connectutil.View.LedlView;
import com.example.andy.connectutil.entity.Device.Device;
import com.example.andy.connectutil.entity.Device.LEDLight;


import io.xlink.wifi.sdk.XlinkCode;

/**
 * Created by andy on 2017/4/6.
 */

public class LEDLightFragment extends Fragment {
    private boolean powerofdelay=false;
    private boolean powernoff=false;
    private boolean powerofnightlight=false;

    private TextView delay;
    private LedlView ledlView;
    private ImageButton power;

    private LEDLightHelper ledLightHelper;
    private LEDLight ledLight;
    private Device device;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_view_led,container,false);
        ledlView=(LedlView)view.findViewById(R.id.LEDLightView);
//         Device device=ledLightHelper.getDevice();
        ledLightHelper=new LEDLightHelper();
//         ledLight=ledLightHelper.update();
        delay=(TextView)view.findViewById(R.id.tv_led_countdown);
        power=(ImageButton)view.findViewById(R.id.ibtn_led_power);
        ledlView.setOncontrolListener(new LedlView.OnControlListener() {
            @Override
            public void onClickCenter() {

            }

            @Override
            public void onClickOne() {
                ledLightHelper.setDataPoint(1, XlinkCode.DP_TYPE_BYTE,(byte)1);
            }

            @Override
            public void onClickTwo() {
                ledLightHelper.setDataPoint(1, XlinkCode.DP_TYPE_BYTE,(byte)2);
            }

            @Override
            public void onClickThree() {
                ledLightHelper.setDataPoint(1, XlinkCode.DP_TYPE_BYTE,(byte)2);
            }

            @Override
            public void onClickBtnTL() {
                ledLightHelper.setDataPoint(2, XlinkCode.DP_TYPE_BYTE,(byte)1);
            }

            @Override
            public void onClickBtnTR() {
                ledLightHelper.setDataPoint(2, XlinkCode.DP_TYPE_BYTE,(byte)2);
            }

            @Override
            public void onClickBtnBL() {
                if(powerofnightlight)
                    ledLightHelper.setDataPoint(3, XlinkCode.DP_TYPE_BYTE,(byte)1);
                else
                    ledLightHelper.setDataPoint(3, XlinkCode.DP_TYPE_BYTE,(byte)2);
            }

            @Override
            public void onClickBtnBR() {
                ledLightHelper.setDataPoint(4, XlinkCode.DP_TYPE_BYTE,(byte)1);
            }
        });
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(powernoff)
                ledLightHelper.setDataPoint(0, XlinkCode.DP_TYPE_BOOL,false);
                else
                    ledLightHelper.setDataPoint(0, XlinkCode.DP_TYPE_BOOL,true);
            }
        });
        delay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(powerofdelay)
                ledLightHelper.setDataPoint(5, XlinkCode.DP_TYPE_BOOL,false);
                else
                    ledLightHelper.setDataPoint(5, XlinkCode.DP_TYPE_BOOL,true);
            }
        });

                return view;
    }

}
