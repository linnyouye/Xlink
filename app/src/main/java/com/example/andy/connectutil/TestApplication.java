package com.example.andy.connectutil;

import android.app.Application;

import java.util.List;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.XlinkNetListener;

/**
 * Created by andy on 2017/3/10.
 */

public class TestApplication extends Application implements XlinkNetListener {
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

    }

    @Override
    public void onEventNotify(EventNotify eventNotify) {

    }
}
