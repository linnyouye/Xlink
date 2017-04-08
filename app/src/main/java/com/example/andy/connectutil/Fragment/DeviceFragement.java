package com.example.andy.connectutil.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.andy.connectutil.R;
import com.example.andy.connectutil.WiFiConfig;
import com.example.andy.connectutil.XlinkConnect;
import com.example.andy.connectutil.entity.Net.Content;

import java.util.ArrayList;
import java.util.List;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkCode;

;

/**
 * Created by andy on 2017/4/2.
 */

public class DeviceFragement extends Fragment {
    public static final String TAG="ChoseScanedDevice";
    List<Button> buttonlist;
    private String produt_id;
    private  List<XDevice> devicelist;

    public static DeviceFragement newInstance(String product_ID) {

        Bundle args = new Bundle();
        args.putString("product_ID",product_ID);
        DeviceFragement fragment = new DeviceFragement();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buttonlist=new ArrayList<>();
        devicelist=new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout view=(LinearLayout)inflater.inflate(R.layout.devicefragment,container,false);
         produt_id=getArguments().getString("product_ID");
        scanDevice();
        for(int i=0;i<devicelist.size();i++)
        {
            Button buttonview=(Button)inflater.inflate(R.layout.devicetextveiw,null);
            view.addView(buttonview);
            buttonlist.add(buttonview);
        }
        setOnclik();
        setText();
        return view;
    }

    private void setOnclik() {
        for(int i=0;i<buttonlist.size();i++)
        {
            View button=buttonlist.get(i);
            final int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    XlinkConnect.init(getActivity());
                    XlinkConnect.bindDevice(devicelist.get(finalI),new  XlinkConnect.BinderDeviceListner()
                    {
                        @Override
                        public void bindDevice(XDevice device, int i) {
                            if(i== XlinkCode.SUCCEED)
                            {
                                Log.d(TAG, "bindDevice:  绑定设备成功");
                            }else 
                            {
                                Log.d(TAG, "bindDevice: 绑定设备失败");
                            }

                        }
                    });
                }
            });
        }
    }
    public void setText()
    {
        String DeviceName="";
        for(int i=0;i<buttonlist.size();i++)
        {
            switch (devicelist.get(i).getProductId())
            {
                case Content.FanLIght_ID:
                    DeviceName="风扇灯";
                    break;
                case Content.Light_ID:
                    DeviceName="灯";
                    break;
                case Content.LEDLIght_ID:
                    DeviceName="LED灯";
                    break;
                case Content.BathBully_ID:
                    DeviceName="浴霸";
                    break;
            }
            buttonlist.get(i).setText(DeviceName);
        }

    }
    private void scanDevice() {
        WiFiConfig w=new WiFiConfig(getActivity());
        w.ScanWifi(produt_id, new WiFiConfig.OnBindDeviceListner() {
            @Override
            public void getDevice(XDevice device) {
               devicelist.add(device);
            }
        });
    }

}
