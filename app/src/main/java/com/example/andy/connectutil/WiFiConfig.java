package com.example.andy.connectutil;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.andy.connectutil.entity.WifiUtils;
import com.hiflying.smartlink.ISmartLinker;
import com.hiflying.smartlink.OnSmartLinkListener;
import com.hiflying.smartlink.SmartLinkedModule;
import com.hiflying.smartlink.v7.MulticastSmartLinker;

import java.util.ArrayList;
import java.util.List;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.XlinkCode;
import io.xlink.wifi.sdk.listener.ScanDeviceListener;

/**
 * Created by andy on 2017/3/14.
 */

public class WiFiConfig {
    private String TAG="WiFiConfig";
    private ISmartLinker smartLinker;

    private String ssid;


    private Context context;
    //使用WiFiconfig时先要就行实例化一些对象

    public WiFiConfig(Context context)
    {
        smartLinker = MulticastSmartLinker.getInstance();
        ssid = WifiUtils.getWifiSSID(context);
        this.context=context;
        OnCreateWifiCOnfig();
    }
    public void OnCreateWifiCOnfig() {

            smartLinker.setOnSmartLinkListener(new OnSmartLinkListener() {
                @Override
                public void onLinked(SmartLinkedModule smartLinkedModule) {
                    Toast.makeText(context, "配置成功", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCompleted() {
                    Toast.makeText(context, "配网完成", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onTimeOut() {
                    Toast.makeText(context, "配网超时", Toast.LENGTH_LONG).show();
                }
            });

    }
    //配置Wifi信息
    public  void StartConfig(String password)
    {
        try {
            smartLinker.start(context,password,ssid);//假如是多个设备的话是发送给所有设备密码
            Log.d(TAG, "StartConfig: 开始配网");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //扫描周围设备
    public void ScanWifi(String productID, final OnBindDeviceListner listner)
    {
        int DeviceNum= XlinkAgent.getInstance().scanDeviceByProductId("160fa6b0f89e03e9160fa6b0f89ed401", new ScanDeviceListener() {
            @Override
            public void onGotDeviceByScan(XDevice xDevice) {

                Log.d(TAG, "onGotDeviceByScan: ");
                
                if(isDeviceValid(xDevice)){
                    listner.getDevice(xDevice);
                    XlinkAgent.getInstance().initDevice(xDevice);
                }
            }
        });

        if(DeviceNum<0){
            showScanError(DeviceNum);
        }
    }
    private void showScanError(int errorCode){
        switch (errorCode){
            case XlinkCode.NETWORD_UNAVAILABLE:
                Toast.makeText(context, "设备不可用", Toast.LENGTH_LONG).show();
                break;
            case XlinkCode.NO_CONNECT_SERVER:
                Toast.makeText(context, "无连接设备", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private boolean isDeviceValid(XDevice device){
        if(device==null){
            return false;
        }
        String mac=device.getMacAddress();
        if(TextUtils.isEmpty(mac)){
            return false;
        }
        return true;
    }
    public static interface OnBindDeviceListner{
        public void getDevice(XDevice device);
    }
}
