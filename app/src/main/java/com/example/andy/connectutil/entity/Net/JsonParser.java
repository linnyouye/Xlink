package com.example.andy.connectutil.entity.Net;

import android.widget.Switch;

import com.example.andy.connectutil.entity.Device.BathBully;
import com.example.andy.connectutil.entity.Device.Device;
import com.example.andy.connectutil.entity.Device.FanLinght;
import com.example.andy.connectutil.entity.Device.LEDLight;
import com.example.andy.connectutil.entity.Device.Light;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;

/**
 * Created by Lin Youye on 2016/4/11.
 */
public class JsonParser {

    private static final String EMPTY_STR = "";
    private static final String NULL_STR = "null";

    public static List<Device> parseDeviceList(String json){
        List<Device> list=new ArrayList<>();

        try {
            JSONArray devices = new JSONArray(json);
            for (int i = 0; i < devices.length(); i++) {
                JSONObject device = devices.getJSONObject(i);

                String mac = device.optString("mac");
                int deviceid = device.optInt("id");
                String pid = device.optString("product_id");


                JSONObject xDeviceJson = new JSONObject();
                xDeviceJson.put("protocol", 1);
                JSONObject deviceJson = new JSONObject();
                deviceJson.put("macAddress", mac);
                deviceJson.put("deviceID", deviceid);
                deviceJson.put("version", 2);
                deviceJson.put("mcuHardVersion", 0);
                deviceJson.put("mucSoftVersion", 0);
                deviceJson.put("productID", pid);
                deviceJson.put("accesskey", device.optInt("access_key"));
                xDeviceJson.put("device", deviceJson);

                XDevice xDevice = XlinkAgent.JsonToDevice(xDeviceJson);

                XlinkAgent.getInstance().initDevice(xDevice);
                Device addDevice = new Device();
                addDevice.setOnline(device.optBoolean(Key.IS_ONLINE));
                addDevice.setProduct_ID(pid);
                addDevice.setxDevice(xDevice);
                switch (pid)
                {
                    case Content.FanLIght_ID:
                        addDevice.setData(new FanLinght());
                        break;
                    case Content.Light_ID:
                        addDevice.setData(new Light());
                        break;
                    case Content.LEDLIght_ID:
                        addDevice.setData(new LEDLight());
                        break;
                    case Content.BathBully_ID:
                        addDevice.setData(new BathBully());
                }

                list.add(addDevice);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


}
