package com.example.andy.connectutil.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andy.connectutil.Activity.MainActivity;
import com.example.andy.connectutil.Bean.Equitment;
import com.example.andy.connectutil.Fragment.BathbullyFragment;
import com.example.andy.connectutil.Fragment.ContFanLedFragment;
import com.example.andy.connectutil.Fragment.FragmentHolder;
import com.example.andy.connectutil.Fragment.LEDLightFragment;
import com.example.andy.connectutil.Fragment.LightFragment;
import com.example.andy.connectutil.R;

import java.util.List;


/**
 * Created by 95815 .
 * Date:2017/3/30.
 * Writter: waiwen .
 * E-mail:iwaiwen@163.com .
 */

public class AddEquitAdapter extends RecyclerView.Adapter<AddEquitAdapter.MyViewHolder> {

    private List<Equitment> equitmentList ;
    private Context mContext;
    private LayoutInflater mInflater;

    public AddEquitAdapter(Context context,List<Equitment> list) {
        this.mContext = context;
        this.equitmentList = list;
        this.mInflater= LayoutInflater.from(mContext);
    }

    /**
     * @param parent  RecycleView
     * @param viewType  view的类型可以用来显示多列表布局等
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View itemView = mInflater.inflate(R.layout.recycleview_itemview,parent,false);
       final MyViewHolder viewHolder = new MyViewHolder(itemView);
        viewHolder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String DeviceName=viewHolder.tv_name.getText().toString();
                MainActivity mainActivity=(MainActivity)mContext;
                mainActivity.setBottomSheetOnOff();
                FragmentHolder fragmentHolder=mainActivity.getHolder();
                if(DeviceName.equals("风扇灯")){
                    fragmentHolder.replaceFragment(new ContFanLedFragment(),"ContFanLedFragment");
                }else if(DeviceName.equals("灯")) {
                    fragmentHolder.replaceFragment(new LightFragment(),"LightFragment");
                }else  if(DeviceName.equals("LED灯"))
                {
                    fragmentHolder.replaceFragment(new LEDLightFragment(),"LEDLightFragment");
                }else if(DeviceName.equals("浴霸"))
                {
                    fragmentHolder.replaceFragment(new BathbullyFragment(),"BathbullyFragment");
                }

            }
        });
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Equitment equitment = equitmentList.get(position);
        holder.tv_name.setText(equitment.getEquit_name());
        holder.img_equitment.setImageResource(equitment.getEquit_resId());
    }





    @Override
    public int getItemCount() {
        return equitmentList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_name;
        public ImageView img_equitment;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView)itemView.findViewById(R.id.item_tv_equitname);
            img_equitment= (ImageView)itemView.findViewById(R.id.item_img_equitment);

        }
    }

}
