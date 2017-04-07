package com.example.andy.connectutil.Activity;


import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.andy.connectutil.Adapter.HelpAdapter;
import com.example.andy.connectutil.Bean.HelpItem;
import com.example.andy.connectutil.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 95815 .
 * Date:2017/4/4.
 * Writter: waiwen .
 * E-mail:iwaiwen@163.com .
 */

public class HelpActivity extends BarActivity {

    @Bind(R.id.ibtn_toolbar_backup)
    ImageButton ibtnToolbarBackup;
    @Bind(R.id.tv_toolbar_others)
    TextView tvToolbarOthers;
    @Bind(R.id.recycleview_help)
    RecyclerView recycleviewHelp;
    private List<HelpItem> helpItemList;

    private HelpAdapter helpAdapter;

    @Override
    protected void setBefortLayout() {
        super.setBefortLayout();
        helpItemList = new ArrayList<>();
        helpItemList.add(new HelpItem("设备列表", "设备列表设备列表设备列表"));
        helpItemList.add(new HelpItem("风扇灯", "设备列表设备列表设备列表" +
                "设备列表设备列表设备列表"
        ));
        helpItemList.add(new HelpItem("灯", "设备列表设备列表设备列表"));
        helpItemList.add(new HelpItem("LED", "设备列表设备列表设备列表"));
        helpItemList.add(new HelpItem("浴霸", "设备列表设备列表设备列表"));
    }

    @Override
    protected void initView() {
        super.initView();
        helpAdapter = new HelpAdapter(this, helpItemList);
// 实现RecyclerView实现竖向列表展示模式
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleviewHelp.setLayoutManager(layoutManager);
        recycleviewHelp.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recycleviewHelp.setAdapter(helpAdapter);

    }

    protected void initData() {

        super.initData();
        getTvTitle().setText("帮助");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_help;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ibtn_toolbar_backup, R.id.tv_toolbar_others, R.id.recycleview_help})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtn_toolbar_backup:
                break;
            case R.id.tv_toolbar_others:
                break;
            case R.id.recycleview_help:
                break;
        }
    }
}