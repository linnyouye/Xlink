package com.example.andy.connectutil.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.connectutil.Adapter.AddEquitAdapter;
import com.example.andy.connectutil.Adapter.DragItemCallback;
import com.example.andy.connectutil.Adapter.OnlineDeviceAdapter;
import com.example.andy.connectutil.Fragment.CountDownFragment;
import com.example.andy.connectutil.Fragment.DeviceFragment.FanLightFragment;
import com.example.andy.connectutil.Fragment.EquitmentSelectFragment;
import com.example.andy.connectutil.Fragment.FragmentHolder;
import com.example.andy.connectutil.Fragment.HolderListener;
import com.example.andy.connectutil.Fragment.WifiConnectionFragment;
import com.example.andy.connectutil.R;
import com.example.andy.connectutil.SharePrefrence.Account;
import com.example.andy.connectutil.View.SpaceItemDecoration;
import com.example.andy.connectutil.entity.Device.Device;
import com.example.andy.connectutil.entity.Net.ErrorMessage;
import com.example.andy.connectutil.entity.Net.HttpUtils;
import com.example.andy.connectutil.entity.Net.JsonParser;
import com.example.andy.connectutil.entity.Net.LoginUtil;
import com.example.andy.connectutil.entity.WifiUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Created by 95815 .
 * Date:2017/3/29.
 * Writter: waiwen .
 * E-mail:iwaiwen@163.com .
 * 描述：该活动是app的主活动，管理view和维护fragment
 */

public class MainActivity extends BasicActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, HolderListener {


    private boolean setting_state = true;

    private long firstTime = 0;

    private List<Device> saveInstance;
    public static final String TAG = "MainActivity";

    private ImageButton refresh_ibtn;
    private String fragment_state = "MainActivity";

    private boolean isExit = false;
    private Timer tExit;
    private Account account;
    private FragmentManager fragmentManager;

    public FragmentHolder holder;
    protected DrawerLayout drawer;
    protected NavigationView navigationView;
    protected RelativeLayout rl_bottom;

    private EquitmentSelectFragment equitmentSelectFragment = null;
    private List<Device> OnlinedeviceList;

    //adapter要获取itemhelper对象，点击是开始滑动
    public ItemTouchHelper getItemTouchHelper() {
        return itemTouchHelper;
    }

    private ItemTouchHelper itemTouchHelper = null;
    private RecyclerView bottomRecyclerView;
    private AddEquitAdapter mAdapter;
    private RecyclerView OnlineDeviceRecycleview;
    private OnlineDeviceAdapter onlineDeviceAdapter;

    private EditText et_tb_search;
    private ImageView ibtn_tb_search;

    private ImageButton toolbar_search;
    private ImageButton toolbar_menu;
    private ImageButton img_backup;

    private ImageButton bottom_add_equitment;
    private ImageButton bottom_setting;

    TextView main_title;
    protected BottomSheetBehavior behavior;

    @Override
    protected void setBefortLayout() {

    }

