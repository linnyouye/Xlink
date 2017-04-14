package com.example.andy.connectutil.Adapter;

import android.app.Notification;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.connectutil.Activity.MainActivity;



import com.example.andy.connectutil.R;
import com.example.andy.connectutil.entity.Device.Device;
import com.example.andy.connectutil.entity.Net.Content;
import com.example.andy.connectutil.entity.Net.ErrorMessage;
import com.example.andy.connectutil.entity.Net.HttpUtils;
import com.example.andy.connectutil.entity.Net.LoginUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

    String DeviceName = "";
    int resource=0;

    private List<XDevice> devices;
    private List<Device> equitmentList ;
    private Context mContext;
    private LayoutInflater mInflater;
    private MainActivity mainActivity;

    public AddEquitAdapter(Context context, List<Device> list) {
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
        viewHolder.img_equitment.setClickable(false);
        viewHolder.tv_name.setVisibility(View.GONE);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {
         int i=0;
        if(equitmentList.get(position).getName()==null||equitmentList.get(position).getName().equals(""))
        {
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
        }else
        {
            DeviceName=equitmentList.get(position).getName();
            switch (equitmentList.get(position).getProduct_ID())
            {

                case Content.FanLIght_ID:
                    resource=R.drawable.buttom_menu_fan_light;
                    break;
                case Content.Light_ID:
                    resource=R.drawable.button_menu_led;
                    break;
                case Content.LEDLIght_ID:
                    resource= R.drawable.button_menu_fanc;
                    break;
                case Content.BathBully_ID:
                    resource=R.drawable.buttom_menu_bathbully;
                    break;
            }

        }

        if(!DeviceName.equals("")&&!(resource==0))
        {
            viewHolder.tv_realname.setText(DeviceName);
            viewHolder.img_equitment.setImageResource(resource);
        }

        viewHolder.tv_realname.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                viewHolder.tv_name.setVisibility(View.VISIBLE);
                viewHolder.tv_realname.setVisibility(View.GONE);
                viewHolder.img_equitment.setImageResource(R.drawable.buttom_pop_menu_suberdivice);
                viewHolder.img_equitment.setClickable(true);
                viewHolder.tv_name.requestFocus();
                setEditTextFocused(viewHolder.tv_name);
                return true;
            }
        });
        viewHolder.img_equitment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"删除设备开始",Toast.LENGTH_SHORT).show();
            /*    LoginUtil.renameDevice(new HttpUtils.HttpUtilsListner() {
                    @Override
                    public void onSuccess(String content) {
                        Toast.makeText(mContext,"修改成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(int code, String msg) {
                        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
                    }
                },"你好",equitmentList.get(position).getProduct_ID(),equitmentList.get(position).getxDevice().getDeviceId());
                LoginUtil.getname(new HttpUtils.HttpUtilsListner() {
                    @Override
                    public void onSuccess(String content) {
                        Toast.makeText(mContext,content,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailed(int code, String msg) {
                        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
                    }
                },equitmentList.get(position).getProduct_ID(),equitmentList.get(position).getxDevice().getDeviceId());*/


               /* LoginUtil.unsubDevice(new HttpUtils.HttpUtilsListner() {
                    @Override
                    public void onSuccess(String content) {
                    Toast.makeText(mContext,"取消订阅成功",Toast.LENGTH_SHORT).show();
                        mainActivity.getOnlinedevicelist();
                    }

                    @Override
                    public void onFailed(int code, String msg) {
                        Toast.makeText(mContext,"取消订阅失败"+msg,Toast.LENGTH_SHORT).show();
                    }
                },equitmentList.get(position).getxDevice().getDeviceId());*/
            }
        });

       /* viewHolder.tv_name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    Toast.makeText(mainActivity,viewHolder.tv_name.getText(),Toast.LENGTH_SHORT).show();
                    viewHolder.tv_realname.setText(viewHolder.tv_name.getText());
                    viewHolder.tv_realname.setVisibility(View.VISIBLE);
                    viewHolder.img_equitment.setImageResource(resource);
                    viewHolder.tv_name.setVisibility(View.GONE);
                }
                return false;
            }
        });*/
        viewHolder.tv_name.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    final String name=viewHolder.tv_name.getText().toString();
                    if(name.length()<32)
                    {
                        InputMethodManager imm = (InputMethodManager) mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(viewHolder.tv_name.getWindowToken(), 0) ;
                        LoginUtil.renameDevice(new HttpUtils.HttpUtilsListner() {
                            @Override
                            public void onSuccess(String content) {
                                viewHolder.tv_realname.setText(name);
                                viewHolder.tv_realname.setVisibility(View.VISIBLE);
                                viewHolder.img_equitment.setImageResource(resource);
                                viewHolder.tv_name.setVisibility(View.GONE);
                                mainActivity.notigynamechange(position,name);
                                viewHolder.img_equitment.setClickable(false);
                            }

                            @Override
                            public void onFailed(int code, String msg) {
                                if(code>4000000)
                                {
                                    ErrorMessage e=new ErrorMessage(mContext,code);
                                }else
                                {
                                    Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
                                }
                            }
                        },name,equitmentList.get(position).getProduct_ID(),equitmentList.get(position).getxDevice().getDeviceId());
                        return true;
                    }else//字符大小不可以超过32个
                    {
                        Toast.makeText(mContext,"不可以超过8个字",Toast.LENGTH_SHORT).show();
                    }

                }
                return false;
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
        public TextView tv_realname;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (EditText)itemView.findViewById(R.id.item_tv_editname);
            img_equitment= (ImageView)itemView.findViewById(R.id.item_img_equitment);
            tv_realname=(TextView)itemView.findViewById(R.id.item_tv_equitname);
        }
    }

    public static void setEditTextFocused(final EditText input) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {
                InputMethodManager inputManager = (InputMethodManager) input
                        .getContext().getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(input, 0);
            }

        }, 200);
    }

}
