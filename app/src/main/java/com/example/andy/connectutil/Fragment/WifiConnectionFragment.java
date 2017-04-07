package com.example.andy.connectutil.Fragment;


import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andy.connectutil.Activity.MainActivity;
import com.example.andy.connectutil.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by 95815 on 2017/3/10.
 */

public class WifiConnectionFragment extends BaseFragment implements View.OnClickListener {


    public static final String fragment_tag = "WifiConnectionFragment";

    private static final String TAG = "waiwen";

    private boolean password_status = true;

    @Bind(R.id.tv_wifi_num)
    TextView wifi_name;
    @Bind(R.id.view_new_guide)
    LinearLayout linearLayout_firstguide;
    @Bind(R.id.btn_next)
    Button btn_next;
    @Bind(R.id.tv_first_guide)
    TextView tv_firstguide;
    @Bind(R.id.ibtn_password_see)
    ImageButton ibtn_password_see;
    @Bind(R.id.et_wifi_password)
    EditText et_wifi_password;


    private static String product_ID;

    public static WifiConnectionFragment newInstance(String wifiName, String produt_id) {

        Bundle args = new Bundle();
        args.putString("wifi名称", wifiName);
        WifiConnectionFragment fragment = new WifiConnectionFragment();
        fragment.setArguments(args);
        product_ID = produt_id;
        return fragment;
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_wifi_connection;
    }

    @Override
    public void initView(View view) {
        showLog("initview");
        ButterKnife.bind(this, view);

    }


    @Override
    public void initData() {
        Bundle args = getArguments();
        String str = args.getString("wifi名称"); //拿到传送过来的数据
        tv_firstguide.setText("wifi配对界面时输入WiFi密码然后用遥控激活配网：按一下遥控开关键再按一下灯开关键，正确配对后\n" +
                "出现‘滴’的一声。" + "\n" + "不带遥控激活方式：按墙壁开关键5次即配对成功。" + "\n" + "再按“next”键");
        wifi_name.setText(str);
        et_wifi_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    @Override
    protected String getState() {
        return Fragment_Tag_State;
    }

    @OnClick({R.id.btn_next, R.id.view_new_guide, R.id.ibtn_password_see})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next:
//               /* String password=et_wifi_password.getText().toString();
//                 WiFiConfig wiFiConfig=new WiFiConfig(getActivity());
//                 wiFiConfig.StartConfig(password);
//                  holderListener.startCountdownFragment();*/
                //获取FargmentHolder跳转到DeviceFargement
                MainActivity mainActivity = (MainActivity) getActivity();
              //  FragmentHolder holder = mainActivity.getHolder();
            //    DeviceFragement deviceFragement = DeviceFragement.newInstance(product_ID);
             //   holder.replaceFragment(deviceFragement, DeviceFragement.TAG);
                FragmentHoldertest holdertest = mainActivity.getHolder();
                DeviceFragement deviceFragement = DeviceFragement.newInstance(product_ID);
                holdertest.replaceFragment(deviceFragement,DeviceFragement.Fragment_Tag_State,true);

                break;
            case R.id.view_new_guide:
                //点击即消失
                linearLayout_firstguide.setVisibility(View.INVISIBLE);
                break;
            case R.id.ibtn_password_see:
                if (password_status) {
                    //密码隐藏
                    et_wifi_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    password_status = false;
                    ibtn_password_see.setImageResource(R.drawable.password_eye_white);
                } else {
                    //密码可见
                    et_wifi_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    password_status = true;
                    ibtn_password_see.setImageResource(R.drawable.password_eye_orange);
                }
                break;
        }
    }


}
