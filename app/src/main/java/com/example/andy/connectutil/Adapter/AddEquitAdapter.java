package com.example.andy.connectutil.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.andy.connectutil.Activity.MainActivity;
import com.example.andy.connectutil.R;
import com.example.andy.connectutil.entity.Device.Device;
import com.example.andy.connectutil.entity.Net.Content;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.xlink.wifi.sdk.XDevice;


/**
 * Created by 95815 .
 * Date:2017/3/30.
 * Writter: waiwen .
 * E-mail:iwaiwen@163.com .
 */

public class AddEquitAdapter extends RecyclerView.Adapter<AddEquitAdapter.MyViewHolder> {


    public void setShow_type(boolean show_type) {
        this.show_type = show_type;
        notifyDataSetChanged();
    }

    private boolean show_type = true;

    String DeviceName = "";
    int resource = 0;

    private List<XDevice> devices;
    private String authorcode;
    private List<Device> equitmentList;
    private Context mContext;
    private LayoutInflater mInflater;
    private MainActivity mainActivity;

    public List<String> getDevicename() {
        return devicename;
    }

    private List<String> devicename = new ArrayList<>();

    public AddEquitAdapter(Context context, List<Device> list, String authorcode, List<String> devicename) {
        this.devicename = devicename;
        this.authorcode = authorcode;
        this.mContext = context;
        mainActivity = (MainActivity) mContext;
        this.equitmentList = list;
        this.mInflater = LayoutInflater.from(mContext);
    }

