package com.example.andy.connectutil.Fragment.DeviceFragment;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.connectutil.Activity.MainActivity;
import com.example.andy.connectutil.Activity.MyApplication;
import com.example.andy.connectutil.Fragment.HolderListener;
import com.example.andy.connectutil.Helper.FanLightHelper;
import com.example.andy.connectutil.R;
import com.example.andy.connectutil.View.ControFanLedlView;
import com.example.andy.connectutil.View.ControlView;
import com.example.andy.connectutil.entity.Device.Device;
import com.example.andy.connectutil.entity.Device.FanLinght;
import com.example.andy.connectutil.entity.Device.ToastUtil;

import java.nio.MappedByteBuffer;
import java.util.List;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.XlinkCode;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.XlinkNetListener;

/**
 * Created by andy on 2017/4/11.
 */

public class FanLightFragment extends Fragment implements View.OnTouchListener{

    private Handler handler;
    public static final String TAG = "FanLightFragment";
    private HolderListener holderListener;
    private boolean fanstate=false;
    private boolean lightstate=false;
    private boolean powerstate=false;

    private boolean imagebtn1state=false;
    private boolean imagebtn2state=false;
    private boolean imagebtn3state=false;
    private boolean imagebtn4state=false;

    private byte lighting;
    private Device device;
    private boolean state = false;
    private  byte date=1;
    private ControFanLedlView controlView;
    private ControlView.OnControlListener listener;
    private PopupWindow popupWindow1,popupWindow2,popupWindow3,popupWindow4;
    private ImageButton imageButton1,imageButton2,imageButton3,imageButton4;
    private FanLightHelper fanLightHelper;
    private ImageButton imageButtonToplef, imageButtonTopRight,imageButtonButtomLeft,imageButtonButtomRight;

    private TextView brightnss;


    private View popupLayout1,popupLayout2,popupLayout3,popupLayout4;
    private ImageButton imageButton;

    FanLinght fanLinght=new FanLinght();

