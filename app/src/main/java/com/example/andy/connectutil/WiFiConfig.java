package com.example.andy.connectutil;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.andy.connectutil.entity.WifiUtils;
import com.hiflying.smartlink.ISmartLinker;
import com.hiflying.smartlink.OnSmartLinkListener;
import com.hiflying.smartlink.v7.MulticastSmartLinker;

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

    public WiFiConfig(Context context,OnSmartLinkListener listener)
    {
        smartLinker = MulticastSmartLinker.getInstance();
        ssid = WifiUtils.getWifiSSID(context);
        this.context=context;
        OnCreateWifiCOnfig(listener);
    }
    public void OnCreateWifiCOnfig(OnSmartLinkListener listener) {

            smartLinker.setOnSmartLinkListener(listener);

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
        int DeviceNum= XlinkAgent.getInstance().scanDeviceByProductId(productID, new ScanDeviceListener() {
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
            listner.toString();
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
        public void failed();
    }
}
