package com.example.andy.connectutil.Fragment.DeviceFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.andy.connectutil.Helper.LightHelper;
import com.example.andy.connectutil.R;
import com.example.andy.connectutil.entity.Device.Device;
import com.example.andy.connectutil.entity.Device.Light;

import java.util.ArrayList;
import java.util.List;

import io.xlink.wifi.sdk.XlinkCode;

/**
 * Created by andy on 2017/4/6.
 */

public class LightFragment extends Fragment{
    private boolean powerofdelay=false;
    private boolean powernoff=false;

    private TextView delay;
    private ImageButton power;

    private int page=4;
    private LightHelper lightHelper;
    private Device device;
    private Light light;
    private List<Integer> layoutlist;
    private List<Integer>  idlist;
    private List<Button> buttonList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        lightHelper=new LightHelper();
        layoutlist=new ArrayList<>();
        idlist=new ArrayList<>();
        buttonList=new ArrayList<>();
        //device=lightHelper.getDevice();
        //light=lightHelper.update();
        layoutlist.add(R.layout.fragment_view_led_1);
        layoutlist.add(R.layout.fragment_view_led_2);
        layoutlist.add(R.layout.fragment_view_led_3);
        layoutlist.add(R.layout.fragment_view_led_4);
        View view=inflater.inflate(layoutlist.get(page-1),container,false);//light.Light_ID

        delay=(TextView)view.findViewById(R.id.tv_led_countdown);
        power=(ImageButton)view.findViewById(R.id.ibtn_led_power);

        idlist.add(R.id.led_btn_one);
        idlist.add(R.id.led_btn_two);
        idlist.add(R.id.led_btn_three);
        idlist.add(R.id.led_btn_four);
        for(int i=0;i<page;i++)//light.Light_ID
        {
            Button button=(Button)view.findViewById(idlist.get(i));
            final int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lightHelper.setDataPoint(finalI +1, XlinkCode.DP_TYPE_BOOL,true);
                }
            });
            buttonList.add(button);
        }
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(powernoff)
                    lightHelper.setDataPoint(0, XlinkCode.DP_TYPE_BOOL,false);
                else
                    lightHelper.setDataPoint(0, XlinkCode.DP_TYPE_BOOL,true);
            }
        });
        delay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(powerofdelay)
                    lightHelper.setDataPoint(6, XlinkCode.DP_TYPE_BOOL,false);
                else
                    lightHelper.setDataPoint(6, XlinkCode.DP_TYPE_BOOL,true);
            }
        });
        return view;
    }


}
