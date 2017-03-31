package com.example.andy.connectutil.entity.Device;

import android.util.Log;

import java.io.Serializable;
import java.util.List;

import io.xlink.wifi.sdk.bean.DataPoint;

/**
 * Created by andy on 2017/3/15.
 */

public class Light extends DeviceDate implements Serializable{
    private String TAG="Light";
    public boolean Power;
    public boolean Light_ID;//路数ID
    public boolean Power_One;//一路开关
    public boolean Power_Two;//二路开关
    public boolean Pwoer_Three;//三路开关
    public boolean Power_Four;//四路开关
    public boolean delayed;


    @Override
    public DeviceDate parseFromDataPoints(List<DataPoint> list) {
        Light data=new Light();
        for(DataPoint dp:list){
            Log.i(TAG, "parseFromDataPoints: "+dp.getIndex()+","+dp.getValue());
            switch (dp.getIndex()){
                case 0:
                    data.Power=(boolean)dp.getValue();
                    break;
                case 1:
                    data.Light_ID=(boolean)dp.getValue();
                    break;
                case 2:
                    data.Power_One=(boolean)dp.getValue();
                    break;
                case 3:
                    data.Power_Two=(boolean)dp.getValue();
                    break;
                case 4:
                    data.Pwoer_Three=(boolean)dp.getValue();
                    break;
                case 5:
                    data.Power_Four=(boolean)dp.getValue();
                    break;
                case 6:
                    data.delayed=(boolean)dp.getValue();
                    break;
            }
        }
        return data;
    }
}
