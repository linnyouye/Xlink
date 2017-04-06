package com.example.andy.connectutil.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andy.connectutil.Activity.LEDLightHelper;
import com.example.andy.connectutil.R;
import com.example.andy.connectutil.View.LedlView;

import java.util.zip.Inflater;

/**
 * Created by andy on 2017/4/6.
 */

public class LEDLightFragment extends Fragment{
    private LedlView ledlView;
    private LEDLightHelper ledLightHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_view_led,container,false);
        ledlView=(LedlView)view.findViewById(R.id.LEDLightView);
        ledLightHelper
        ledlView.setOncontrolListener(new LedlView.OnControlListener() {
            @Override
            public void onClickCenter() {

            }

            @Override
            public void onClickOne() {

            }

            @Override
            public void onClickTwo() {

            }

            @Override
            public void onClickThree() {

            }

            @Override
            public void onClickBtnTL() {

            }

            @Override
            public void onClickBtnTR() {

            }

            @Override
            public void onClickBtnBL() {

            }

            @Override
            public void onClickBtnBR() {

            }
        });
                return view;
    }
}
