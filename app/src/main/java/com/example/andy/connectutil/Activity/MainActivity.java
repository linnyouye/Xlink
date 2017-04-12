package com.example.andy.connectutil.Activity;

import android.content.Context;
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
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.connectutil.Adapter.AddEquitAdapter;
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
import com.example.andy.connectutil.XlinkConnect;
import com.example.andy.connectutil.entity.Device.Device;
import com.example.andy.connectutil.entity.Net.HttpUtils;
import com.example.andy.connectutil.entity.Net.JsonParser;
import com.example.andy.connectutil.entity.Net.Key;
import com.example.andy.connectutil.entity.Net.LoginUtil;
import com.example.andy.connectutil.entity.WifiUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 95815 .
 * Date:2017/3/29.
 * Writter: waiwen .
 * E-mail:iwaiwen@163.com .
 * 描述：该活动是app的主活动，管理view和维护fragment
 */

public class MainActivity extends BasicActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, HolderListener {


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
    List<Device> OnlinedeviceList;
    RecyclerView recyclerView;
    AddEquitAdapter mAdapter;
    RecyclerView OnlineDeviceRecycleview;
    OnlineDeviceAdapter onlineDeviceAdapter;

    EditText et_tb_search;
    ImageView ibtn_tb_search;

    ImageButton toolbar_search;
    ImageButton toolbar_menu;
    ImageButton img_backup;

    ImageButton bottom_add_equitment;
    ImageButton bottom_setting;

    TextView main_title;
    List<String> devicenames;
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
    protected void initData() {
        fragmentManager = getSupportFragmentManager();
        holder = new FragmentHolder(this, this, fragmentManager);
        OnlinedeviceList = new ArrayList<>();
        devicenames=new ArrayList<>();
        //测试数据
        mAdapter = new AddEquitAdapter(this, OnlinedeviceList, XlinkConnect.authorize,devicenames);
        int spacingInPixels = 8;
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        recyclerView.setAdapter(mAdapter);

        onlineDeviceAdapter = new OnlineDeviceAdapter(this, OnlinedeviceList,devicenames);
        OnlineDeviceRecycleview.setAdapter(onlineDeviceAdapter);

        Log.d("waiwen", "setAdapter：");


        account = new Account(getApplicationContext());
        getOnlinedevicelist();
        if(equitmentSelectFragment == null){
            equitmentSelectFragment = EquitmentSelectFragment.newInstance();
        }
        notifybackup();
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

        recyclerView = obtainView(R.id.pop_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        OnlineDeviceRecycleview = obtainView(R.id.Online_device);
        OnlineDeviceRecycleview.setLayoutManager(new LinearLayoutManager(this));
        Log.d("waiwen", "initview");
        // recyclerView.setItemAnimator(new DefaultItemAnimator());
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
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.img_backup:
               // fragmentManager.popBackStackImmediate();
                    holder.removeAllFragment();
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
        if(fragment_state == MainActivity.TAG){
            img_backup.setVisibility(View.INVISIBLE);
            refresh_ibtn.setVisibility(View.VISIBLE);
        }else {
            img_backup.setVisibility(View.VISIBLE);
            refresh_ibtn.setVisibility(View.INVISIBLE);
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
        if(holder.getFragmentManager().getFragments()==null||holder.getFragment_State().equals("Main"))
        {
            int state=behavior.getState();
            if (state==BottomSheetBehavior.STATE_EXPANDED) {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

            } else {
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {  super.onBackPressed();}

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(!imm.isActive())
        {
            int state=behavior.getState();
            if (state==BottomSheetBehavior.STATE_EXPANDED) {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }else
            {
                if (keyCode == event.KEYCODE_BACK) {

                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START);
                    } else if (!holder.removeOne()) {
                        backup();

                    }

                }
            }
        }



        return false;  //super.onKeyDown(keyCode, event);
    }

    public String getFragment_state() {
        return fragment_state;
    }


    public FragmentHolder getHolder() {
        return holder;
    }

    public FragmentManager getFragmentManger() {
        return fragmentManager;
    }

    public void backup() {
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "双击退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 1000); // 如果时间内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            this.onBackPressed();
            System.exit(0);
        }

    }

    public void addEquitment() {
        if (getFragment_state() != EquitmentSelectFragment.TAG) {
            if (getFragment_state() == WifiConnectionFragment.TAG) {
                fragmentManager.popBackStackImmediate();
            }
            else if(getFragment_state() == CountDownFragment.TAG){
                return;
            }
            else {
                holder.replaceFragment(equitmentSelectFragment, EquitmentSelectFragment.TAG, true);
            }{

            }
        }

    }

    public void getOnlinedevicelist() {
        for(int i=0;i<OnlinedeviceList.size();i++)
        {
            OnlinedeviceList.remove(i);
            devicenames.remove(i);
        }

        LoginUtil.getDevices(new HttpUtils.HttpUtilsListner() {
            @Override
            public void onSuccess(String content) {
                Toast.makeText(getApplicationContext(), "获取设备列表成功" , Toast.LENGTH_SHORT).show();
                List<Device> list = new ArrayList<Device>();
                list = JsonParser.parseDeviceList(content);
                for (Device device : list) {
                    if(!OnlinedeviceList.contains(device))
                        OnlinedeviceList.add(device);
                    getname(OnlinedeviceList.size());
                }

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
                Toast.makeText(getApplicationContext(), "获取设备列表失败", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void notifyAdapter() {
        mAdapter.notifyDataSetChanged();
        onlineDeviceAdapter.notifyDataSetChanged();
    }
    public void destoryOrtherFragment()
    {
        holder.removeAllFragment();
    }
    public void notifybackup()
    {
        if(holder.getFragment_State().equals("Main"))
        {
        img_backup.setVisibility(View.INVISIBLE);
        }
        else
        {
            img_backup.setVisibility(View.VISIBLE);
        }

    }
//刷新列表的设备名字
   public void getname(int position)
    {

            LoginUtil.getname(new HttpUtils.HttpUtilsListner() {
                @Override
                public void onSuccess(String content) {
                    String name;
                    try {

                        JSONObject obj=new JSONObject(content);
                         name=obj.optString("name");
                        devicenames.add(name);
                        //防止position出错
                        if(OnlinedeviceList.size()==devicenames.size())
                            notifyAdapter();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailed(int code, String msg) {
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                }
            },OnlinedeviceList.get(position-1).getProduct_ID(),OnlinedeviceList.get(position-1).getxDevice().getDeviceId());

    }

    public void notigynamechange(int position,String name)
    {
        devicenames.set(position,name);
        mAdapter.notifyDataSetChanged();
        onlineDeviceAdapter.notifyDataSetChanged();
    }
}
