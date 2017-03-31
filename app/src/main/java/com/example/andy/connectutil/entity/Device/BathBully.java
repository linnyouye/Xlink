package com.example.andy.connectutil.entity.Device;

import android.util.Log;

import java.io.Serializable;
import java.util.List;

import io.xlink.wifi.sdk.bean.DataPoint;

/**
 * Created by andy on 2017/3/29.
 */

public class BathBully extends DeviceDate implements Serializable {
    private String TAG="BathBully";
    public boolean Power;
    public boolean Power_one;//一路暖气开关
    public boolean Power_Two;//二路暖气开关
    public boolean Power_Light;//灯开关
    public boolean Power_Pump;//抽气开关
    public boolean delayed;//延时
    @Override
    public DeviceDate parseFromDataPoints(List<DataPoint> list) {
        BathBully data=new BathBully();
        for(DataPoint dp:list){
            Log.i(TAG, "parseFromDataPoints: "+dp.getIndex()+","+dp.getValue());
            switch (dp.getIndex()){
                case 0:
                    data.Power=(boolean)dp.getValue();
                    break;
                case 1:
                    data.Power_one=(boolean)dp.getValue();
                    break;
                case 2:
                    data.Power_Two=(boolean)dp.getValue();
                    break;
                case 3:
                    data.Power_Light=(boolean)dp.getValue();
                    break;
                case 4:
                    data.Power_Pump=(boolean)dp.getValue();
                    break;
                case 5:
                    data.delayed=(boolean)dp.getValue();
                    break;
            }
        }
        return data;
    }
}
