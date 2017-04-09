package com.example.andy.connectutil.Helper;

import android.util.Log;

import com.example.andy.connectutil.entity.Device.Device;
import com.example.andy.connectutil.entity.Device.FanLinght;
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

public class FanLightHelper {
    private Device device;
    private FanLinght data;

   public   FanLightHelper(Device device)
   {
       this.device=device;
       XlinkAgent.getInstance().connectDevice(device.getxDevice(), new ConnectDeviceListener() {
           @Override
           public void onConnectDevice(XDevice xDevice, int i) {
               Log.d(TAG, "onConnectDevice: code:"+i);
           }
       });
   }
   /* private List<Device> list;

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
*/

    public String TAG="FanLightHelper";
    public FanLinght update()
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
                FanLinght f=new FanLinght();
                data=(FanLinght) f.parseFromDataPoints(list);
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
            FanLinght date=(FanLinght)device.getData();
            @Override
            public void onSetDataPoint(XDevice xDevice, int i, int i1) {
                if(i==XlinkCode.SUCCEED){
                    switch (index){
                        case 0:
                              date.Power=(boolean)value;
                            break;
                        case 1:
                            date.PowerOfFanc=(boolean)value;
                            break;
                        case 2:
                            date.PowerOfLight=(boolean)value;
                            break;
                        case 3:
                            date.FanDirection=(byte)value;
                            break;
                        case 4:
                            date.Model=(byte)value;
                            break;
                        case 5:
                            date.FanModel=(byte)value;
                            break;
                        case 6:
                            date.FanPosition=(byte)value;
                            break;
                        case 7:
                            date.Coolor_Tem=(byte)value;
                            break;
                        case 8:
                            date.brightness=(byte)value;
                            break;
                        case 9:
                            date.Timing=(byte)value;
                            break;
                    }

                }

            }
        });

    }
}
