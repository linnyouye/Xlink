package com.example.andy.connectutil;

import android.content.Context;
import android.util.Log;

import com.example.andy.connectutil.entity.Net.Key;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import java.util.Random;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.XlinkCode;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.ConnectDeviceListener;
import io.xlink.wifi.sdk.listener.GetSubscribeKeyListener;
import io.xlink.wifi.sdk.listener.SendPipeListener;
import io.xlink.wifi.sdk.listener.SetDeviceAccessKeyListener;
import io.xlink.wifi.sdk.listener.SubscribeDeviceListener;
import io.xlink.wifi.sdk.listener.XlinkNetListener;

/**
 * Created by andy on 2017/3/15.
 */

public class XlinkConnect {
    private static String TAG = "XlinkConnect";
    public static int userId=0;
    public static String authorize="";
    public static String accessToken="";
    public static String refreshToken="";
    public static long expireTime=0;

    public static void init(Context context){
        Log.i(TAG, "init: ");

        XlinkAgent.init(context);
        XlinkAgent.getInstance().debug(true);
        XlinkAgent.getInstance().addXlinkListener(new XlinkNetListener() {
            @Override
            public void onStart(int i) {
                Log.i(TAG, "onStart: "+i);
            }

            @Override
            public void onLogin(int i) {
                Log.i(TAG, "onLogin: "+i);
            }

            @Override
            public void onLocalDisconnect(int i) {
                Log.i(TAG, "onLocalDisconnect: "+i);
            }

            @Override
            public void onDisconnect(int i) {
                Log.i(TAG, "onDisconnect: "+i);
            }

            @Override
            public void onRecvPipeData(short i, XDevice xDevice, byte[] bytes) {
                Log.i(TAG, "onRecvPipeData: "+i);
            }

            @Override
            public void onRecvPipeSyncData(short i, XDevice xDevice, byte[] bytes) {
                Log.i(TAG, "onRecvPipeSyncData: "+i);
            }

            @Override
            public void onDeviceStateChanged(XDevice xDevice, int i) {
                Log.i(TAG, "onDeviceStateChanged: "+i);

            }

            @Override
            public void onDataPointUpdate(XDevice xDevice, List<DataPoint> list, int i) {
                Log.i(TAG, "onDataPointUpdate: "+list.toString());
                /*for(Device device:deviceList){
                    if(device.getxDevice().getDeviceId()==xDevice.getDeviceId()){
                        device.setData(DeviceData.parseFromDataPoints(list));
                        deviceListenerCallback(xDevice.getDeviceId());
                    }
                }
                for(DataPoint dp:list){
                    Log.i(TAG, "onDataPointUpdate: "+dp.toString());
                }*/
            }
            @Override
            public void onEventNotify(EventNotify eventNotify) {

               /* try{
                    String notifyData=new String(eventNotify.notifyData);
                    JSONObject obj=new JSONObject(notifyData.substring(notifyData.indexOf("{")));
                    int deviceId=obj.optInt("device_id");
                    String type=obj.optString("type");
                    for(Device device:deviceList){
                        if(device.getxDevice().getDeviceId()==deviceId){
                            if(type.equals("online")){
                                device.setOnline(true);
                            }else if(type.equals("offline")){
                                device.setOnline(false);
                            }
                        }
                    }
                    deviceListenerCallback(deviceId);
                }catch (Exception e){
                    e.printStackTrace();
                }*/
            }
        });
        XlinkAgent.getInstance().start();
        XlinkAgent.getInstance().login(userId,authorize);
    }



    //订阅（绑定）局域网扫描到的设备
    public static void bindDevice(Context context,XDevice device,BinderDeviceListner listener){
        Log.i(TAG, "bindDevice: "+XlinkAgent.deviceToJson(device));
        init(context);
        if(device.getAccessKey()>0&&device.getSubKey()>0){
            subscribeDevice(device,listener);
        }else if(device.getAccessKey()<0){
            setAccessKey(device, new Random().nextInt(999999),listener);
        }else{
            getSubscribeKey(device,listener);
        }

    }

    private static void setAccessKey(final XDevice device, int accessKey, final BinderDeviceListner listener){
        XlinkAgent.getInstance().setDeviceAccessKey(device, accessKey, new SetDeviceAccessKeyListener() {
            @Override
            public void onSetLocalDeviceAccessKey(XDevice xDevice, int i, int i1) {
                Log.i(TAG, "onSetLocalDeviceAccessKey: "+i);
                if(i==XlinkCode.SUCCEED){
                    getSubscribeKey(device,listener);
                }else{
                    listener.bindDevice(device,i);
                }
            }
        });
    }

    private static void  getSubscribeKey(final XDevice device, final BinderDeviceListner listener){

        XlinkAgent.getInstance().getDeviceSubscribeKey(device, device.getAccessKey(), new GetSubscribeKeyListener() {
            @Override
            public void onGetSubscribekey(XDevice xDevice, int i, int i1) {
                Log.i(TAG, "getSubscribeKey: "+i);
                if(i==XlinkCode.SUCCEED){
                    subscribeDevice(device,listener);
                }else{
                    listener.bindDevice(device,i);
                }

            }
        });
    }

