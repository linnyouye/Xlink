package com.example.andy.connectutil.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.andy.connectutil.entity.Device.BathBully;
import com.example.andy.connectutil.entity.Device.Device;
import com.example.andy.connectutil.entity.Device.LEDLight;

import java.util.ArrayList;
import java.util.List;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.XlinkCode;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.listener.SetDataPointListener;

/**
 * Created by andy on 2017/3/30.
 */

public class BathBullyActivity extends Activity {
    private Device device;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setDataPoint(final int index, int type, final Object value) {

        List<DataPoint> list = new ArrayList<>();
        DataPoint dp=new DataPoint(index,type);

        switch (type){
            case XlinkCode.DP_TYPE_BOOL:
                dp.setValueOfBool((boolean)value);
                break;
            case XlinkCode.DP_TYPE_BYTE:
                dp.setValueOfByte((byte)value);
                break;
            case XlinkCode.DP_TYPE_INT:
                dp.setValueOfInt((int)value);
                break;
            case XlinkCode.DP_TYPE_SHORT:
                dp.setValueOfShort((short)value);
                break;
        }

        list.add(dp);


        XlinkAgent.getInstance().setDataPoint(device.getxDevice(), list, new SetDataPointListener() {
            BathBully date=(BathBully) device.getData();
            @Override
            public void onSetDataPoint(XDevice xDevice, int i, int i1) {
                if(i==XlinkCode.SUCCEED){
                    switch (index){
                        case 0:
                            date.Power=(boolean)value;
                            break;
                        case 1:
                            date.Power_one=(boolean)value;
                            break;
                        case 2:
                            date.Power_Two=(boolean)value;
                            break;
                        case 3:
                            date.Power_Light=(boolean)value;
                            break;
                        case 4:
                            date.Power_Pump=(boolean)value;
                            break;
                        case 5:
                            date.delayed=(boolean)value;
                            break;
                    }
                }

            }
        });

    }
}
