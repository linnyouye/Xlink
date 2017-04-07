package com.example.andy.connectutil.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.andy.connectutil.Adapter.AddEquitAdapter;
import com.example.andy.connectutil.Bean.Equitment;
import com.example.andy.connectutil.Fragment.DeviceFragement;
import com.example.andy.connectutil.Fragment.EquitmentSelectFragment;
import com.example.andy.connectutil.Fragment.FragmentHoldertest;
import com.example.andy.connectutil.Fragment.HolderListener;
import com.example.andy.connectutil.Fragment.WifiConnectionFragment;
import com.example.andy.connectutil.R;
import com.example.andy.connectutil.SharePrefrence.Account;
import com.example.andy.connectutil.View.SpaceItemDecoration;
import com.example.andy.connectutil.entity.WifiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.andy.connectutil.R.id.img_backup;

/**
 * Created by 95815 .
 * Date:2017/3/29.
 * Writter: waiwen .
 * E-mail:iwaiwen@163.com .
 * 描述：该活动是app的主活动，管理view和维护fragment
 */

public class MainActivity extends BasicActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, HolderListener {


    @Bind(R.id.toolbar_et_search)
    EditText toolbarEtSearch;
    @Bind(R.id.toolbar_ibtn_search)
    ImageButton toolbarIbtnSearch;
    @Bind(R.id.toolbar_menu)
    ImageButton toolbarMenu;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(img_backup)
    ImageButton imgBackup;
    @Bind(R.id.main_tv_title)
    TextView mainTvTitle;
    @Bind(R.id.fragment_layout)
    FrameLayout fragmentLayout;
    @Bind(R.id.main_up_ibtn)
    ImageButton mainUpIbtn;
    @Bind(R.id.main_down_ibtn)
    ImageButton mainDownIbtn;
    @Bind(R.id.main_refresh_ibtn)
    ImageView mainRefreshIbtn;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.rl_bottom_addequit)
    RelativeLayout rlBottomAddequit;
    @Bind(R.id.pop_recycleview)
    RecyclerView popRecycleview;
    @Bind(R.id.bottom_add_ibtn)
    ImageButton bottomAddIbtn;
    @Bind(R.id.bottom_setting)
    ImageButton bottomSetting;
    @Bind(R.id.bottomsheet_view)
    NestedScrollView bottomsheetView;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private Account account;
    private FragmentManager fragmentManager;

 //   public FragmentHolder holder;
    public FragmentHoldertest holdertest;
    protected DrawerLayout drawer;
    private static final String Main_State = "MainActivity";

    public String getFragment_state() {
        return fragment_state;
    }

    private String fragment_state = null;

    List<Equitment> equitmentList;
    RecyclerView recyclerView;
    AddEquitAdapter mAdapter;


    protected BottomSheetBehavior behavior;

    @Override
    protected void setBefortLayout() {

    }

    @Override
    protected void setActionbar() {
        setSupportActionBar(toolbar);
    }
    @Override
    public void mainViewUpdate(String state) {

    }
    @Override
    protected void initData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        equitmentList = new ArrayList<>();
        behavior = BottomSheetBehavior.from(bottomsheetView);
        //测试数据
        equitmentList.add(new Equitment("风扇", R.drawable.button_menu_fanc));
        equitmentList.add(new Equitment("LED灯", R.drawable.button_menu_led));
        mAdapter = new AddEquitAdapter(this, equitmentList);
        int spacingInPixels = 8;
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        fragmentManager = getSupportFragmentManager();
       // holder = new FragmentHolder(this, fragmentManager);
        holdertest = new FragmentHoldertest(this,fragmentManager);
       // holder.setState(FragmentHolder.MAIN_PAGE);

        account = new Account(getApplicationContext());

    }

    @Override
    public void setFraagment_State(String Tag_State) {
        this.fragment_state = Tag_State;
        switch (fragment_state) {
            case Main_State:
                mainTvTitle.setText("主界面");
                break;
            case EquitmentSelectFragment.Fragment_Tag_State:
              mainTvTitle.setText("选择设备界面");
                break;
            case WifiConnectionFragment.Fragment_Tag_State:
                mainTvTitle.setText("设备联网");
                break;
                   case DeviceFragement.Fragment_Tag_State:
                 mainTvTitle.setText("局域网设备");
                       break;
        }


    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_basic;
    }

    @OnClick({R.id.toolbar_menu, R.id.rl_bottom_addequit, R.id.bottom_add_ibtn, img_backup})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_menu:
                setDrawerOnOff();
                if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;
            case R.id.rl_bottom_addequit:
                showToast("点击了底部菜单");
                setBottomSheetOnOff();
                break;
            case R.id.bottom_add_ibtn:
