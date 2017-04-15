package com.example.andy.connectutil.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.andy.connectutil.R;
import com.example.andy.connectutil.WiFiConfig;
import com.example.andy.connectutil.XlinkConnect;
import com.example.andy.connectutil.entity.Device.Device;
import com.example.andy.connectutil.entity.Net.Content;
import com.example.andy.connectutil.entity.Net.HttpUtils;
import com.example.andy.connectutil.entity.Net.JsonParser;
import com.example.andy.connectutil.entity.Net.LoginUtil;
import com.hiflying.smartlink.OnSmartLinkListener;
import com.hiflying.smartlink.SmartLinkedModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkCode;

/**
 * Created by 95815 .
 * Date:2017/3/31.
 * Writter: waiwen .
 * E-mail:iwaiwen@163.com .
 */

public class CountDownFragment extends BaseFragment {


    public static final String TAG = "CountDownFragment";
    private List<XDevice> devicelist;
    private List<Device> Exitslist;
    private List<String> MacList;


    private Handler mHandler;
    private Timer timer = new Timer();
    private TimerTask task;
    private ImageView img_gif_wifi;
    private TextView tv_countdown;

    private int recLen = 60;

    private String password;
    private OnSmartLinkListener listner = new OnSmartLinkListener() {
        @Override
        public void onLinked(SmartLinkedModule smartLinkedModule) {
            Toast.makeText(getActivity(), "连接成功", Toast.LENGTH_SHORT).show();
            getExitDevice();
        }

        @Override
        public void onCompleted() {

        }

        @Override
        public void onTimeOut() {
            notifyMainActivity();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        devicelist = new ArrayList<>();
        Exitslist = new ArrayList<>();
        MacList = new ArrayList<>();
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static CountDownFragment newInstance(String password) {

        Bundle args = new Bundle();
        args.putString("WiFi密码",password);
        CountDownFragment fragment = new CountDownFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_view_countdown;
    }

    @Override
    public void initView(View view) {
        img_gif_wifi = obtainView(view, R.id.img_countdown);
        tv_countdown = obtainView(view, R.id.tv_countdown);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initData() {
        password =getArguments().getString("WiFi密码");
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Glide.with(getActivity()).load(R.drawable.wifi).asGif().into(img_gif_wifi);
            }
        });
        startCountDown();
        timer.schedule(task, 1000, 1000);
    }

    @Override
    public String setState() {
        return this.TAG;
    }

