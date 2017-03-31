package com.example.andy.connectutil.entity.Device;

import java.util.List;

import io.xlink.wifi.sdk.bean.DataPoint;

/**
 * Created by andy on 2017/3/29.
 */

public abstract class DeviceDate {
     public abstract  DeviceDate parseFromDataPoints(List<DataPoint> list);
}
