package com.example.andy.connectutil;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.andy.connectutil.entity.Net.HttpUtils;
import com.example.andy.connectutil.entity.Net.LoginUtil;
import com.hiflying.smartlink.OnSmartLinkListener;
import com.hiflying.smartlink.SmartLinkedModule;

import java.util.ArrayList;
import java.util.List;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkCode;

public class andy extends AppCompatActivity implements View.OnClickListener{
    private Button start;
    private Button subscrib;
    private WiFiConfig wiFiConfig;
    private List<XDevice> list;
    private Button resultButton;
    private Button send;
    private Button senVertify;
    private Button getDevices;
    private boolean Result_Bind=false;
    private boolean Result_Send=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_andy);
        wiFiConfig=new WiFiConfig(this, new OnSmartLinkListener() {
            @Override
            public void onLinked(SmartLinkedModule smartLinkedModule) {

            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onTimeOut() {

            }
        });
        list=new ArrayList<>();
        XlinkConnect.init(this);
        start=(Button)findViewById(R.id.Start);
        subscrib=(Button) findViewById(R.id.num);
        resultButton=(Button)findViewById(R.id.Result);
        send=(Button)findViewById(R.id.send);
        send.setOnClickListener(this);
        start.setOnClickListener(this);
        subscrib.setOnClickListener(this);
        resultButton.setOnClickListener(this);
        senVertify=(Button)findViewById(R.id.sendVertifyCOde);
        senVertify.setOnClickListener(this);
        getDevices=(Button)findViewById(R.id.getDevices);
        getDevices.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Start:
               wiFiConfig.ScanWifi("", new WiFiConfig.OnBindDeviceListner() {
                    @Override
                    public void getDevice(XDevice device) {
                        list.add(device);
                        Log.d("getDevice", "getDevice: "+list.size());
                    }
                });
                //Toast.makeText(this,list.size()+"",Toast.LENGTH_LONG).show();*/
                /*wiFiConfig.StartConfig("");*/
                break;
            case R.id.num:
                Toast.makeText(this,"This list have"+list.size()+"",Toast.LENGTH_LONG).show();
               XlinkConnect.bindDevice(list.get(0), new XlinkConnect.BinderDeviceListner() {
                   @Override
                   public void bindDevice(XDevice device, int i) {
                       if(i==XlinkCode.SUCCEED)
                       {
                           Result_Bind=true;
                       }

                   }
               });
                break;
            case R.id.Result:
                if(Result_Bind)
                    Toast.makeText(this,"点阅成功",Toast.LENGTH_LONG).show();
                if(Result_Send)
                    Toast.makeText(this,"发送成功",Toast.LENGTH_LONG).show();
                break;
            case R.id.send:
                XlinkConnect.sendTODevice(list.get(0), new XlinkConnect.SendDatelistener() {
                    @Override
                    public void OnRecieve() {
                        Result_Send=true;
                    }
                });
                break;
            case R.id.sendVertifyCOde:
                //getAuthCode();
                login();
                break;
            case R.id.getDevices:
                getDevices();
        }
    }
    private void getAuthCode(){
        String phone="18819259421";

        LoginUtil.getAuthCode(phone, new HttpUtils.HttpUtilsListner() {

            @Override
            public void onSuccess(String content) {
                Toast.makeText(getApplicationContext(),content,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(int code, String msg) {
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDevices()
    {
        LoginUtil.getDevices(new HttpUtils.HttpUtilsListner() {


            @Override
            public void onSuccess(String content) {
                Toast.makeText(getApplicationContext(),content,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(int code, String msg) {
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void login(){
        final String account="18819259421";
        final String password="123456";
        LoginUtil.login(account, password, new HttpUtils.HttpUtilsListner() {


            @Override
            public void onSuccess(String content) {
                Toast.makeText(getApplicationContext(),content,Toast.LENGTH_SHORT).show();
                XlinkConnect.getLoginResult(content);
            }

            @Override
            public void onFailed(int code, String msg) {
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
