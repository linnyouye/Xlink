package com.example.andy.connectutil.Adapter;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.connectutil.Activity.MainActivity;
import com.example.andy.connectutil.Bean.Equitment;
import com.example.andy.connectutil.Fragment.DeviceFragment.BathbullyFragment;
import com.example.andy.connectutil.Fragment.DeviceFragment.ContFanLedFragment;
import com.example.andy.connectutil.Fragment.FragmentHolder;

import com.example.andy.connectutil.Fragment.DeviceFragment.LEDLightFragment;
import com.example.andy.connectutil.Fragment.DeviceFragment.LightFragment;

import com.example.andy.connectutil.R;
import com.example.andy.connectutil.entity.Device.Device;
import com.example.andy.connectutil.entity.Net.Content;

import java.util.List;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.XlinkCode;
import io.xlink.wifi.sdk.listener.SubscribeDeviceListener;


/**
 * Created by 95815 .
 * Date:2017/3/30.
 * Writter: waiwen .
 * E-mail:iwaiwen@163.com .
 */

public class AddEquitAdapter extends RecyclerView.Adapter<AddEquitAdapter.MyViewHolder> {


    private List<XDevice> devices;
    private String  authorcode;
    private List<Device> equitmentList ;
    private Context mContext;
    private LayoutInflater mInflater;
    private MainActivity mainActivity;

    public AddEquitAdapter(Context context, List<Device> list, String authorcode) {
        this.authorcode=authorcode;
        this.mContext = context;
        mainActivity=(MainActivity)mContext;
        this.equitmentList = list;
        this.mInflater= LayoutInflater.from(mContext);
    }

    /**
     * @param parent  RecycleView
     * @param viewType  view的类型可以用来显示多列表布局等
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
       View itemView = mInflater.inflate(R.layout.recycleview_itemview,parent,false);
       final MyViewHolder viewHolder = new MyViewHolder(itemView);
        viewHolder.tv_name.setFocusableInTouchMode(false);
        viewHolder.tv_name.setFocusable(false);
/*        viewHolder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String DeviceName=viewHolder.tv_name.getText().toString();

                MainActivity mainActivity=(MainActivity)mContext;
                mainActivity.setBottomSheetOnOff();
                FragmentHolder fragmentHolder=mainActivity.getHolder();
                if(DeviceName.equals("风扇灯")){
                    fragmentHolder.replaceFragment(new ContFanLedFragment(),"ContFanLedFragment",true);
                }else if(DeviceName.equals("灯")) {
                    fragmentHolder.replaceFragment(new LightFragment(),"LightFragment",true);
                }else  if(DeviceName.equals("LED灯"))
                {
                    fragmentHolder.replaceFragment(new LEDLightFragment(),"LEDLightFragment",true);
                }else if(DeviceName.equals("浴霸"))
                {
                    fragmentHolder.replaceFragment(new BathbullyFragment(),"BathbullyFragment",true);

                }

            }
        });*/
        viewHolder.img_equitment.setClickable(false);

        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {
        String DeviceName = "";
        int resource=0;
        switch (equitmentList.get(position).getProduct_ID())
        {

                case Content.FanLIght_ID:
                    DeviceName="风扇灯";
                    resource=R.drawable.buttom_menu_fan_light;
                    break;
                case Content.Light_ID:
                    DeviceName="灯";
                    resource=R.drawable.button_menu_led;
                    break;
                case Content.LEDLIght_ID:
                    DeviceName="LED灯";
                    resource= R.drawable.button_menu_fanc;
                    break;
                case Content.BathBully_ID:
                    DeviceName="浴霸";
                    resource=R.drawable.buttom_menu_bathbully;
                    break;
        }
        if(!DeviceName.equals("")&&!(resource==0))
        {
            viewHolder.tv_name.setText(DeviceName);
            viewHolder.img_equitment.setImageResource(resource);
        }

        viewHolder.tv_name.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                viewHolder.tv_name.setFocusableInTouchMode(true);
                viewHolder.tv_name.setFocusable(true);
                viewHolder.img_equitment.setImageResource(R.drawable.buttom_pop_menu_suberdivice);
                viewHolder.img_equitment.setClickable(true);
                return true;
            }
        });
        viewHolder.img_equitment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"删除设备开始",Toast.LENGTH_SHORT).show();
                Log.d("popWindow", "onClick: "+authorcode+"解绑");
                int ret=XlinkAgent.getInstance().unsubscribeDevice(equitmentList.get(position).getxDevice(), authorcode, new SubscribeDeviceListener() {
                    @Override
                    public void onSubscribeDevice(XDevice xDevice, int i) {
                        XlinkAgent.getInstance().removeDevice(xDevice);
                        mainActivity.getOnlinedevicelist();
                    }
                });
                if(ret==0)
                {
                    Toast.makeText(mContext,"删除设备成功",Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(mContext,""+ret,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return equitmentList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public EditText tv_name;
        public ImageView img_equitment;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (EditText)itemView.findViewById(R.id.item_tv_equitname);
            img_equitment= (ImageView)itemView.findViewById(R.id.item_img_equitment);
        }
    }

}
