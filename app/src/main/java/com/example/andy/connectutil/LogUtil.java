package com.example.andy.connectutil;

import android.util.Log;

/**
 * Created by 95815 .
 * Date:2017/4/8.
 * Writter: waiwen .
 * E-mail:iwaiwen@163.com .
 */

public class LogUtil {
    private static boolean showLog = true;
    public  static void  showLog(String str){
        if(showLog){
            Log.d("waiwen",str);
        }

    }








}