    @Override
    protected void setActionbar() {
        Toolbar toolbar = obtainView(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    protected void initView() {


        toolbar_search = obtainView(R.id.toolbar_ibtn_search);
        toolbar_menu = obtainView(R.id.toolbar_menu);

        refresh_ibtn = obtainView(R.id.main_refresh_ibtn);
        img_backup = obtainView(R.id.img_backup);
        main_title = obtainView(R.id.main_tv_title);

        navigationView = obtainView(R.id.nav_view);
        rl_bottom = obtainView(R.id.rl_bottom_addequit);
        drawer = obtainView(R.id.drawer_layout);

        NestedScrollView bottomSheetView = obtainView(R.id.bottomsheet_view);
        behavior = BottomSheetBehavior.from(bottomSheetView);

        et_tb_search = obtainView(R.id.toolbar_et_search);
        ibtn_tb_search = obtainView(R.id.toolbar_ibtn_search);

        bottom_add_equitment = obtainView(R.id.bottom_add_ibtn);
        bottom_setting = obtainView(R.id.bottom_setting);

        bottomRecyclerView = obtainView(R.id.pop_recycleview);
        bottomRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        OnlineDeviceRecycleview = obtainView(R.id.Online_device);
        OnlineDeviceRecycleview.setLayoutManager(new LinearLayoutManager(this));
        Log.d("waiwen", "initview");
        // recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void setListener() {

        toolbar_menu.setOnClickListener(this);
        toolbar_search.setOnClickListener(this);
        img_backup.setOnClickListener(this);

        navigationView.setNavigationItemSelectedListener(this);
        rl_bottom.setOnClickListener(this);

        bottom_add_equitment.setOnClickListener(this);
        bottom_setting.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        fragmentManager = getSupportFragmentManager();
        holder = new FragmentHolder(this, this, fragmentManager);
        OnlinedeviceList = new ArrayList<>();
        //测试数据
        mAdapter = new AddEquitAdapter(this, OnlinedeviceList);
        int spacingInPixels = 8;
        bottomRecyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        bottomRecyclerView.setAdapter(mAdapter);

        onlineDeviceAdapter = new OnlineDeviceAdapter(this, OnlinedeviceList);
        OnlineDeviceRecycleview.setAdapter(onlineDeviceAdapter);

        itemTouchHelper = new ItemTouchHelper(new DragItemCallback(mAdapter, onlineDeviceAdapter));
        itemTouchHelper.attachToRecyclerView(bottomRecyclerView);

        Log.d("waiwen", "setAdapter：");


        account = new Account(getApplicationContext());
        getOnlinedevicelist();
        if (equitmentSelectFragment == null) {
            equitmentSelectFragment = EquitmentSelectFragment.newInstance();
        }
        //notifybackup();
        setFraagment_State(MainActivity.TAG);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_basic;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_menu:
                setDrawerOnOff();
                if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;
            case R.id.rl_bottom_addequit:
                setBottomSheetOnOff();
                break;
            case R.id.bottom_add_ibtn:
                addEquitment();
                setBottomSheetOnOff();
                break;
            case R.id.img_backup:

                holder.removeAllFragment();
                break;
            case R.id.bottom_setting:
                if (setting_state) {

                    mAdapter.setShow_type(true);
                    setting_state = false;

                } else {
                    mAdapter.setShow_type(false);
                    setting_state = true;
                }
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_addequitment) {
            addEquitment();
            setDrawerOnOff();
        } else if (id == R.id.nav_share) {
            startActivity(new Intent(this, ShareActivity.class));
            setDrawerOnOff();
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this, AboutActivity.class));
            setDrawerOnOff();
        } else if (id == R.id.nav_help) {
            startActivity(new Intent(this, HelpActivity.class));
            setDrawerOnOff();
        } else if (id == R.id.nav_language) {
            startActivity(new Intent(this, LanguageActivity.class));
            // holder.replaceFragment(ControlFanLedFragment.newInstance(),ControlFanLedFragment.TAG,false);
            setDrawerOnOff();
        } else if (id == R.id.nav_backup) {
            account.setAccount(account.getUser(), "");
            finish();
            Intent intent = new Intent(MainActivity.this, RegisterAndLoginActivity.class);
            startActivity(intent);
        }
        return true;
    }

    protected void setDrawerOnOff() {
        if (!drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START);
        } else {
            drawer.closeDrawer(GravityCompat.START);
        }

    }

    @Override
    public void startWifiConnection(String produt_id) {

        holder.replaceFragment(WifiConnectionFragment.newInstance(WifiUtils.getWifiSSID(this), produt_id), WifiConnectionFragment.TAG, true);

    }

    @Override
    public void setFraagment_State(String str) {
        fragment_state = str;
        if (fragment_state == MainActivity.TAG) {
            img_backup.setVisibility(View.INVISIBLE);
            refresh_ibtn.setVisibility(View.VISIBLE);
            rl_bottom.setClickable(true);
        } else {
            img_backup.setVisibility(View.VISIBLE);
            refresh_ibtn.setVisibility(View.INVISIBLE);
            rl_bottom.setClickable(false );
        }
        switch (fragment_state) {

            case MainActivity.TAG:
                main_title.setText("设备列表");

                break;

            case EquitmentSelectFragment.TAG:
                main_title.setText("选择设备型号");
                break;

            case WifiConnectionFragment.TAG:
                main_title.setText("设备联网");
                break;

            case CountDownFragment.TAG:
                main_title.setText("正在连接");
                break;
            case FanLightFragment.TAG:
                main_title.setText("风扇灯");
                break;
        }


    }


    public void setBottomSheetOnOff() {
            int state = behavior.getState();
            if (state == BottomSheetBehavior.STATE_EXPANDED) {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

            } else {
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else if (!fragmentManager.popBackStackImmediate()) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
            } else {
                super.onBackPressed();
            }
        }


    }


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//            int state=behavior.getState();
//            if (state==BottomSheetBehavior.STATE_EXPANDED) {
//          //      behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//         //       return true;
//            }else
//            {
//                if (keyCode == event.KEYCODE_BACK) {
//
//                    if (drawer.isDrawerOpen(GravityCompat.START)) {
//                        drawer.closeDrawer(GravityCompat.START);
//                    } else if (!holder.removeOne()) {
//
//                  //      backup();
//
//                    }
//
//                }
//            }
//        return false;  //super.onKeyDown(keyCode, event);
//    }

    public String getFragment_state() {
        return fragment_state;
    }


    public FragmentHolder getHolder() {
        return holder;
    }

    public FragmentManager getFragmentManger() {
        return fragmentManager;
    }


    public void addEquitment() {
        if (getFragment_state() != EquitmentSelectFragment.TAG) {
            if (getFragment_state() == WifiConnectionFragment.TAG) {
                fragmentManager.popBackStackImmediate();
            } else if (getFragment_state() == CountDownFragment.TAG) {
                return;
            } else {
                holder.replaceFragment(equitmentSelectFragment, EquitmentSelectFragment.TAG, true);
            }
            {

            }
        }

    }

    public void getOnlinedevicelist() {
        for (int i = 0; i < OnlinedeviceList.size(); i++) {
            OnlinedeviceList.remove(i);
        }

        LoginUtil.getDevices(new HttpUtils.HttpUtilsListner() {
            @Override
            public void onSuccess(String content) {
                Toast.makeText(getApplicationContext(), "获取设备列表成功", Toast.LENGTH_SHORT).show();
                List<Device> list = new ArrayList<Device>();
                list = JsonParser.parseDeviceList(content);
                for (Device device : list) {
                    if (!OnlinedeviceList.contains(device))
                        OnlinedeviceList.add(device);
                }
                getname();
               /* try {

                    JSONObject obj=new JSONObject(content);
                   String name=obj.optString("name");
                    devicenames.add(name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }

            @Override
            public void onFailed(int code, String msg) {
                if (code > 4000000) {
                    ErrorMessage e = new ErrorMessage(getApplicationContext(), code);
                } else {
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void notifyAdapter() {
        mAdapter.notifyDataSetChanged();
        onlineDeviceAdapter.notifyDataSetChanged();
    }

    public void destoryOrtherFragment() {
        holder.removeAllFragment();
    }

    public void notifybackup() {
            if (holder.getFragment_State().equals(MainActivity.TAG)) {
            img_backup.setVisibility(View.INVISIBLE);
        } else {
            img_backup.setVisibility(View.VISIBLE);
        }

    }

    //刷新列表的设备名字
    public void getname() {
        for (int i = 0; i < OnlinedeviceList.size(); i++) {
            final int finalI = i;
            LoginUtil.getname(new HttpUtils.HttpUtilsListner() {
                @Override
                public void onSuccess(String content) {
                    String name;
                    try {

                        JSONObject obj = new JSONObject(content);
                        name = obj.optString("name");
                        OnlinedeviceList.get(finalI).setName(name);
                        //防止position出错
                        if (finalI == OnlinedeviceList.size() - 1)
                            notifyAdapter();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailed(int code, String msg) {
                    if (code > 4000000) {
                        ErrorMessage e = new ErrorMessage(getApplicationContext(), code);
                    } else {
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                }
            }, OnlinedeviceList.get(i).getProduct_ID(), OnlinedeviceList.get(i).getxDevice().getDeviceId());
        }


    }

    public void delectDevice(int position) {


        //在这里写你的删除逻辑，不用再写通知变化，adapter里已经写了，这样职责分明点


    }


    public void notifyNameChange(int position, String name) {
        OnlinedeviceList.get(position).setName(name);
        mAdapter.notifyDataSetChanged();
        onlineDeviceAdapter.notifyDataSetChanged();
    }

    public void saveInstance() {

    }
}
