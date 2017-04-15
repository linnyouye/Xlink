package com.example.andy.connectutil.entity.Device;

/**
 * Created by andy on 2017/4/15.
 */

import android.content.Context;
import android.widget.Toast;

import com.example.andy.connectutil.Activity.MyApplication;

public class ToastUtil {
    private static Toast mToast = null;

    private static long lastClickTime;

    // 防止连续点击按钮
    public ToastUtil(Context context)
    {

    }
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 1900) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    static {
        mToast = Toast.makeText(MyApplication.getContext(), "",
                Toast.LENGTH_SHORT);
    }

    public static void showToast(String str) {
        try {
            mToast.setText(str);
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showToast(int resId) {
        try {
            mToast.setText(resId);
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}