//                if (holder.getState() == FragmentHolder.MAIN_PAGE) {
//                    holder.addFragment(EquitmentSelectFragment.newInstance(), EquitmentSelectFragment.Fragment_Tag_State);
//                    holder.setState(FragmentHolder.SELECT_FRAGMENT);
//                }
//                if (holder.getState() != FragmentHolder.SELECT_FRAGMENT) {
//                    holder.replaceFragment(EquitmentSelectFragment.newInstance(), EquitmentSelectFragment.Fragment_Tag_State);
//                    holder.setState(FragmentHolder.SELECT_FRAGMENT);
//                }
             holdertest.addFragment(EquitmentSelectFragment.newInstance(),EquitmentSelectFragment.Fragment_Tag_State,true);

                setBottomSheetOnOff();
                break;
            case img_backup:
//                if (holder.getState() == FragmentHolder.WIFI_CONNECTION_FRAGMENT) {
//                    holder.removeFragmentByTag(WifiConnectionFragment.fragment_tag);
//                } else if (holder.getState() == FragmentHolder.SELECT_FRAGMENT) {
//                    holder.removeFragmentByTag(EquitmentSelectFragment.Fragment_Tag_State);
//                }
                if(!fragmentManager.popBackStackImmediate()){
                    new BackUtil(this,1000).backup();
                }

                break;

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_addequitment) {
            add_Aquitment();
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
            setDrawerOnOff();
        } else if (id == R.id.nav_backup) {
            account.setAccount(account.getUser(), "");
            finish();
            Intent intent = new Intent(MainActivity.this, RegisterAndLoginActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public void add_Aquitment() {
//        if (holder.getState() == FragmentHolder.MAIN_PAGE) {
//            holder.addFragment(EquitmentSelectFragment.newInstance(), EquitmentSelectFragment.Fragment_Tag_State);
//            holder.setState(FragmentHolder.SELECT_FRAGMENT);
//        }
//        if (holder.getState() != FragmentHolder.SELECT_FRAGMENT) {
//            holder.replaceFragment(EquitmentSelectFragment.newInstance(), EquitmentSelectFragment.Fragment_Tag_State);
//            holder.setState(FragmentHolder.SELECT_FRAGMENT);
//        }


    }


    protected void setDrawerOnOff() {
        if (!drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START);
        } else {
            drawer.closeDrawer(GravityCompat.START);
        }

    }

    @Override
    public void startCountdownFragment() {
//        if (holder.getState() == FragmentHolder.WIFI_CONNECTION_FRAGMENT) {
//            holder.replaceFragment(CountDownFragment.newInstance(), CountDownFragment.fragment_tag);
//        }

    }


    @Override
    public void startWifiConnection(String produt_id) {

//        if (holder.getState() == FragmentHolder.SELECT_FRAGMENT) {
//            holder.replaceFragment(WifiConnectionFragment.newInstance(WifiUtils.getWifiSSID(this), produt_id), WifiConnectionFragment.fragment_tag);
//            holder.setState(FragmentHolder.WIFI_CONNECTION_FRAGMENT);
//        }
        holdertest.replaceFragment(WifiConnectionFragment.newInstance(WifiUtils.getWifiSSID(this),produt_id),WifiConnectionFragment.Fragment_Tag_State,true);
    }

    /**
     * @param fragment_title 页面标题
     * @param view_status    后退键的状态
     */
    @Override
    public void setMainPage(String fragment_title, int view_status) {
        imgBackup.setVisibility(view_status);

    }


    protected void setBottomSheetOnOff() {
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
        } else {

            super.onBackPressed();
        }
    }

    public FragmentHoldertest getHolder() {
      //  return holder;
        return holdertest;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == event.KEYCODE_BACK) {
            new BackUtil(this, 1000).backup();
        }
        return false;//super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
