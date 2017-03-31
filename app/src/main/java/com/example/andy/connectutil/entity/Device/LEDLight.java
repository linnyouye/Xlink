package com.example.andy.connectutil.entity.Device;

import android.util.Log;

import java.io.Serializable;
import java.util.List;

import io.xlink.wifi.sdk.bean.DataPoint;

/**
 * Created by andy on 2017/3/15.
 */

public class LEDLight extends DeviceDate implements Serializable{

    public String TAG="LEDLight";
    public boolean Power;
    public byte Color_Tem;//色温
    public byte brightness;//亮度
    public boolean Power_NightLight;//小夜灯开关
    public byte ColorTem_NightLight;//夜灯色温
    public boolean delayed;//是否延时


    @Override
    public DeviceDate parseFromDataPoints(List<DataPoint> list) {
        LEDLight data=new LEDLight();
        for(DataPoint dp:list){
            Log.i(TAG, "parseFromDataPoints: "+dp.getIndex()+","+dp.getValue());
            switch (dp.getIndex()){
                case 0:
                    data.Power=(boolean)dp.getValue();
                    break;
                case 1:
                    data.Color_Tem=(byte)dp.getValue();
                    break;
                case 2:
                    data.brightness=(byte)dp.getValue();
                    break;
                case 3:
                    data.Power_NightLight=(boolean)dp.getValue();
                    break;
                case 4:
                    data.ColorTem_NightLight=(byte)dp.getValue();
                    break;
                case 5:
                    data.delayed=(boolean)dp.getValue();
                    break;
            }
        }
        return data;
    }
}
