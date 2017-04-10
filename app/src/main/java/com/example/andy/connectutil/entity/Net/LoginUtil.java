package com.example.andy.connectutil.entity.Net;

import android.util.Log;

import com.example.andy.connectutil.XlinkConnect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.xlink.wifi.sdk.XDevice;

/**
 * Created by andy on 2017/3/24.
 */

public class LoginUtil {
    //获取验证码
    public static void getAuthCode(String phone, HttpUtils.HttpUtilsListner listener) {

            try {
                JSONObject content=new JSONObject();
                content.put(Key.corp_id, Content.CORP_ID);
                content.put(Key.phone,phone);
                HttpUtils.init();
                HttpUtils.postJson(Url.GET_AUTH_CODE, content.toString(),null, listener);
            }catch (JSONException e)
            {
                e.printStackTrace();
            }


    }

    //校验验证码
    public static void verifyCode(String phone, String code, HttpUtils.HttpUtilsListner listener){
        try {
            JSONObject data=new JSONObject();
            data.put(Key.corp_id, Content.CORP_ID);
            data.put(Key.phone,phone);
            data.put(Key.verifycode,code);
            HttpUtils.postJson(Key.verifycode, data.toString(),null, listener);
        }catch (JSONException e)
        {
            e.printStackTrace();
        }


    }

    public static void registerByMail(String mail,String password,String userName,HttpUtils.HttpUtilsListner listener){/*{
        "email":"邮箱地址",
                "nickname":"昵称",
                "corp_id":"企业ID",
                "password":"认证密码",
                "source":"用户来源",
                "local_lang":"本地语言代码",
                "plugin_id":"应用插件ID"
    }*/
        try {
            JSONObject data=new JSONObject();
            data.put(Key.corp_id, Content.CORP_ID);
            data.put(Key.email,mail);
            data.put(Key.nickname,userName);
            data.put("source",Key.source);
            data.put(Key.password,password);
            data.put(Key.LOCAL_LANG,Key.LANG_CN);
            HttpUtils.postJson(Url.REGISTER, data.toString(),null, listener);
        }catch (JSONException e)
        {
            e.printStackTrace();
        }


    }

    //注册
    public static void register(String phone, String password, String code,String userName, HttpUtils.HttpUtilsListner listener) {
        try{
            JSONObject data=new JSONObject();
            data.put(Key.corp_id, Content.CORP_ID);
            data.put(Key.phone,phone);
            data.put(Key.nickname,userName);
            data.put(Key.verifycode,code);
            data.put("source",Key.source);
            data.put(Key.password,password);
            data.put(Key.LOCAL_LANG,Key.LANG_CN);
            HttpUtils.postJson(Url.REGISTER, data.toString(),null, listener);
        }catch (Exception e ){
            e.printStackTrace();
        }

    }

    //登录
    public static void login(String user, String password, HttpUtils.HttpUtilsListner listener) {
        try{
            JSONObject data=new JSONObject();
            data.put(Key.corp_id, Content.CORP_ID);
            if(user.contains("@")){
                data.put(Key.email,user);
            }else{
                data.put(Key.phone,user);
            }


            data.put(Key.password,password);
            HttpUtils.postJson(Url.LOGIN, data.toString(),null, listener);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    //获取设备列表
    public static void getDevices(HttpUtils.HttpUtilsListner listener){
        try{


            Map<String,String> header=new HashMap<>();
            header.put("Access-Token", XlinkConnect.accessToken);

            String url=String.format(Url.GET_DEVICE_LIST,XlinkConnect.userId);

            HttpUtils.get(url,header,listener);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }


    //取消订阅设备
    public static void unsubDevice(HttpUtils.HttpUtilsListner listener,int Device_id){
        try{
            JSONObject data=new JSONObject();
            data.put(Key.Device_id,Device_id);
            Map<String,String> header=new HashMap<>();
            header.put("Access-Token", XlinkConnect.accessToken);

            String url=String.format(Url.UNSBCRIB_DEVICE,XlinkConnect.userId);
            Log.d("UnSBdevice", "unsubDevice: "+XlinkConnect.accessToken);
            HttpUtils.postJson(url,data.toString(),header,listener);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
    //获取设备地理位置
    public static void getDeviceLocation(String pid,int deviceId,HttpUtils.HttpUtilsListner listener){
        try{

            Map<String,String> header=new HashMap<>();
            header.put("Access-Token",XlinkConnect.accessToken);
            String url=String.format(Url.GET_DEVICE_LOCATION,pid,deviceId);
            HttpUtils.get(url,header,listener);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    public static void getDeviceLocation(XDevice device, HttpUtils.HttpUtilsListner listener){
        getDeviceLocation(device.getProductId(),device.getDeviceId(),listener);
    }


    //忘记密码
    public static void forgetPassword(String account, HttpUtils.HttpUtilsListner listener){
        try{
            JSONObject data=new JSONObject();
            data.put(Key.CORP_ID, Content.CORP_ID);
            if(account.contains("@")){
                data.put(Key.EMAIL,account);
            }else{
                data.put(Key.PHONE,account);
            }

            HttpUtils.postJson(Url.FORGET_PASSWORD, data.toString(),null, listener);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    //重置密码
    public static void resetPassword(String account, String code, String password, HttpUtils.HttpUtilsListner listener){
        try{
            JSONObject data=new JSONObject();
            data.put(Key.CORP_ID, Content.CORP_ID);
            if(account.contains("@")){
                data.put(Key.EMAIL,account);
            }else{
                data.put(Key.PHONE,account);
            }
            data.put(Key.VERIFY_CODE,code);
            data.put(Key.NEW_PASSWORD,password);

            HttpUtils.postJson(Url.RESET_PASSWORD,data.toString(),null, listener);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    //获取用户信息
    public static void getUserInfo(int userId, HttpUtils.HttpUtilsListner listener){
        String url=String.format(Url.GET_USER_INFO,userId+"");
        HttpUtils.get(url,null,listener);
    }
}
