package com.example.andy.connectutil.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 95815 .
 * Date:2017/4/7.
 * Writter: waiwen .
 * E-mail:iwaiwen@163.com .
 */

public class BackUtil extends AppCompatActivity {


    private static boolean isExit = false;

    private Timer tExit;
    public Context context;
    private int time;

    BackUtil(Context context, int time) {

        this.context = context;
        this.time = time;
    }


    /**
     * 双击退出函数
     */


    public void backup() {

        if (isExit == false) {

            isExit = true; // 准备退出

            Toast.makeText(context, "双击退出程序", Toast.LENGTH_SHORT).show();

            tExit = new Timer();

            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, time); // 如果时间内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            onBackPressed();
            System.exit(0);
        }

    }


}
