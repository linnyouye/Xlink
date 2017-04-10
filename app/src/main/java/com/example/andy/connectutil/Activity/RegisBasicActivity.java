package com.example.andy.connectutil.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.andy.connectutil.R;

/**
 * Created by andy on 2017/4/10.
 */

public class RegisBasicActivity extends Activity{
    protected boolean D = true;
    protected String TAG = "lyy";


    private long mLastTimeTitleClicked;

    private InputMethodManager mInputManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        TAG = "lyy-" + getClass().getSimpleName();

        mInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0).setFitsSystemWindows(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // ----------startActivity-------------------------------------------------------------------
    protected void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    protected void startActivityForResult(Class<?> clz, int requestCode) {
        startActivityForResult(new Intent(this, clz), requestCode);
    }

    // ----------enableReturning-------------------------------------------------------------------
    protected void enableReturning() {
        View view = findViewById(R.id.btnBack);
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    finish();
                }
            });
        }
    }

    protected void enableSwipeBack() {

    }

    // ----------title-------------------------------------------------------------------
    @Override
    public void setTitle(CharSequence title) {
        try {
            ((TextView) findViewById(R.id.title_text)).setText(title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setTitle(int textId) {
        try {
            ((TextView) findViewById(R.id.title_text)).setText(textId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------Toast-------------------------------------------------------------------
    public void showToast(CharSequence msg) {

    }

    public void showToast(int strId) {

    }

    // ----------ProgressDialog-------------------------------------------------------------------
    public void showProgressDialog(CharSequence msg) {


    }

    public void showProgressDialog() {
        showProgressDialog(null);
    }

    public void showProgressDialog(int strId) {
        showProgressDialog(getResources().getString(strId));
    }

    public void cancelProgressDialog() {

    }

    protected void enableScrollToTop(final View containerView) {
        findViewById(R.id.title_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System.currentTimeMillis() - mLastTimeTitleClicked < 1000) {
                    if (containerView instanceof RecyclerView) {
                        ((RecyclerView) containerView).smoothScrollToPosition(0);
                    } else {
                        containerView.scrollTo(0, 0);
                    }
                }
                mLastTimeTitleClicked = System.currentTimeMillis();
            }
        });
    }

    //-------------InputMethodManager-------------------------
    protected boolean isInputManagerActive() {
        return mInputManager.isActive();
    }

    protected void hideInputManager(EditText et) {
        if (mInputManager.isActive()) {
            mInputManager.hideSoftInputFromWindow(et.getWindowToken(), 0);
        }

    }

}
