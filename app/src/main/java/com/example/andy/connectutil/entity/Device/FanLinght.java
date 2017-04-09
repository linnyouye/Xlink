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

    public boolean isPower() {
        return Power;
    }

    public void setPower(boolean power) {
        Power = power;
    }

    public boolean isPowerOfFanc() {
        return PowerOfFanc;
    }

    public void setPowerOfFanc(boolean powerOfFanc) {
        PowerOfFanc = powerOfFanc;
    }

    public boolean isPowerOfLight() {
        return PowerOfLight;
    }

    public void setPowerOfLight(boolean powerOfLight) {
        PowerOfLight = powerOfLight;
    }

    public byte getFanDirection() {
        return FanDirection;
    }

    public void setFanDirection(byte fanDirection) {
        FanDirection = fanDirection;
    }

    public byte getModel() {
        return Model;
    }

    public void setModel(byte model) {
        Model = model;
    }

    public byte getFanModel() {
        return FanModel;
    }

    public void setFanModel(byte fanModel) {
        FanModel = fanModel;
    }

    public byte getFanPosition() {
        return FanPosition;
    }

    public void setFanPosition(byte fanPosition) {
        FanPosition = fanPosition;
    }

    public byte getCoolor_Tem() {
        return Coolor_Tem;
    }

    public void setCoolor_Tem(byte coolor_Tem) {
        Coolor_Tem = coolor_Tem;
    }

    public byte getBrightness() {
        return brightness;
    }

    public void setBrightness(byte brightness) {
        this.brightness = brightness;
    }

    public byte getTiming() {
        return Timing;
    }

    public void setTiming(byte timing) {
        Timing = timing;
    }

    public byte brightness;//亮度
    public byte Timing;

    @Override
    public DeviceDate parseFromDataPoints(List<DataPoint> list) {
        FanLinght data=new FanLinght();
        for(DataPoint dp:list){
            Log.i(TAG, "parseFromDataPoints: "+dp.getIndex()+","+dp.getValue());
            switch (dp.getIndex()){
                case 0:
                    data.Power=(byte)dp.getValue()>0;
                    break;
                case 1:
                    data.PowerOfFanc=(byte)dp.getValue()>0;
                    break;
                case 2:
                    data.PowerOfLight=(byte)dp.getValue()>0;
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
