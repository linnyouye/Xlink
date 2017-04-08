package com.example.andy.connectutil.Fragment.DeviceFragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import com.example.andy.connectutil.Helper.FanLightHelper;
import com.example.andy.connectutil.R;
import com.example.andy.connectutil.entity.Device.Device;
import com.example.andy.connectutil.entity.Device.FanLinght;

import com.example.andy.connectutil.View.ControlView;
import io.xlink.wifi.sdk.XlinkCode;


/**
 * Created by 95815 on 2017/3/25.
 */

public class ContFanLedFragment extends Fragment {
    private boolean fanstate=false;
    private boolean lightstate=false;

    private byte lighting;
    private Device device;
    private boolean state = false;
    private  byte date=1;
    private ControlView controlView;
    private ControlView.OnControlListener listener;
    private PopupWindow popupWindow1,popupWindow2,popupWindow3,popupWindow4;
    private ImageButton imageButton1,imageButton2,imageButton3,imageButton4;
    private FanLightHelper fanLightHelper;


    private View popupLayout1,popupLayout2,popupLayout3,popupLayout4;
    private ImageButton imageButton;

    FanLinght fanLinght;

    public void setDevice(Device device) {
        this.device = device;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);

//          fanLightHelper=new FanLightHelper(device);
//          fanLinght=fanLightHelper.update();
         // initData();//初始化数据值

          View view = inflater.inflate(R.layout.fragment_view_fanled,container,false);
          controlView = (ControlView) view.findViewById(R.id.controlView);
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

