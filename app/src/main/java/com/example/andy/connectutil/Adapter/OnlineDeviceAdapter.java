package com.example.andy.connectutil.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.andy.connectutil.Activity.MainActivity;
import com.example.andy.connectutil.Fragment.DeviceFragment.BathbullyFragment;
import com.example.andy.connectutil.Fragment.DeviceFragment.FanLightFragment;
import com.example.andy.connectutil.Fragment.DeviceFragment.LEDLightFragment;
import com.example.andy.connectutil.Fragment.DeviceFragment.LightFragment;
import com.example.andy.connectutil.Fragment.FragmentHolder;
import com.example.andy.connectutil.R;
import com.example.andy.connectutil.entity.Device.Device;
import com.example.andy.connectutil.entity.Net.Content;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andy on 2017/4/8.
 */

public class OnlineDeviceAdapter extends RecyclerView.Adapter<OnlineDeviceAdapter.MyHolder>{
    private List<Device> OnlineList ;
    private Context mContext;
    private LayoutInflater mInflater;
    private List<String> devicename=new ArrayList<>();
    public OnlineDeviceAdapter(Context context, List<Device> onlineList,List<String> devicename)
    {
        this.devicename=devicename;
        this.mContext=context;
        this.OnlineList=onlineList;
        this.mInflater=LayoutInflater.from(mContext);
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.devicetextveiw,parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {

        String DeviceName="";
        if(devicename.size()==0||devicename.get(position).equals(""))
        {
            switch (OnlineList.get(position).getProduct_ID())
            {
                case Content.FanLIght_ID:
                    DeviceName="风扇灯";
                    break;
                case Content.Light_ID:
                    DeviceName="灯";
                    break;
                case Content.LEDLIght_ID:
                    DeviceName="LED灯";
                    break;
                case Content.BathBully_ID:
                    DeviceName="浴霸";
                    break;
            }
        }else
        {
            DeviceName=devicename.get(position);
        }

        holder.btn.setText(DeviceName);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity mainActivity=(MainActivity) mContext;
                FragmentHolder fragmentHolder=mainActivity.getHolder();
                if(OnlineList.get(position).getxDevice().getProductId().equals(Content.FanLIght_ID)){
                    FanLightFragment fanLedFragment=new FanLightFragment();
                    fanLedFragment.setDevice(OnlineList.get(position));
                    fragmentHolder.replaceFragment(fanLedFragment,"fanLightFragment",true);
                }else if(OnlineList.get(position).getxDevice().getProductId().equals(Content.Light_ID)) {

                    fragmentHolder.replaceFragment(new LightFragment(),"LightFragment",true);

                }else  if(OnlineList.get(position).getxDevice().getProductId().equals(Content.LEDLIght_ID))
                {
                    fragmentHolder.replaceFragment(new LEDLightFragment(),"LEDLightFragment",true);
                }else if(OnlineList.get(position).getxDevice().getProductId().equals(Content.BathBully_ID))
                {
                    fragmentHolder.replaceFragment(new BathbullyFragment(),"BathbullyFragment",true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return OnlineList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder
    {
        public Button btn;
        public MyHolder(View itemView) {
            super(itemView);
            btn=(Button)itemView.findViewById(R.id.Basic_text);
        }
    }
}
