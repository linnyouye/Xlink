package com.example.andy.connectutil.entity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

/**
 * Created by andy on 2017/3/10.
 */

public class WifiUtils {
    public static String getWifiSSID(Context context)
    {
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo myWifi=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(myWifi.isConnected())
        {
            WifiManager wifiManager=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo=wifiManager.getConnectionInfo();
            String ssid=wifiInfo.getSSID();

                return ssid.replaceAll("\"","");
        }else {
            Toast.makeText(context,"请链接WiFi设备",Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public static String getWiFiName(Context context)
    {
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo myWifi=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(myWifi.isConnected())
        {
            WifiManager wifiManager=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo=wifiManager.getConnectionInfo();
            String name=wifiInfo.toString();

            return name;
        }else {
            Toast.makeText(context,"请链接WiFi设备",Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
