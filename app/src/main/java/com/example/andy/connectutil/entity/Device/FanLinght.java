package com.example.andy.connectutil.entity.Device;

import android.util.Log;

import java.io.Serializable;
import java.util.List;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.bean.DataPoint;

/**
 * Created by andy on 2017/3/15.
 */

public class FanLinght extends DeviceDate implements Serializable {
    public String TAG="FanLight";
    public boolean Power;//总开关
    public boolean PowerOfFanc;
    public boolean PowerOfLight;
    public byte FanDirection;//风扇方向
    public byte Model;//模式
    public byte FanModel;//风扇档位模式
    public byte FanPosition;//风扇档位
    public byte Coolor_Tem;//色温
    public byte brightness;//亮度
    public byte Timing;

    @Override
    public DeviceDate parseFromDataPoints(List<DataPoint> list) {
        FanLinght data=new FanLinght();
        for(DataPoint dp:list){
            Log.i(TAG, "parseFromDataPoints: "+dp.getIndex()+","+dp.getValue());
            switch (dp.getIndex()){
                case 0:
                    data.Power=(boolean)dp.getValue();
                    break;
                case 1:
                    data.PowerOfFanc=(boolean)dp.getValue();
                    break;
                case 2:
                    data.PowerOfLight=(boolean)dp.getValue();
                    break;
                case 3:
                    data.FanDirection=(byte)dp.getValue();
                    break;
                case 4:
                    data.Model=(byte)dp.getValue();
                    break;
                case 5:
                    data.FanModel=(byte)dp.getValue();
                    break;
                case 6:
                    data.FanPosition=(byte)dp.getValue();
                    break;
                case 7:
                    data.Coolor_Tem=(byte)dp.getValue();
                    break;
                case 8:
                    data.brightness=(byte)dp.getValue();
                    break;
                case 9:
                    data.Timing=(byte)dp.getValue();
                    break;
            }
        }
        return data;
    }
}
