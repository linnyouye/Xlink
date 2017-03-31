package com.example.andy.connectutil.entity.Device;

import java.io.Serializable;

import io.xlink.wifi.sdk.XDevice;

/**
 * Created by andy on 2017/3/29.
 */

public class Device implements Serializable{
    private boolean isOnline;
    private String Product_ID;
    private XDevice xDevice;
    private DeviceDate data;

    public String getProduct_ID() {return Product_ID;}

    public void setProduct_ID(String product_ID) {Product_ID = product_ID;}

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public XDevice getxDevice() {
        return xDevice;
    }

    public void setxDevice(XDevice xDevice) {
        this.xDevice = xDevice;
    }

    public DeviceDate getData() {
        return data;
    }

    public void setData(DeviceDate data) {
        this.data = data;
    }
}
