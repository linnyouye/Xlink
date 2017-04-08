package com.example.andy.connectutil.Helper;

import android.util.Log;

import com.example.andy.connectutil.entity.Device.Device;
import com.example.andy.connectutil.entity.Device.Light;
import com.example.andy.connectutil.entity.Net.HttpUtils;
import com.example.andy.connectutil.entity.Net.JsonParser;
import com.example.andy.connectutil.entity.Net.LoginUtil;

import java.util.ArrayList;
import java.util.List;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.XlinkCode;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.ConnectDeviceListener;
import io.xlink.wifi.sdk.listener.SetDataPointListener;
import io.xlink.wifi.sdk.listener.XlinkNetListener;

/**
 * Created by andy on 2017/3/30.
 */

public class LightHelper {
    private Device device;
    private Light data;
    private List<Device> list;

    public Device getDevice() {
        LoginUtil.getDevices(new HttpUtils.HttpUtilsListner() {
            @Override
            public void onSuccess(String content) {
                list=new ArrayList<Device>();
                Log.d(TAG, "onSuccess: "+content);
                list= JsonParser.parseDeviceList(content);
                device=list.get(0);
                XlinkAgent.getInstance().connectDevice(device.getxDevice(), new ConnectDeviceListener() {
                    @Override
                    public void onConnectDevice(XDevice xDevice, int i) {
                        Log.d(TAG, "onConnectDevice: code:"+i);
                    }
                });
            }

            @Override
            public void onFailed(int code, String msg) {

            }
        });
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String TAG="FanLightHelper";
    public Light update()
    {
        XlinkAgent.getInstance().addXlinkListener(new XlinkNetListener() {
            @Override
            public void onStart(int i) {

            }

            @Override
            public void onLogin(int i) {

            }

            @Override
            public void onLocalDisconnect(int i) {

            }

            @Override
            public void onDisconnect(int i) {

            }

            @Override
            public void onRecvPipeData(short i, XDevice xDevice, byte[] bytes) {

            }

            @Override
            public void onRecvPipeSyncData(short i, XDevice xDevice, byte[] bytes) {

            }

            @Override
            public void onDeviceStateChanged(XDevice xDevice, int i) {

            }

            @Override
            public void onDataPointUpdate(XDevice xDevice, List<DataPoint> list, int i) {
                Log.d(TAG, "onDataPointUpdate: "+list.toString());
                Light f=new Light();
                /*data=(FanLinght) f.parseFromDataPoints(list);*/
            }

            @Override
            public void onEventNotify(EventNotify eventNotify) {

            }
        });
        return data;
    }

    public void setDataPoint(final int index, int type, final Object value) {

        List<DataPoint> list = new ArrayList<>();
        DataPoint dp=new DataPoint(index,type);

        switch (type){
            case XlinkCode.DP_TYPE_BOOL:
                dp.setValueOfBool((boolean)value);
                break;
            case XlinkCode.DP_TYPE_BYTE:
                dp.setValueOfByte((byte)value);
                break;
            case XlinkCode.DP_TYPE_INT:
                dp.setValueOfInt((int)value);
                break;
            case XlinkCode.DP_TYPE_SHORT:
                dp.setValueOfShort((short)value);
                break;
        }

        list.add(dp);


        XlinkAgent.getInstance().setDataPoint(device.getxDevice(), list, new SetDataPointListener() {
            Light date=(Light) device.getData();
            @Override
            public void onSetDataPoint(XDevice xDevice, int i, int i1) {
                if(i==XlinkCode.SUCCEED){
                    switch (index){
                        case 0:
                            date.Power=(boolean)value;
                            break;
                        case 1:
                            date.Light_ID=(byte)value;
                            break;
                        case 2:
                            date.Power_One=(boolean)value;
                            break;
                        case 3:
                            date.Power_Two=(boolean)value;
                            break;
                        case 4:
                            date.Pwoer_Three=(boolean)value;
                            break;
                        case 5:
                            date.Power_Four=(boolean)value;
                            break;
                        case 6:
                            date.delayed=(boolean)value;
                            break;
                    }
                }

            }
        });

    }
}
