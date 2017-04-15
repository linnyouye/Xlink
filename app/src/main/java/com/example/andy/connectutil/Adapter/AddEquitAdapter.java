package com.example.andy.connectutil.Adapter;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.connectutil.Activity.MainActivity;
import com.example.andy.connectutil.R;
import com.example.andy.connectutil.entity.Device.Device;
import com.example.andy.connectutil.entity.Net.Content;
import com.example.andy.connectutil.entity.Net.ErrorMessage;
import com.example.andy.connectutil.entity.Net.HttpUtils;
import com.example.andy.connectutil.entity.Net.LoginUtil;

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

    String DeviceName = "";
    int resource = 0;


    private AddEquipmentListener addEquipmentListener = null;


    //正常显示状态
    private boolean show_type = true;


    private List<XDevice> devices;


    private List<Device> equitmentList;
    private Context mContext;
    private LayoutInflater mInflater;
    private MainActivity mainActivity;

    public AddEquitAdapter(Context context, List<Device> equitmentList) {
        this.mContext = context;
        mainActivity = (MainActivity) mContext;
        this.equitmentList = equitmentList;
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
        //    int i=0;

        //获取到要显示的名字和图片
        if (equitmentList.get(position).getName() == null || equitmentList.get(position).getName().equals("")) {
            switch (equitmentList.get(position).getProduct_ID()) {

                case Content.FanLIght_ID:
                    DeviceName = "风扇灯";
                    resource = R.drawable.icon_item_fanled_white;
                    break;
                case Content.Light_ID:
                    DeviceName = "灯";
                    resource = R.drawable.icon_led;
                    break;
                case Content.LEDLIght_ID:
                    DeviceName = "LED灯";
                    resource = R.drawable.icon_light;
                    break;
                case Content.BathBully_ID:
                    DeviceName = "浴霸";
                    resource = R.drawable.buttom_menu_bathbully;
                    break;
            }
        } else {
            DeviceName = equitmentList.get(position).getName();
            resource = returnDeviceDrawable(equitmentList.get(position).getProduct_ID());
        }
        //右边的拖动ibtn和中间的edittext这时要隐藏
        viewHolder.right_ibtn.setVisibility(View.INVISIBLE);
        viewHolder.edit_name.setVisibility(View.GONE);
        viewHolder.right_ibtn.setClickable(false);
        //每次刷新，初始状态变成不拦截
        
        viewHolder.tv_realname.setVisibility(View.VISIBLE);
        viewHolder.tv_realname.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        viewHolder.left_ibtn.setClickable(false);
        if(!DeviceName.equals("")&& resource !=0 ){
        viewHolder.tv_realname.setText(DeviceName);
        //左边ibtn要设置设备图片和不可点击

        viewHolder.left_ibtn.setImageResource(resource);}

        // 根据show_type进行相应的显示
        if (show_type) {
            //这里重写是为了返回的时候拦截长按，否则在编辑长按返回正常显示后，这里也能长按编辑
            viewHolder.tv_realname.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });


        } else {
            //右边的滑动拖拽图片
            viewHolder.right_ibtn.setVisibility(View.VISIBLE);
            viewHolder.right_ibtn.setImageResource(R.drawable.icon_item_drag);
            viewHolder.right_ibtn.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                        //通知开始滑动
                        mainActivity.getItemTouchHelper().startDrag(viewHolder);
                    }
                    return true;
                }
            });


            //左边ibtn要设置删除设备图片和可点击
            viewHolder.left_ibtn.setClickable(true);
            viewHolder.left_ibtn.setImageResource(R.drawable.icon_delect);
            //左边ibtn点击删除
            viewHolder.left_ibtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //    在主类的该方法写你的删除逻辑，已经传进去Position;
                    mainActivity.delectDevice(viewHolder.getAdapterPosition());
                    //改变
                    notifyDataSetChanged();
                    //   notifyDataSetChanged();

                    Toast.makeText(mContext, "删除设备开始", Toast.LENGTH_SHORT).show();
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


            //长按中间的textview
            viewHolder.tv_realname.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    viewHolder.tv_realname.setVisibility(View.GONE);

                    viewHolder.edit_name.setVisibility(View.VISIBLE);
                    viewHolder.edit_name.setClickable(true);
                    viewHolder.edit_name.requestFocus();
                    setEditTextFocused(viewHolder.edit_name);
                    viewHolder.edit_name.setText(DeviceName);
                    return true;
                }
            });
            viewHolder.edit_name.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {
                        final String name = viewHolder.edit_name.getText().toString();
                        if (name.length() < 32) {
                            InputMethodManager imm = (InputMethodManager) mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(viewHolder.edit_name.getWindowToken(), 0);
                            LoginUtil.renameDevice(new HttpUtils.HttpUtilsListner() {
                                @Override
                                public void onSuccess(String content) {
                                    viewHolder.edit_name.setVisibility(View.GONE);
                                    viewHolder.tv_realname.setText(name);
                                    viewHolder.tv_realname.setVisibility(View.VISIBLE);

                                    mainActivity.notifyNameChange(position, name);
                                }

                                @Override
                                public void onFailed(int code, String msg) {
                                    if (code > 4000000) {
                                        ErrorMessage e = new ErrorMessage(mContext, code);
                                    } else {
                                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, name, equitmentList.get(position).getProduct_ID(), equitmentList.get(position).getxDevice().getDeviceId());
                            return true;
                        } else//字符大小不可以超过32个
                        {
                            Toast.makeText(mContext, "不可以超过8个字", Toast.LENGTH_SHORT).show();
                        }

                    }
                    return false;
                }

            });


        }


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

    }

    @Override
    public int getItemCount() {
        return equitmentList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public EditText edit_name;
        public ImageButton right_ibtn;
        public TextView tv_realname;
        public ImageButton left_ibtn;

        public MyViewHolder(View itemView) {
            super(itemView);
            edit_name = (EditText) itemView.findViewById(R.id.item_edit_name);
            left_ibtn = (ImageButton) itemView.findViewById(R.id.item_left_ibtn);
            right_ibtn = (ImageButton) itemView.findViewById(R.id.item_right_ibtn);
            tv_realname = (TextView) itemView.findViewById(R.id.item_tv_name);
        }
    }


    public List<Device> getEquitmentList() {
        return equitmentList;
    }

    public void setShow_type(boolean show_type) {
        this.show_type = show_type;
        notifyDataSetChanged();
    }


    interface AddEquipmentListener {

        void startDrag(AddEquitAdapter.MyViewHolder viewHolder);

    }


    //返回图片id
    private int returnDeviceDrawable(String product_id) {
        int drawableID = 0;
        switch (product_id) {
            case Content.FanLIght_ID:
                drawableID = R.drawable.icon_item_fanled_white;
                break;
            case Content.Light_ID:
                drawableID = R.drawable.icon_light;
                break;
            case Content.LEDLIght_ID:
                drawableID = R.drawable.icon_led;
                break;
            case Content.BathBully_ID:
                drawableID = R.drawable.buttom_menu_bathbully;
                break;
            default:
                drawableID = R.drawable.icon_delect;
                break;
        }

        return drawableID;


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
