package com.example.andy.connectutil.Fragment.DeviceFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.andy.connectutil.Fragment.BaseFragment;
import com.example.andy.connectutil.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 95815 .
 * Date:2017/4/10.
 * Writter: waiwen .
 * E-mail:iwaiwen@163.com .
 */

public class ControlFanLedFragment extends BaseFragment {

   public static final String TAG = "ControlFanLedFragment";
    @Bind(R.id.fanled_ibtn_tl)
    ImageButton fanledIbtnTl;
    @Bind(R.id.fanled_tv_topshow)
    TextView fanledTvTopshow;
    @Bind(R.id.fanled_ibtn_tr)
    ImageButton fanledIbtnTr;
    @Bind(R.id.fanled_ibtn_bl)
    ImageButton fanledIbtnBl;
    @Bind(R.id.fanled_ibtn_br)
    ImageButton fanledIbtnBr;

    public static ControlFanLedFragment newInstance() {

        Bundle args = new Bundle();
        ControlFanLedFragment fragment = new ControlFanLedFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_view_controlfanled;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.fanled_ibtn_tl, R.id.fanled_ibtn_tr, R.id.fanled_ibtn_bl, R.id.fanled_ibtn_br})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanled_ibtn_tl:

                break;
            case R.id.fanled_ibtn_tr:
                break;
            case R.id.fanled_ibtn_bl:
                break;
            case R.id.fanled_ibtn_br:
                break;
        }
    }
}