    /**
     * @param parent   RecycleView
     * @param viewType view的类型可以用来显示多列表布局等
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycleview_itemview, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {

        if (devicename.size() == 0 || devicename.get(position).equals("")) {
            switch (equitmentList.get(position).getProduct_ID()) {

                case Content.FanLIght_ID:
                    DeviceName = "风扇灯";
                    resource = R.drawable.icon_item_fanled_white;
                    break;
                case Content.Light_ID:
                    DeviceName = "灯";
                    resource = R.drawable.icon_light;
                    break;
                case Content.LEDLIght_ID:
                    DeviceName = "LED灯";
                    resource = R.drawable.icon_led;
                    break;
                case Content.BathBully_ID:
                    DeviceName = "浴霸";
                    resource = R.drawable.buttom_menu_bathbully;
                    break;
            }
        } else {
            DeviceName = devicename.get(position);
        }
        //当show_type为true时，为正常显示模式
if(show_type) {
    viewHolder.edit_name.setVisibility(View.INVISIBLE);   //edittext不可见
    viewHolder.right_ibtn.setVisibility(View.INVISIBLE); //右边滑动指示图片不可见
    if (!DeviceName.equals("") && !(resource == 0)) {
        viewHolder.tv_name.setText(DeviceName);
        viewHolder.left_ibtn.setImageResource(resource);
        viewHolder.left_ibtn.setEnabled(false);
    }
}else {
    viewHolder.edit_name.setVisibility(View.VISIBLE);
    viewHolder.edit_name.setText(DeviceName);  //用了fragmentlayout,edittext在textview下面此时仍被遮挡，
    viewHolder.left_ibtn.setImageResource(R.drawable.icon_delect);
    viewHolder.left_ibtn.setEnabled(true);
    viewHolder.right_ibtn.setVisibility(View.VISIBLE);
    viewHolder.right_ibtn.setImageResource(R.drawable.icon_item_drag);
    viewHolder.tv_name.setText(DeviceName);

    //接下来这里写你的逻辑

    viewHolder.left_ibtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //这里写你的删除逻辑



            notifyItemRemoved(viewHolder.getAdapterPosition()); //这两个函数是adapter自带的刷新的，会带动画
            notifyItemRangeChanged(position,devicename.size()-1 );

        }
    });



    viewHolder.tv_name.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            viewHolder.tv_name.setVisibility(View.GONE);
            //这里吧textview去掉了，大概写你的重命名逻辑吧



            return true;
        }
    });





}
//        viewHolder.tv_realname.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                viewHolder.tv_name.setVisibility(View.VISIBLE);
//                viewHolder.tv_realname.setVisibility(View.GONE);
//                viewHolder.img_equitment.setImageResource(R.drawable.icon_delect);
//                viewHolder.img_equitment.setClickable(true);
//                viewHolder.tv_name.requestFocus();
//                setEditTextFocused(viewHolder.tv_name);
//                return true;
//            }
//        });
//        viewHolder.img_equitment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "删除设备开始", Toast.LENGTH_SHORT).show();
//            /*    LoginUtil.renameDevice(new HttpUtils.HttpUtilsListner() {
//                    @Override
//                    public void onSuccess(String content) {
//                        Toast.makeText(mContext,"修改成功",Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFailed(int code, String msg) {
//                        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
//                    }
//                },"你好",equitmentList.get(position).getProduct_ID(),equitmentList.get(position).getxDevice().getDeviceId());
//                LoginUtil.getname(new HttpUtils.HttpUtilsListner() {
//                    @Override
//                    public void onSuccess(String content) {
//                        Toast.makeText(mContext,content,Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFailed(int code, String msg) {
//                        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
//                    }
//                },equitmentList.get(position).getProduct_ID(),equitmentList.get(position).getxDevice().getDeviceId());*/
//
//
//               /* LoginUtil.unsubDevice(new HttpUtils.HttpUtilsListner() {
//                    @Override
//                    public void onSuccess(String content) {
//                    Toast.makeText(mContext,"取消订阅成功",Toast.LENGTH_SHORT).show();
//                        mainActivity.getOnlinedevicelist();
//                    }
//
//                    @Override
//                    public void onFailed(int code, String msg) {
//                        Toast.makeText(mContext,"取消订阅失败"+msg,Toast.LENGTH_SHORT).show();
//                    }
//                },equitmentList.get(position).getxDevice().getDeviceId());*/
//            }
//        });
//
//       /* viewHolder.tv_name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_GO) {
//                    Toast.makeText(mainActivity,viewHolder.tv_name.getText(),Toast.LENGTH_SHORT).show();
//                    viewHolder.tv_realname.setText(viewHolder.tv_name.getText());
//                    viewHolder.tv_realname.setVisibility(View.VISIBLE);
//                    viewHolder.img_equitment.setImageResource(resource);
//                    viewHolder.tv_name.setVisibility(View.GONE);
//                }
//                return false;
//            }
//        });*/
//        viewHolder.tv_name.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_ENTER) {
//                    final String name = viewHolder.tv_name.getText().toString();
//                    if (name.length() < 32) {
//                        InputMethodManager imm = (InputMethodManager) mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
//                        imm.hideSoftInputFromWindow(viewHolder.tv_name.getWindowToken(), 0);
//                        LoginUtil.renameDevice(new HttpUtils.HttpUtilsListner() {
//                            @Override
//                            public void onSuccess(String content) {
//                                viewHolder.tv_realname.setText(name);
//                                viewHolder.tv_realname.setVisibility(View.VISIBLE);
//                                viewHolder.img_equitment.setImageResource(resource);
//                                viewHolder.tv_name.setVisibility(View.GONE);
//                                mainActivity.notifyNameChange(position, name);
//                                viewHolder.img_equitment.setClickable(false);
//                            }
//
//                            @Override
//                            public void onFailed(int code, String msg) {
//                                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
//                            }
//                        }, name, equitmentList.get(position).getProduct_ID(), equitmentList.get(position).getxDevice().getDeviceId());
//                        return true;
//                    } else//字符大小不可以超过32个
//                    {
//                        Toast.makeText(mContext, "不可以超过8个字", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//                return false;
//            }
//
//        });

    }

    @Override
    public int getItemCount() {
        return equitmentList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        public ImageButton right_ibtn;
        public TextView tv_name;
        public EditText edit_name;
        public ImageButton left_ibtn;

        public MyViewHolder(View itemView) {
            super(itemView);
           left_ibtn = (ImageButton)itemView.findViewById(R.id.left_ibtn) ;
            right_ibtn = (ImageButton)itemView.findViewById(R.id.right_ibtn);
            tv_name = (TextView)itemView.findViewById(R.id.tv_name);
            edit_name = (EditText)itemView.findViewById(R.id.edit_name);


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