    /**
     * 倒计时
     */
    public void startCountDown() {

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        if(recLen > 58){
                            configWiFi(password);
                        }  if (recLen > 0) {
                            tv_countdown.setText(String.valueOf(recLen) + "s");
                        } else if (recLen < 0) {
                            tv_countdown.setText("0s");
                            timer.cancel();
                            mActivity.getFragmentManger().popBackStackImmediate();
                        }
                }
            }
        };
        task = new TimerTask() {
            @Override
            public void run() {
                recLen--;
                Message message = new Message();
                message.what = 1;
                mHandler.sendMessage(message);
            }
        };
    }


    private void configWiFi(String password) {
        Toast.makeText(mActivity, "开始配网", Toast.LENGTH_SHORT).show();
        WiFiConfig wiFiConfig = new WiFiConfig(getActivity(), listner);
        if (password == null)
            password = "";
        wiFiConfig.StartConfig(password);
    }


    private void getExitDevice() {
        LoginUtil.getDevices(new HttpUtils.HttpUtilsListner() {
            @Override
            public void onSuccess(String content) {
                Toast.makeText(getActivity(), "获取设备成功", Toast.LENGTH_SHORT).show();
                Exitslist = JsonParser.parseDeviceList(content);
                for (Device d : Exitslist) {
                    MacList.add(d.getxDevice().getMacAddress());
                }
                scanDevice();
            }

            @Override
            public void onFailed(int code, String msg) {
                Toast.makeText(getActivity(), "获取设备失败", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailed: 获取设备列表失败");
            }
        });


    }

    public void scanDevice() {
        WiFiConfig w = new WiFiConfig(getActivity(), listner);
        w.ScanWifi(Content.FanLIght_ID, new WiFiConfig.OnBindDeviceListner() {
            @Override
            public void getDevice(XDevice device) {
                Toast.makeText(getActivity(), "扫描到设备", Toast.LENGTH_SHORT).show();
                devicelist.add(device);
                BindDevice();
            }

            @Override
            public void failed() {
                Toast.makeText(getActivity(), "扫描失败", Toast.LENGTH_SHORT).show();
                notifyMainActivity();
            }
        });
     /*   w.ScanWifi(Content.LEDLIght_ID, new WiFiConfig.OnBindDeviceListner() {
            @Override
            public void getDevice(XDevice device) {
                devicelist.add(device);
                BindDevice();
            }
        });
        w.ScanWifi(Content.Light_ID, new WiFiConfig.OnBindDeviceListner() {
            @Override
            public void getDevice(XDevice device) {
                devicelist.add(device);
                BindDevice();
            }
        });
        w.ScanWifi(Content.BathBully_ID, new WiFiConfig.OnBindDeviceListner() {
            @Override
            public void getDevice(XDevice device) {
                devicelist.add(device);
                BindDevice();
            }
        });*/

    }

    public void BindDevice() {
        XlinkConnect.init(getActivity());
        for (int i = 0; i < devicelist.size(); i++) {
            if (!MacList.contains(devicelist.get(i).getMacAddress())) {
                XlinkConnect.bindDevice(mActivity,devicelist.get(i), new XlinkConnect.BinderDeviceListner() {
                    @Override
                    public void bindDevice(XDevice device, int i) {
                        if (i == XlinkCode.SUCCEED) {
                            Log.d(TAG, "bindDevice:  绑定设备成功");
                            Toast.makeText(getActivity(), "绑定设备成功", Toast.LENGTH_SHORT).show();
                            notifyMainActivity();
                        } else {
                            Toast.makeText(getActivity(), "绑定设备失败"+i, Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "bindDevice: 绑定设备失败");
                            notifyMainActivity();
                        }

                    }
                });
            }
        }

    }


        public void firstget ()
        {
            LoginUtil.getDevices(new HttpUtils.HttpUtilsListner() {
                @Override
                public void onSuccess(String content) {
                    Exitslist = JsonParser.parseDeviceList(content);
                    for (Device d : Exitslist) {
                        MacList.add(d.getxDevice().getMacAddress());
                    }
                    firstScan();
                }

                @Override
                public void onFailed(int code, String msg) {
                    Log.d(TAG, "onFailed: 获取设备列表失败");
                }
            });
        }

    public void firstScan() {
        WiFiConfig w = new WiFiConfig(getActivity(), listner);
        w.ScanWifi(Content.FanLIght_ID, new WiFiConfig.OnBindDeviceListner() {
            @Override
            public void getDevice(XDevice device) {
                Toast.makeText(getActivity(), "扫描到设备", Toast.LENGTH_SHORT).show();
                devicelist.add(device);
                firstBindDivce();
            }

            @Override
            public void failed() {
                Toast.makeText(getActivity(), "扫描失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void firstBindDivce() {
        XlinkConnect.init(getActivity());
        for (int i = 0; i < devicelist.size(); i++) {
            if (!MacList.contains(devicelist.get(i).getMacAddress())) {
                XlinkConnect.bindDevice(mActivity,devicelist.get(i), new XlinkConnect.BinderDeviceListner() {
                    @Override
                    public void bindDevice(XDevice device, int i) {
                        if (i == XlinkCode.SUCCEED) {
                            Log.d(TAG, "bindDevice:  绑定设备成功");
                            Toast.makeText(getActivity(), "绑定设备成功\n请返回", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d(TAG, "bindDevice: 绑定设备失败");
                        }

                    }
                });
            }

        }
    }
    //最后通知MainActivity
    private void notifyMainActivity() {
       mActivity .getOnlinedevicelist();
        mActivity.notifyAdapter();
        mActivity.getHolder().removeAllFragment();
        //  h.replaceFragment(new EquitmentSelectFragment(),"EquitmentSelectFragment",true);
    }


}
