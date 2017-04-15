package com.example.andy.connectutil.Activity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.andy.connectutil.Adapter.HelpAdapter;
import com.example.andy.connectutil.Bean.HelpItem;
import com.example.andy.connectutil.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 95815 .
 * Date:2017/4/4.
 * Writter: waiwen .
 * E-mail:iwaiwen@163.com .
 */

public class HelpActivity extends BarActivity {

    private  LinearLayoutManager linearLayoutManager;
    private List<HelpItem> helpItemList;

    public RecyclerView getRecyclerHelp() {
        return recyclerHelp;
    }

    private RecyclerView recyclerHelp;
    private HelpAdapter helpAdapter;

    @Override
    protected void setBefortLayout() {
        super.setBefortLayout();
        helpItemList = new ArrayList<>();
        helpItemList.add(new HelpItem("设备列表", R.drawable.help_device_list));
        helpItemList.add(new HelpItem("风扇灯", R.drawable.help_fan_light));
        helpItemList.add(new HelpItem("灯", R.drawable.help_light));
        helpItemList.add(new HelpItem("LED", R.drawable.help_led));
        helpItemList.add(new HelpItem("浴霸", R.drawable.help_wash_heater));
        helpItemList.add(new HelpItem("添加设备",R.drawable.help_add_device));
        helpItemList.add(new HelpItem("设备列表属性",R.drawable.help_edit_device_list));
    }

    @Override
    protected void initView() {
        super.initView();
        recyclerHelp = obtainView(R.id.recycleview_help);
        helpAdapter = new HelpAdapter(this, helpItemList);
// 实现RecyclerView实现竖向列表展示模式
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerHelp.setLayoutManager(linearLayoutManager);


        recyclerHelp.setAdapter(helpAdapter);

    }

    protected void initData() {

        super.initData();
        getTvTitle().setText("帮助");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_help;
    }

   public void smoothMoveToPosition(int position) {
        int firstPosition = linearLayoutManager.findFirstVisibleItemPosition();
        int lastPosition = linearLayoutManager.findLastVisibleItemPosition();
        if(position <= lastPosition){
            int top = recyclerHelp.getChildAt(position - firstPosition).getTop();
            recyclerHelp.smoothScrollBy(0, top);
        }

    }


}