    public void setDevice(Device device) {
        this.device = device;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


       /* testdata();*/

        View view = inflater.inflate(R.layout.fragment_view_controlfanled,container,false);
        view.setOnTouchListener(this);
        controlView = (ControFanLedlView)view.findViewById(R.id.controlfanledview);
        controlView.setGeerNum(4);
        controlView.setBottomAngle(50f);
        popupLayout1 = LayoutInflater.from(getContext()).inflate(R.layout.popview_itbn_one, null);
        popupWindow1 = getPopupWindow(popupLayout1);

        popupLayout2 = LayoutInflater.from(getContext()).inflate(R.layout.popview_itbn_two, null);
        popupWindow2 = getPopupWindow(popupLayout2);

        popupLayout3=LayoutInflater.from(getContext()).inflate(R.layout.popview_itbn_three, null);
        popupWindow3 = getPopupWindow(popupLayout3);

        popupLayout4=LayoutInflater.from(getContext()).inflate(R.layout.popview_itbn_four, null);
        popupWindow4 =getPopupWindow(popupLayout4);


        imageButton1 = (ImageButton)view.findViewById(R.id.ibtn_one_top);

        imageButton2 = (ImageButton)view.findViewById(R.id.ibtn_two_top);

        imageButton3 = (ImageButton)view.findViewById(R.id.ibtn_three_top);

        imageButton4 = (ImageButton)view.findViewById(R.id.ibtn_four_top);

        imageButtonToplef=(ImageButton)view.findViewById(R.id.fanled_ibtn_tl);
        imageButtonTopRight=(ImageButton)view.findViewById(R.id.fanled_ibtn_tr);
        imageButtonButtomLeft=(ImageButton)view.findViewById(R.id.fanled_ibtn_bl);
        imageButtonButtomRight=(ImageButton)view.findViewById(R.id.fanled_ibtn_br);

        brightnss=(TextView)view.findViewById(R.id.fanled_tv_topshow);

        controlView.setArcState(-1);
        brightnss.setTextColor(this.getResources().getColor(R.color.linear_item_bg_normal));
        //处理加减的长按和点击事件
        handler = new Handler() {
            public void handleMessage (Message msg){
                switch (msg.what) {
                    case 0x01:
                        if(lightstate)
                        {
                            if(fanLinght.getBrightness()>=100)
                            {
                                ToastUtil.showToast("已经是最大亮度");
                            }else
                            {
                                lighting+=5;
                                if(lighting>100)
                                    lighting=100;
                                fanLinght.setBrightness((byte) lighting);
                                fanLightHelper.setDataPoint(8, XlinkCode.DP_TYPE_BYTE, lighting);
                                brightnss.setText(lighting+"");
                            }

                        }else
                        {
                            ToastUtil.showToast("请打开灯开关");
                        }

                        break;
                    case 0x00:
                        if(lightstate)
                        {
                            if(fanLinght.getBrightness()>=100)
                            {
                                ToastUtil.showToast("已经是最大亮度");
                            }else {
                                fanLightHelper.setDataPoint(8, XlinkCode.DP_TYPE_BYTE, ++lighting);
                                brightnss.setText(lighting + "");
                                fanLinght.setBrightness(lighting);
                            }
                        }else
                        {
                            ToastUtil.showToast("请打开灯开关");
                        }
                    break;
                    case 0x10:
                        if(lightstate)
                        {
                            if(fanLinght.getBrightness()<=0)
                            {
                                ToastUtil.showToast("已经是最小亮度");
                            }else
                            {
                                lighting-=5;
                                if(lighting<0)
                                    lighting=0;
                                fanLinght.setBrightness((byte) lighting);
                                fanLightHelper.setDataPoint(8, XlinkCode.DP_TYPE_BYTE, lighting);
                                brightnss.setText(lighting+"");
                            }

                        }else
                        {
                            ToastUtil.showToast("请打开灯开关");
                        }
                        break;
                    case 0x11:
                        if(lightstate)
                        {
                            if(fanLinght.getBrightness()<=0)
                            {
                                ToastUtil.showToast("已经是最小亮度");
                            }else
                            {
                                fanLightHelper.setDataPoint(8, XlinkCode.DP_TYPE_BYTE,--lighting);
                                brightnss.setText(lighting+"");
                                fanLinght.setBrightness(lighting);
                            }

                        }else
                        {
                            ToastUtil.showToast("请打开灯开关");
                        }
                    default:
                        break;
                }
            }
        };


            if(device.isOnline())
            {
                   fanLightHelper=new FanLightHelper(device);

                   updata();

                   setOnClike();
            }else
            {
                ToastUtil.showToast("设备不在线");
            }
      /*  fanLightHelper=new FanLightHelper(device);
        initData();
        setOnClike();*/

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        holderListener.setFraagment_State(FanLightFragment.TAG);
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            holderListener =(HolderListener) activity;
        }catch (Exception e){


        }

    }

    private void updata() {
        XlinkAgent.getInstance().addXlinkListener(new XlinkNetListener() {
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
                Log.d("FanLight", "onDataPointUpdate: "+list.toString());
                FanLinght f=new FanLinght();
                fanLinght=(FanLinght) f.parseFromDataPoints(list);
                initData();//初始化数据值
            }

            @Override
            public void onEventNotify(EventNotify eventNotify) {

            }
        });
    }


    private void initData() {

        fanstate=fanLinght.PowerOfFanc;
        lightstate=fanLinght.PowerOfLight;
        powerstate=fanLinght.PowerOfFanc;
        controlView.setPowerState(powerstate);

        lighting=fanLinght.brightness;
       // controlView.setLightNum(lighting);
        brightnss.setText(lighting+"");
        controlView.setGeerNum(fanLinght.FanModel+1);
        if(fanLinght.FanPosition<1)
        {
            controlView.setArcState((int)-1);
        }else
        {
            controlView.setArcState((int) fanLinght.FanPosition);
        }

        if(fanstate)
        {
            imageButtonButtomLeft.setImageResource(R.drawable.icon_fanled_bl_orange);
        }else
        {
            imageButtonButtomLeft.setImageResource(R.drawable.icon_fanled_bl_white);
        }

        if(lightstate)
        {
            imageButtonButtomRight.setImageResource(R.drawable.icon_fanled_br_orange);
            brightnss.setTextColor(this.getResources().getColor(R.color.colorOrange));
        }else
        {
            imageButtonButtomRight.setImageResource(R.drawable.icon_fanled_br_white);
            brightnss.setTextColor(this.getResources().getColor(R.color.linear_item_bg_normal));
        }


        if(fanLinght.FanDirection==0)
        {
            if(fanstate)
            imageButton1.setImageResource(R.drawable.ibtn_1_cloud_2_orange);
            else
                imageButton1.setImageResource(R.drawable.ibtn_1_cloud_2_white);
        }else
        {
            if(fanstate)
            imageButton1.setImageResource(R.drawable.ibtn_1_cloud_orange);
            else
                imageButton1.setImageResource(R.drawable.ibtn_1_cloud_white);
        }


        if(fanLinght.Model==0)
        {
            if(fanstate)
            imageButton2.setImageResource(R.drawable.ibtn_2_natural_orange);
            else
                imageButton2.setImageResource(R.drawable.ibtn_2_natural_white);
        }else
        {
            if (fanstate)
            imageButton2.setImageResource(R.drawable.ibtn_2_fan_orange);
            else
                imageButton2.setImageResource(R.drawable.ibtn_2_fan_white);
        }

        if(fanLinght.Coolor_Tem==0)
        {
            if(lightstate)
            imageButton3.setImageResource(R.drawable.ibtn_3_light_two_orange);
            else
                imageButton3.setImageResource(R.drawable.ibtn_3_light_two_white);
        }else if(fanLinght.Coolor_Tem==10)
        {
            if (lightstate)
            imageButton3.setImageResource(R.drawable.ibtn_3_light_one_orange);
            else
                imageButton3.setImageResource(R.drawable.ibtn_3_light_one_white);
        }else
        {
            if (lightstate)
            imageButton3.setImageResource(R.drawable.ibtn_3_light_three_orange);
            else
                imageButton3.setImageResource(R.drawable.ibtn_3_light_three_white);
        }

    }

    public PopupWindow getPopupWindow(View v) {
        PopupWindow popupWindow = new PopupWindow(getContext());
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setContentView(v);
        return popupWindow;
    }


    @Override
    public void onStop() {
        super.onStop();
        holderListener.setFraagment_State(MainActivity.TAG);
    }

    public void setOnClike()
    {
        //主界面的点击事件
        controlView.setOncontrolListener(new ControFanLedlView.OnControlListener() {
            @Override
            public void onClickCenter() {
                if(powerstate)
                {
                    fanLightHelper.setDataPoint(0, XlinkCode.DP_TYPE_BOOL,false);
                    controlView.setPowerState(false);
                    powerstate=false;
                    fanLinght.setPower(false);
                }else
                {
                    fanLightHelper.setDataPoint(0, XlinkCode.DP_TYPE_BOOL,true);
                    controlView.setPowerState(true);
                    powerstate=true;
                    fanLinght.setPower(true);
                }

            }

            @Override
            public void onClickBottom() {

            }

            @Override
            public void onClickOne() {
                if(fanLinght.Power)
                {
                    fanLightHelper.setDataPoint(6, XlinkCode.DP_TYPE_BYTE,(byte)1);
                    controlView.setArcState(1);
                    fanLinght.setFanPosition((byte)1);
                }else
                {
                    ToastUtil.showToast("请打开电源");
                }

            }

            @Override
            public void onClickTwo() {
                if(fanLinght.Power)
                {
                    fanLightHelper.setDataPoint(6, XlinkCode.DP_TYPE_BYTE,(byte)2);
                    controlView.setArcState(2);
                    fanLinght.setFanPosition((byte)2);
                }else
                {
                    ToastUtil.showToast("请打开电源");
                }


            }

            @Override
            public void onClickThree() {
                if(fanLinght.Power)
                {
                    fanLightHelper.setDataPoint(6, XlinkCode.DP_TYPE_BYTE,(byte)3);
                    controlView.setArcState(3);
                    fanLinght.setFanPosition((byte)3);
                }else
                {
                    ToastUtil.showToast("请打开电源");
                }

            }

            @Override
            public void onClickFour() {
                if(fanLinght.Power)
                {
                    fanLightHelper.setDataPoint(6, XlinkCode.DP_TYPE_BYTE,(byte)4);
                    controlView.setArcState(4);
                    fanLinght.setFanPosition((byte)4);
                }else
                {
                    ToastUtil.showToast("请打开电源");
                }

            }

            @Override
            public void onClickFive() {
                if(fanLinght.Power)
                {
                    fanLightHelper.setDataPoint(6, XlinkCode.DP_TYPE_BYTE,(byte)5);
                    controlView.setArcState(5);
                    fanLinght.setFanPosition((byte)5);
                }else
                {
                    ToastUtil.showToast("请打开电源");
                }

            }

            @Override
            public void onClickSix() {
                if(fanLinght.Power)
                {
                    fanLightHelper.setDataPoint(6, XlinkCode.DP_TYPE_BYTE,(byte)6);
                    controlView.setArcState(6);
                    fanLinght.setFanPosition((byte)6);
                }else
                {
                    ToastUtil.showToast("请打开电源");
                }

            }

            @Override
            public void onClickSeven() {
                if(fanLinght.Power)
                {
                    fanLightHelper.setDataPoint(6, XlinkCode.DP_TYPE_BYTE,(byte)7);
                    controlView.setArcState(7);
                    fanLinght.setFanPosition((byte)7);
                }else
                {
                    ToastUtil.showToast("请打开电源");
                }

            }

            @Override
            public void onClickEight() {
                if(fanLinght.Power)
                {
                    fanLightHelper.setDataPoint(6, XlinkCode.DP_TYPE_BYTE,(byte)8);
                    controlView.setArcState(8);
                    fanLinght.setFanPosition((byte)8);
                }else
                {
                    ToastUtil.showToast("请打开电源");
                }

            }

            @Override
            public void onClickNine() {
                if(fanLinght.Power)
                {
                    fanLightHelper.setDataPoint(6, XlinkCode.DP_TYPE_BYTE,(byte)9);
                    controlView.setArcState(9);
                    fanLinght.setFanPosition((byte)9);
                }else
                {
                    ToastUtil.showToast("请打开电源");
                }

            }});


        imageButtonToplef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lightstate)
                {
                    if(fanLinght.getBrightness()==0)
                    {
                        ToastUtil.showToast("已经是最小亮度");
                    }else
                    {
                        fanLightHelper.setDataPoint(8, XlinkCode.DP_TYPE_BYTE,--lighting);
                        brightnss.setText(lighting+"");
                        fanLinght.setBrightness(lighting);
                    }

                }else
                {
                    ToastUtil.showToast("请打开灯开关");
                }
            }
        });

        imageButtonToplef.setOnTouchListener(new View.OnTouchListener() {
            boolean OnClick;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final long time=System.currentTimeMillis();
                if( event.getAction() == MotionEvent.ACTION_DOWN ){
                    OnClick = true;
                    final Thread t = new Thread(){

                        public void run() {
                            while (OnClick){
                                long now=System.currentTimeMillis();
                                if(now-time>399)
                                {
                                    handler.sendEmptyMessage(0x11);
                                }else
                                {
                                    handler.sendEmptyMessage(0x10);
                                }
                                try {
                                    Thread.sleep(300);
                                }catch(InterruptedException e){
                                    e.printStackTrace();
                                }

                                //新开启的线程中不能修改UI界面，通过handler与UI线程交互
                            }
                        }
                    };
                    t.start();

                }else if( event.getAction() == MotionEvent.ACTION_UP ){
                    OnClick = false;
                }
                return false;
            }
        });

        imageButtonTopRight.setOnTouchListener(new View.OnTouchListener() {
            boolean OnClick;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final long time=System.currentTimeMillis();
                if( event.getAction() == MotionEvent.ACTION_DOWN ){
                    OnClick = true;
                    final Thread t = new Thread(){

                        public void run() {
                            while (OnClick){
                                long now=System.currentTimeMillis();
                                if(now-time>399)
                                {
                                    handler.sendEmptyMessage(0x01);
                                }else
                                {
                                    handler.sendEmptyMessage(0x00);
                                }
                                try {
                                    Thread.sleep(300);
                                }catch(InterruptedException e){
                                    e.printStackTrace();
                                }

                                //新开启的线程中不能修改UI界面，通过handler与UI线程交互
                            }
                        }
                    };
                    t.start();

                }else if( event.getAction() == MotionEvent.ACTION_UP ){
                    OnClick = false;
                }
                return false;
            }

        });

       /* imageButtonTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lightstate)
                {
                    if(fanLinght.getBrightness()==100)
                    {
                        ToastUtil.showToast("已经是最大亮度");
                    }else {
                        fanLightHelper.setDataPoint(8, XlinkCode.DP_TYPE_BYTE, ++lighting);
                        brightnss.setText(lighting + "");
                        fanLinght.setBrightness(lighting);
                    }
                }else
                {
                    ToastUtil.showToast("请打开灯开关");
                }
            }
        });*/

        imageButtonButtomLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fanstate)
                {
                    fanLightHelper.setDataPoint(1, XlinkCode.DP_TYPE_BOOL,false);
                    fanstate=false;
                    fanLinght.setPowerOfFanc(false);
                    imageButtonButtomLeft.setImageResource(R.drawable.icon_fanled_bl_white);



                    if(fanLinght.FanDirection==0)
                    imageButton1.setImageResource(R.drawable.ibtn_1_cloud_2_white);
                    else
                        imageButton1.setImageResource(R.drawable.ibtn_1_cloud_white);
                    if(fanLinght.FanModel==0)
                    imageButton2.setImageResource(R.drawable.ibtn_2_natural_white);
                    else
                        imageButton2.setImageResource(R.drawable.ibtn_2_fan_white);


                }else
                {
                    fanLightHelper.setDataPoint(1, XlinkCode.DP_TYPE_BOOL,true);
                    fanstate=true;
                    fanLinght.setPowerOfFanc(true);
                    imageButtonButtomLeft.setImageResource(R.drawable.icon_fanled_bl_orange);


                    if(fanLinght.FanDirection==0)
                        imageButton1.setImageResource(R.drawable.ibtn_1_cloud_2_orange);
                    else
                        imageButton1.setImageResource(R.drawable.ibtn_1_cloud_orange);
                    if(fanLinght.FanModel==0)
                        imageButton2.setImageResource(R.drawable.ibtn_2_natural_orange);
                    else
                        imageButton2.setImageResource(R.drawable.ibtn_2_fan_orange);
                }
            }
        });

        imageButtonButtomRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lightstate)
                {
                    brightnss.setTextColor(getActivity().getResources().getColor(R.color.linear_item_bg_normal));

                    fanLightHelper.setDataPoint(2, XlinkCode.DP_TYPE_BOOL,false);
                    lightstate=false;
                    fanLinght.setPowerOfLight(false);
                    imageButtonButtomRight.setImageResource(R.drawable.icon_fanled_br_white);

                    if(fanLinght.Coolor_Tem==0)
                    imageButton3.setImageResource(R.drawable.ibtn_3_light_one_white);
                    else if(fanLinght.Coolor_Tem==1)
                        imageButton3.setImageResource(R.drawable.ibtn_3_light_two_white);
                    else
                        imageButton3.setImageResource(R.drawable.ibtn_3_light_three_white);
                }else
                {
                    brightnss.setTextColor(getActivity().getResources().getColor(R.color.colorOrange));
                    fanLightHelper.setDataPoint(2, XlinkCode.DP_TYPE_BOOL,true);
                    lightstate=true;
                    fanLinght.setPowerOfLight(true);
                    imageButtonButtomRight.setImageResource(R.drawable.icon_fanled_br_orange);

                    if(fanLinght.Coolor_Tem==0)
                        imageButton3.setImageResource(R.drawable.ibtn_3_light_one_orange);
                    else if(fanLinght.Coolor_Tem==1)
                        imageButton3.setImageResource(R.drawable.ibtn_3_light_two_orange);
                    else
                        imageButton3.setImageResource(R.drawable.ibtn_3_light_three_orange);
                }
            }
        });
        //上部第一个按钮的点击事件
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!imagebtn1state) {popupWindow1.setWidth(v.getMeasuredWidth());
                    popupWindow1.showAsDropDown(v,0,15);
                    imagebtn1state = true;}
                else {
                    popupWindow1.dismiss();
                    imagebtn1state = false;
                }
            }
        });
        //上部第二个按钮的点击事件
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!imagebtn2state) {popupWindow2.setWidth(v.getMeasuredWidth());
                    popupWindow2.showAsDropDown(v,0,15);
                    imagebtn2state = true;}
                else {
                    popupWindow2.dismiss();
                    imagebtn2state = false;
                }
            }
        });
        //上部第三个按钮的点击事件
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!imagebtn3state) {popupWindow3.setWidth(v.getMeasuredWidth());
                    popupWindow3.showAsDropDown(v,0,15);
                    imagebtn3state = true;}
                else {
                    popupWindow3.dismiss();
                    imagebtn3state = false;
                }
            }
        });
        //上部第四个按钮的点击事件
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!imagebtn4state) {popupWindow4.setWidth(v.getMeasuredWidth());
                    popupWindow4.showAsDropDown(v,0,15);
                    imagebtn4state = true;}
                else {
                    popupWindow4.dismiss();
                    imagebtn4state = false;
                }
            }
        });



        final ImageButton imageButton1_one;
        final ImageButton imageButton1_two;

        imageButton1_one=(ImageButton)popupLayout1.findViewById(R.id.ibtn_1_pop_one);
        imageButton1_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fanstate)
                {
                    fanLightHelper.setDataPoint(3, XlinkCode.DP_TYPE_BYTE,(byte)0);
                    popupWindow1.dismiss();
                    imagebtn1state=false;
                    imageButton1.setImageResource(R.drawable.ibtn_1_cloud_2_orange);
                    fanLinght.setFanDirection((byte)0);
                }else
                {
                    ToastUtil.showToast("请打开风扇开关");
                }

            }
        });

        imageButton1_two=(ImageButton)popupLayout1.findViewById(R.id.ibt_1_pop_two);
        imageButton1_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fanstate)
                {
                    fanLightHelper.setDataPoint(3, XlinkCode.DP_TYPE_BYTE,(byte)1);
                    popupWindow1.dismiss();
                    imagebtn1state=false;
                    imageButton1.setImageResource(R.drawable.ibtn_1_cloud_orange);
                    fanLinght.setFanDirection((byte)1);
                }else
                {
                    ToastUtil.showToast("请打开风扇开关");
                }

            }
        });



        ImageButton imageButton2_one;
        ImageButton imageButton2_two;
        imageButton2_one=(ImageButton)popupLayout2.findViewById(R.id.ibt_2_pop_one);
        imageButton2_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fanstate)
                {
                    imagebtn2state=false;
                    fanLightHelper.setDataPoint(4, XlinkCode.DP_TYPE_BYTE,(byte)0);
                    popupWindow2.dismiss();
                    imageButton2.setImageResource(R.drawable.ibtn_2_natural_orange);
                    fanLinght.setFanModel((byte)0);
                }else
                {
                    ToastUtil.showToast("请打开风扇开关");
                }

            }
        });

        imageButton2_two=(ImageButton)popupLayout2.findViewById(R.id.ibt_2_pop_two);
        imageButton2_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fanstate)
                {
                    imagebtn2state=false;
                    fanLightHelper.setDataPoint(4, XlinkCode.DP_TYPE_BYTE,(byte)1);
                    popupWindow2.dismiss();
                    imageButton2.setImageResource(R.drawable.ibtn_2_fan_orange);
                    fanLinght.setFanModel((byte)1);
                }else
                {
                    ToastUtil.showToast("请打开风扇开关");
                }

            }
        });


        ImageButton imageButton3_one;
        ImageButton imageButton3_two;
        ImageButton imageButton3_three;
        imageButton3_one=(ImageButton)popupLayout3.findViewById(R.id.ibt_3_pop_one);
        imageButton3_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lightstate)
                {
                    imagebtn3state=false;
                    fanLightHelper.setDataPoint(7, XlinkCode.DP_TYPE_BYTE,(byte)0);
                    popupWindow3.dismiss();
                    imageButton3.setImageResource(R.drawable.ibtn_3_light_one_orange);
                    fanLinght.setCoolor_Tem((byte)0);
                }else
                {
                    ToastUtil.showToast("请打开开关");
                }


            }
        });

        imageButton3_two=(ImageButton)popupLayout3.findViewById(R.id.ibt_3_pop_two);
        imageButton3_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lightstate)
                {
                    imagebtn3state=false;
                    fanLightHelper.setDataPoint(7, XlinkCode.DP_TYPE_BYTE,(byte)1);
                    popupWindow3.dismiss();
                    imageButton3.setImageResource(R.drawable.ibtn_3_light_two_orange);
                    fanLinght.setCoolor_Tem((byte)1);
                }else
                {
                    ToastUtil.showToast("请打开开关");
                }

            }
        });
        imageButton3_three=(ImageButton)popupLayout3.findViewById(R.id.ibt_3_pop_three);
        imageButton3_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lightstate)
                {
                    popupWindow3.dismiss();
                    imagebtn3state=false;
                    fanLightHelper.setDataPoint(7, XlinkCode.DP_TYPE_BYTE,(byte)2);
                    imageButton3.setImageResource(R.drawable.ibtn_3_light_three_orange);
                    fanLinght.setCoolor_Tem((byte)2);
                }else
                {
                    ToastUtil.showToast("请打开开关");
                }

            }
        });


        Button btn1;
        Button btn2;
        Button btn3;
        Button btn4;
        Button btn5;
        Button btn6;
        Button btn7;
        Button btn8;
        Button btn9;

        btn1=(Button)popupLayout4.findViewById(R.id.ibt_4_pop_one);
        btn2=(Button)popupLayout4.findViewById(R.id.ibt_4_pop_two);
        btn3=(Button)popupLayout4.findViewById(R.id.ibt_4_pop_three);
        btn4=(Button)popupLayout4.findViewById(R.id.ibt_4_pop_four);
        btn5=(Button)popupLayout4.findViewById(R.id.ibt_4_pop_five);
        btn6=(Button)popupLayout4.findViewById(R.id.ibt_4_pop_six);
        btn7=(Button)popupLayout4.findViewById(R.id.ibt_4_pop_seven);
        btn8=(Button)popupLayout4.findViewById(R.id.ibt_4_pop_eighte);
        btn9=(Button)popupLayout4.findViewById(R.id.ibt_4_pop_nine);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow4.dismiss();
                imagebtn4state=false;
                fanLightHelper.setDataPoint(9, XlinkCode.DP_TYPE_BYTE,(byte)1);
                fanLinght.setTiming((byte)1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow4.dismiss();
                imagebtn4state=false;
                fanLightHelper.setDataPoint(9, XlinkCode.DP_TYPE_BYTE,(byte)2);
                fanLinght.setTiming((byte)2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow4.dismiss();
                imagebtn4state=false;
                fanLightHelper.setDataPoint(9, XlinkCode.DP_TYPE_BYTE,(byte)3);
                fanLinght.setTiming((byte)3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow4.dismiss();
                imagebtn4state=false;
                fanLightHelper.setDataPoint(9, XlinkCode.DP_TYPE_BYTE,(byte)4);
                fanLinght.setTiming((byte)4);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow4.dismiss();
                imagebtn4state=false;
                fanLightHelper.setDataPoint(9, XlinkCode.DP_TYPE_BYTE,(byte)5);
                fanLinght.setTiming((byte)5);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow4.dismiss();
                imagebtn4state=false;
                fanLightHelper.setDataPoint(9, XlinkCode.DP_TYPE_BYTE,(byte)6);
                fanLinght.setTiming((byte)6);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow4.dismiss();
                imagebtn4state=false;
                fanLightHelper.setDataPoint(9, XlinkCode.DP_TYPE_BYTE,(byte)7);
                fanLinght.setTiming((byte)7);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow4.dismiss();
                imagebtn4state=false;
                fanLightHelper.setDataPoint(9, XlinkCode.DP_TYPE_BYTE,(byte)8);
                fanLinght.setTiming((byte)8);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow4.dismiss();
                imagebtn4state=false;
                fanLightHelper.setDataPoint(9, XlinkCode.DP_TYPE_BYTE,(byte)9);
                fanLinght.setTiming((byte)9);
            }
        });
    }
    private void testdata() {
      /*  public boolean Power;//总开关
        public boolean PowerOfFanc;
        public boolean PowerOfLight;
        public byte FanDirection;//风扇方向
        public byte Model;//模式
        public byte FanModel;//风扇档位模式
        public byte FanPosition;//风扇档位
        public byte Coolor_Tem;//色温
        public byte brightness;//亮度
        public byte Timing;*/
        fanLinght.setPower(true);
        fanLinght.setPowerOfFanc(true);
        fanLinght.setPowerOfLight(true);
        fanLinght.setFanDirection((byte)0);
        fanLinght.setModel((byte) 0);
        fanLinght.setFanModel((byte)9);
        fanLinght.setFanPosition((byte)1);
        fanLinght.setCoolor_Tem((byte)1);
        fanLinght.setBrightness((byte)50);
        fanLinght.setTiming((byte)9);
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(!device.isOnline())
        {
            ToastUtil.showToast("设备不在线");
        }
        return true;
    }

    @Override
    public void onDestroy() {
        if(fanLinght!=null)
            device.setData(fanLinght);
        super.onDestroy();
    }

    }
