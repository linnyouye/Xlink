package com.example.andy.connectutil.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andy.connectutil.Activity.MainActivity;
import com.example.andy.connectutil.R;
import com.example.andy.connectutil.WiFiConfig;


/**
 * Created by 95815 on 2017/3/10.
 */

public class WifiConnectionFragment extends BaseFragment implements View.OnClickListener{


    public static final String fragment_tag ="WifiConnectionFragment";

    private static  final String TAG = "waiwen";

      private TextView wifi_name;
      private EditText et_wifi_password;
      private Button btn_next;
     LinearLayout linearLayout;
     private static String product_ID;

    public static WifiConnectionFragment newInstance(String str, String produt_id) {
        
        Bundle args = new Bundle();
        args.putString("设备类型",str);
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
        wifi_name = obtainView(view,R.id.tv_wifi_num);
        et_wifi_password = obtainView(view,R.id.et_wifi_password);
        btn_next = obtainView(view, R.id.btn_next);
        linearLayout = obtainView(view,R.id.view_new_guide);


    }



    @Override
    public void setListener() {
              btn_next.setOnClickListener(this);
        //用于新手引导层
        linearLayout.setOnClickListener(this);
    }

    @Override
    public void initData() {
        Bundle args = getArguments();
        String str = args.getString("设备类型"); //拿到传送过来的数据
        wifi_name.setText(str);
    }

    @Override
    public <T extends View> T obtainView(View view, int ReId) {
        return super.obtainView(view, ReId);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach:关联 ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    @Override
    public void onClick(View v) {
         switch(v.getId()){
             case R.id.btn_next:
                String password=et_wifi_password.getText().toString();
                 WiFiConfig wiFiConfig=new WiFiConfig(getActivity());
                 wiFiConfig.StartConfig(password);
                 holderListener.startCountdownFragment();
                 //获取FargmentHolder跳转到DeviceFargement
                 MainActivity mainActivity = (MainActivity)getActivity();
                 FragmentHolder holder = mainActivity.getHolder();
                 CountDownFragment deviceFragement = new CountDownFragment();
                 holder.replaceFragment(deviceFragement,DeviceFragement.TAG,true);
                 break;
             case R.id.view_new_guide:
                 //点击即消失
                 linearLayout.setVisibility(View.INVISIBLE);
                 break;
}
    }
   

}
