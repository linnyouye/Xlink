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

import com.example.andy.connectutil.Helper.BathBullyHelper;
import com.example.andy.connectutil.R;
import com.example.andy.connectutil.entity.Device.BathBully;
import com.example.andy.connectutil.entity.Device.Device;

import io.xlink.wifi.sdk.XlinkCode;

/**
 * Created by andy on 2017/4/7.
 */

public class BathbullyFragment extends Fragment {

    private boolean powofheatone=false;
    private boolean powofheattwo=false;
    private boolean powoflight=false;
    private boolean powofpump=false;

    private boolean powerofdelay=false;
    private boolean powernoff=false;

    private TextView delay;
    private ImageButton power;

    private Device device;
    private BathBullyHelper bathBullyHelper;
    private BathBully bathBully;
    private Button heating1;
    private Button heating2;
    private Button light;
    private Button pump;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bathBullyHelper=new BathBullyHelper();
        //device=bathBullyHelper.getDevice();
        //bathBully=bathBullyHelper.update();
        View view=inflater.inflate(R.layout.fragment_view_bathbully,container,false);

        delay=(TextView)view.findViewById(R.id.tv_led_countdown);
        power=(ImageButton)view.findViewById(R.id.ibtn_led_power);

        heating1=(Button)view.findViewById(R.id.bathbully_btn_one);
        heating2=(Button)view.findViewById(R.id.bathbully_btn_two);
        light=(Button)view.findViewById(R.id.bathbully_btn_three);
        pump=(Button)view.findViewById(R.id.bathbully_btn_four);
        heating1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(powofheatone)
                    bathBullyHelper.setDataPoint(1,XlinkCode.DP_TYPE_BOOL,false);
                else
                    bathBullyHelper.setDataPoint(1,XlinkCode.DP_TYPE_BOOL,true);
            }
        });
        heating2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(powofheattwo)
                    bathBullyHelper.setDataPoint(2,XlinkCode.DP_TYPE_BOOL,false);
                else
                    bathBullyHelper.setDataPoint(2,XlinkCode.DP_TYPE_BOOL,true);
            }
        });
        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(powoflight)
                    bathBullyHelper.setDataPoint(3,XlinkCode.DP_TYPE_BOOL,false);
                else
                    bathBullyHelper.setDataPoint(3,XlinkCode.DP_TYPE_BOOL,true);
            }
        });
        pump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(powofpump)
                    bathBullyHelper.setDataPoint(4,XlinkCode.DP_TYPE_BOOL,false);
                else
                    bathBullyHelper.setDataPoint(4,XlinkCode.DP_TYPE_BOOL,true);
            }
        });

        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(powernoff)
                    bathBullyHelper.setDataPoint(0, XlinkCode.DP_TYPE_BOOL,false);
                else
                    bathBullyHelper.setDataPoint(0, XlinkCode.DP_TYPE_BOOL,true);
            }
        });
        delay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(powerofdelay)
                    bathBullyHelper.setDataPoint(5, XlinkCode.DP_TYPE_BOOL,false);
                else
                    bathBullyHelper.setDataPoint(5, XlinkCode.DP_TYPE_BOOL,true);
            }
        });

        return view;
    }
}
