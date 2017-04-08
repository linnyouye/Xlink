package com.example.andy.connectutil.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.andy.connectutil.Bean.Equitment;
import com.example.andy.connectutil.R;
import com.example.andy.connectutil.entity.Device.BathBully;
import com.example.andy.connectutil.entity.Device.Device;
import com.example.andy.connectutil.entity.Device.FanLinght;
import com.example.andy.connectutil.entity.Device.LEDLight;
import com.example.andy.connectutil.entity.Device.Light;
import com.example.andy.connectutil.entity.Net.Content;

import java.util.List;

/**
 * Created by andy on 2017/4/8.
 */

public class Online_device_adapter extends RecyclerView.Adapter<Online_device_adapter.MyHolder>{
    private List<Device> OnlineList ;
    private Context mContext;
    private LayoutInflater mInflater;
    public Online_device_adapter(Context context,List<Device> onlineList)
    {
        this.mContext=context;
        this.OnlineList=onlineList;
        this.mInflater=LayoutInflater.from(mContext);
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.devicetextveiw,parent,false);
        MyHolder holder=new MyHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        String DeviceName="";
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
        holder.text.setText(DeviceName);
    }

    @Override
    public int getItemCount() {
        return OnlineList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder
    {
        public TextView text;
        public MyHolder(View itemView) {
            super(itemView);
            text=(TextView)itemView.findViewById(R.id.Basic_text);
        }
    }
}