          controlView.setOncontrolListener(new ControlView.OnControlListener() {
            @Override
            public void onClickCenter() {
                 fanLightHelper.setDataPoint(0, XlinkCode.DP_TYPE_BOOL,true);
            }

            @Override
            public void onClickBottom() {

            }

            @Override
            public void onClickOne() {
                fanLightHelper.setDataPoint(6, XlinkCode.DP_TYPE_BYTE,date);
            }

            @Override
            public void onClickTwo() {
                fanLightHelper.setDataPoint(6, XlinkCode.DP_TYPE_BYTE,date);
            }

            @Override
            public void onClickThree() {
                fanLightHelper.setDataPoint(6, XlinkCode.DP_TYPE_BYTE,date);
            }

            @Override
            public void onClickFour() {
                fanLightHelper.setDataPoint(6, XlinkCode.DP_TYPE_BYTE,date);
            }

            @Override
            public void onClickFive() {
                fanLightHelper.setDataPoint(6, XlinkCode.DP_TYPE_BYTE,5);
            }

            @Override
            public void onClickSix() {
                fanLightHelper.setDataPoint(6, XlinkCode.DP_TYPE_BYTE,6);
            }

            @Override
            public void onClickSeven() {
                fanLightHelper.setDataPoint(6, XlinkCode.DP_TYPE_BYTE,7);
            }

            @Override
            public void onClickEight() {
                fanLightHelper.setDataPoint(6, XlinkCode.DP_TYPE_BYTE,8);
            }

            @Override
            public void onClickNine() {
                fanLightHelper.setDataPoint(6, XlinkCode.DP_TYPE_BYTE,9);
            }

              @Override
              public void onClickTopLeft() {
                  fanLightHelper.setDataPoint(8, XlinkCode.DP_TYPE_BYTE,--lighting);
              }

              @Override
              public void onClickTopRight() {
                  fanLightHelper.setDataPoint(8, XlinkCode.DP_TYPE_BYTE,++lighting);
              }

              @Override
              public void onClickBottomLeft() {
                  if(fanstate)
                  {
                      fanLightHelper.setDataPoint(1, XlinkCode.DP_TYPE_BOOL,false);
                  }else
                  {
                      fanLightHelper.setDataPoint(1, XlinkCode.DP_TYPE_BOOL,true);
                  }

              }

              @Override
              public void onClickBottomRight() {
                  if(lightstate)
                  {
                      fanLightHelper.setDataPoint(2, XlinkCode.DP_TYPE_BOOL,false);
                  }else
                  {
                      fanLightHelper.setDataPoint(2, XlinkCode.DP_TYPE_BOOL,true);
                  }
              }
          });
         imageButton1 = (ImageButton)view.findViewById(R.id.ibtn_one_top);
      imageButton1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
           if(!state) {popupWindow1.setWidth(v.getMeasuredWidth());
              popupWindow1.showAsDropDown(v,0,15);
           state = true;}
              else {
               popupWindow1.dismiss();
               state = false;
           }
          }
      });
         imageButton2 = (ImageButton)view.findViewById(R.id.ibtn_two_top);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!state) {popupWindow2.setWidth(v.getMeasuredWidth());
                    popupWindow2.showAsDropDown(v,0,15);
                    state = true;}
                else {
                    popupWindow2.dismiss();
                    state = false;
                }
            }
        });
         imageButton3 = (ImageButton)view.findViewById(R.id.ibtn_three_top);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!state) {popupWindow3.setWidth(v.getMeasuredWidth());
                    popupWindow3.showAsDropDown(v,0,15);
                    state = true;}
                else {
                    popupWindow3.dismiss();
                    state = false;
                }
            }
        });
         imageButton4 = (ImageButton)view.findViewById(R.id.ibtn_four_top);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!state) {popupWindow4.setWidth(v.getMeasuredWidth());
                    popupWindow4.showAsDropDown(v,0,15);
                    state = true;}
                else {
                    popupWindow4.dismiss();
                    state = false;
                }
            }
        });

        setOnClike();
        return view;
    }


    private void initData() {

        fanstate=fanLinght.PowerOfFanc;
        lightstate=fanLinght.PowerOfLight;

        lighting=fanLinght.brightness;

        controlView.setArcState((int) fanLinght.FanPosition);


        if(fanLinght.FanDirection==0)
        {
            imageButton1.setImageResource(R.drawable.ibtn_1_cloud_2_orange);
        }else
        {
            imageButton1.setImageResource(R.drawable.ibtn_1_cloud_orange);
        }


        if(fanLinght.Model==0)
        {
            imageButton2.setImageResource(R.drawable.ibtn_2_natural_orange);
        }else
        {
            imageButton2.setImageResource(R.drawable.ibtn_2_fan_orange);
        }

        if(fanLinght.Coolor_Tem==0)
        {
            imageButton3.setImageResource(R.drawable.ibtn_3_light_two_orange);
        }else if(fanLinght.Coolor_Tem==10)
        {
            imageButton3.setImageResource(R.drawable.ibtn_3_light_one_orange);
        }else
        {
            imageButton3.setImageResource(R.drawable.ibtn_3_light_three_orange);
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



    public void setOnClike()
    {
        ImageButton imageButton1_one;
        ImageButton imageButton1_two;

        imageButton1_one=(ImageButton)popupLayout1.findViewById(R.id.ibtn_1_pop_one);
        imageButton1_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
                fanLightHelper.setDataPoint(3, XlinkCode.DP_TYPE_BYTE,(byte)0);
            }
        });

        imageButton1_two=(ImageButton)popupLayout1.findViewById(R.id.ibtn_1_pop_one);
        imageButton1_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
                fanLightHelper.setDataPoint(3, XlinkCode.DP_TYPE_BYTE,(byte)1);
            }
        });



        ImageButton imageButton2_one;
        ImageButton imageButton2_two;
        imageButton2_one=(ImageButton)popupLayout2.findViewById(R.id.ibt_2_pop_one);
        imageButton2_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
                fanLightHelper.setDataPoint(4, XlinkCode.DP_TYPE_BYTE,(byte)0);
            }
        });

        imageButton2_two=(ImageButton)popupLayout2.findViewById(R.id.ibt_2_pop_one);
        imageButton2_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
                fanLightHelper.setDataPoint(4, XlinkCode.DP_TYPE_BYTE,(byte)1);
            }
        });


        ImageButton imageButton3_one;
        ImageButton imageButton3_two;
        ImageButton imageButton3_three;
        imageButton3_one=(ImageButton)popupLayout3.findViewById(R.id.ibt_3_pop_one);
        imageButton3_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow3.dismiss();
                fanLightHelper.setDataPoint(7, XlinkCode.DP_TYPE_BYTE,(byte)0);
            }
        });

        imageButton3_two=(ImageButton)popupLayout3.findViewById(R.id.ibt_3_pop_two);
        imageButton3_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow3.dismiss();
                fanLightHelper.setDataPoint(7, XlinkCode.DP_TYPE_BYTE,(byte)1);
            }
        });
        imageButton3_three=(ImageButton)popupLayout3.findViewById(R.id.ibt_3_pop_three);
        imageButton3_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow3.dismiss();
                fanLightHelper.setDataPoint(7, XlinkCode.DP_TYPE_BYTE,(byte)2);
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
                fanLightHelper.setDataPoint(9, XlinkCode.DP_TYPE_BYTE,(byte)1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow4.dismiss();
                fanLightHelper.setDataPoint(9, XlinkCode.DP_TYPE_BYTE,(byte)2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow4.dismiss();
                fanLightHelper.setDataPoint(9, XlinkCode.DP_TYPE_BYTE,(byte)3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow4.dismiss();
                fanLightHelper.setDataPoint(9, XlinkCode.DP_TYPE_BYTE,(byte)4);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow4.dismiss();
                fanLightHelper.setDataPoint(9, XlinkCode.DP_TYPE_BYTE,(byte)5);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow4.dismiss();
                fanLightHelper.setDataPoint(9, XlinkCode.DP_TYPE_BYTE,(byte)6);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow4.dismiss();
                fanLightHelper.setDataPoint(9, XlinkCode.DP_TYPE_BYTE,(byte)7);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow4.dismiss();
                fanLightHelper.setDataPoint(9, XlinkCode.DP_TYPE_BYTE,(byte)8);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow4.dismiss();
                fanLightHelper.setDataPoint(9, XlinkCode.DP_TYPE_BYTE,(byte)9);
            }
        });
    }
}
