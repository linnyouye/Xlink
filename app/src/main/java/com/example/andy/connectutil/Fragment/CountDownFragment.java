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
import com.example.andy.connectutil.Activity.MainActivity;
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

    public static final String fragment_tag = "CountDownFragment";


    public static final String TAG = "ChoseScanedDevice";
    private List<XDevice> devicelist;
    private List<Device> Exitslist;
    private List<String> MacList;

    private int count = 0;
    private Handler mHandler;
    private Handler mHandler1;
    private Timer timer;
    private ImageView img_gif_wifi;
    private TextView tv_countdown;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        devicelist = new ArrayList<>();
        Exitslist = new ArrayList<>();
        MacList = new ArrayList<>();
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    public static CountDownFragment newInstance() {

        Bundle args = new Bundle();
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
        mHandler1 = new Handler();
        mHandler1.post(new Runnable() {
            @Override
            public void run() {
                Glide.with(getActivity()).load(R.drawable.wifi).asGif().into(img_gif_wifi);
            }
        });
        startCountDown();
    }


    @Override
    public void onResume() {
        super.onResume();
        /*scanDevice();
        getExitDevice();
        BindDevice();*/

        /*FragmentHolder fragmentHolder=m.getHolder();
        fragmentHolder.removeAllFragment();
        onDestroy();*/
    }


    /**
     * 倒计时
     */
    public void startCountDown() {

        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==58)
                {
                    scanDevice();
                    getExitDevice();
                    BindDevice();
                }
                if (msg.what > 0) {
                    tv_countdown.setText(msg.what + "s");
                } else {
                    if (msg.what == 0) {
                        tv_countdown.setText("0s");
                        timer.cancel();
                        holderListener.setMainPage("设备连接失败", View.VISIBLE);
                        mHandler1.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                           mActivity.getFragmentManger().popBackStackImmediate();
                            }

                        }, 3000);
                    }
                }
            }

        };


        timer = new Timer(true);
        TimerTask tt = new TimerTask() {
            int countTime = 60;

            @Override
            public void run() {
                if (countTime > 0) {
                    countTime--;
                }
                Message msg = new Message();
                msg.what = countTime;
                mHandler.sendMessage(msg);

            }
        };
        timer.schedule(tt, 1000, 1000);
    }

    private void getExitDevice() {
        LoginUtil.getDevices(new HttpUtils.HttpUtilsListner() {
            @Override
            public void onSuccess(String content) {
                Exitslist = JsonParser.parseDeviceList(content);
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.d(TAG, "onFailed: 获取设备列表失败");
            }
        });
        for (Device d : Exitslist) {
            MacList.add(d.getxDevice().getMacAddress());
        }
    }

    public void scanDevice() {
        WiFiConfig w = new WiFiConfig(getActivity(), new OnSmartLinkListener() {
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
        w.ScanWifi(Content.FanLIght_ID, new WiFiConfig.OnBindDeviceListner() {
            @Override
            public void getDevice(XDevice device) {
                Toast.makeText(getActivity(), "you yige ", Toast.LENGTH_SHORT).show();
                devicelist.add(device);
            }
        });
        w.ScanWifi(Content.LEDLIght_ID, new WiFiConfig.OnBindDeviceListner() {
            @Override
            public void getDevice(XDevice device) {
                devicelist.add(device);
            }
        });
        w.ScanWifi(Content.Light_ID, new WiFiConfig.OnBindDeviceListner() {
            @Override
            public void getDevice(XDevice device) {
                devicelist.add(device);
            }
        });
        w.ScanWifi(Content.BathBully_ID, new WiFiConfig.OnBindDeviceListner() {
            @Override
            public void getDevice(XDevice device) {
                devicelist.add(device);
            }
        });
    }

    public void BindDevice() {
        XlinkConnect.init(getActivity());
        for (int i = 0; i < devicelist.size(); i++) {
            if (!MacList.contains(devicelist.get(i).getMacAddress())) {
                XlinkConnect.bindDevice(devicelist.get(i), new XlinkConnect.BinderDeviceListner() {
                    @Override
                    public void bindDevice(XDevice device, int i) {
                        if (i == XlinkCode.SUCCEED) {
                            Log.d(TAG, "bindDevice:  绑定设备成功");
                            ontifyMainActivity();
                        } else {
                            Log.d(TAG, "bindDevice: 绑定设备失败");
                        }

                    }
                });
            }

        }
        ontifyMainActivity();
    }
    //最后通知MainActivity
    private void ontifyMainActivity() {

        Toast.makeText(getActivity(),"绑定设备成功\n请返回",Toast.LENGTH_SHORT).show();
        MainActivity m = (MainActivity) getActivity();
        m.getOnlinedevicelist();
        m.notifyAdapter();
        mActivity.getHolder().removeAllFragment();
      //  h.replaceFragment(new EquitmentSelectFragment(),"EquitmentSelectFragment",true);
    }

}