    private static void subscribeDevice(final XDevice device, final BinderDeviceListner listener){
        int ret=XlinkAgent.getInstance().subscribeDevice(device, device.getSubKey(), new SubscribeDeviceListener() {
            @Override
            public void onSubscribeDevice(XDevice xDevice, int i) {
                listener.bindDevice(device,0);

            }
        });
        if(ret<0){
            listener.bindDevice(device,ret);
            connectDeviceListener.onConnectDevice(device,ret);
        }

    }

    private static ConnectDeviceListener connectDeviceListener = new ConnectDeviceListener() {

        @Override
        public void onConnectDevice(XDevice xDevice, int result) {

            switch (result) {
                case XlinkCode.DEVICE_STATE_LOCAL_LINK:
                    Log.d(TAG, "onConnectDevice: 连接设备成功 设备处于内网 ");
                    // 连接设备成功 设备处于内网
                    break;
                case XlinkCode.DEVICE_STATE_OUTER_LINK:
                    Log.d(TAG, "onConnectDevice: 连接设备成功 设备处于云端 ");
                    // 连接设备成功 设备处于云端
                    break;
                case XlinkCode.CONNECT_DEVICE_INVALID_KEY:
                    Log.d(TAG, "onConnectDevice: 设备授权码错误 ");
                    // 设备授权码错误
                    break;
                case XlinkCode.CONNECT_DEVICE_OFFLINE:
                    Log.d(TAG, "设备不在线");
                    // 设备不在线
                    break;
                case XlinkCode.CONNECT_DEVICE_TIMEOUT:
                    Log.d(TAG, "onConnectDevice: 连接设备超时了，（设备未应答，或者服务器未应答）");
                    // 连接设备超时了，（设备未应答，或者服务器未应答）
                    break;
                case XlinkCode.CONNECT_DEVICE_SERVER_ERROR:
                    Log.d(TAG, "onConnectDevice: 错误 ");
                    break;
                case XlinkCode.CONNECT_DEVICE_OFFLINE_NO_LOGIN:
                    Log.d(TAG, "连接设备失败，设备未在局域网内，且当前手机只有局域网环境 ");
                    //连接设备失败，设备未在局域网内，且当前手机只有局域网环境"
                    break;
                default:
                    break;
            }
        }
    };
    public static void sendTODevice(XDevice device,SendDatelistener listner)
    {

        int ret = XlinkAgent.getInstance().sendPipeData(device,"1111".getBytes(), pipeListener);
        if (ret < 0) {
            switch (ret) {
                case XlinkCode.NO_CONNECT_SERVER:
                    Log.d(TAG,"发送数据失败，手机未连接服务器");
                    break;
                case XlinkCode.NETWORD_UNAVAILABLE:
                    Log.d(TAG,"当前网络不可用,发送数据失败");
                    break;
                case XlinkCode.NO_DEVICE:
                    Log.d(TAG,"未找到设备");
                    XlinkAgent.getInstance().initDevice(device);
                    break;
                default:
                    Log.d(TAG,"发送数据失败，错误码：" + ret);
                    break;
            }
        } else {
            //发送数据成功
            listner.OnRecieve();
        }
    }
    private static SendPipeListener pipeListener = new SendPipeListener() {

        @Override
        public void onSendLocalPipeData(XDevice device, int code, int messageId) {
            // setDeviceStatus(false);
            switch (code) {
                case XlinkCode.SUCCEED:
                    Log.d(TAG,"发送数据,msgId:" + messageId + "成功");
                    break;
                case XlinkCode.TIMEOUT:
                    // 重新调用connect
                    Log.d(TAG,"发送数据,msgId:" + messageId + "超时");
                    break;
                case XlinkCode.SERVER_CODE_UNAUTHORIZED:
                    //订阅关系失败，请调用订阅函数后重新连接
                    break;
                case XlinkCode.SERVER_DEVICE_OFFLIEN:
                    Log.d(TAG,"设备不在线");
                    break;
                default:
                    Log.d(TAG,"控制设备其他错误码:" + code);
                    break;
            }
        }
    };
    public static interface BinderDeviceListner
    {
        void bindDevice(XDevice device,int i);
    }
    public static  interface SendDatelistener
    {
        void OnRecieve();
    }

    public static void getLoginResult(String json){
        try {
            JSONObject obj=new JSONObject(json);

            userId=obj.optInt(Key.USER_ID);
            authorize=obj.optString(Key.AUTHORIZE);
            accessToken=obj.optString(Key.ACCESS_TOKEN);
            refreshToken=obj.optString(Key.REFRESH_TOKEN);
            expireTime=System.currentTimeMillis()+obj.optInt(Key.EXPIRE_IN);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}




