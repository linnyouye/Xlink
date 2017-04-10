package com.example.andy.connectutil.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.andy.connectutil.R;
import com.example.andy.connectutil.entity.Net.Content;
import com.example.andy.connectutil.entity.Net.HttpUtils;
import com.example.andy.connectutil.entity.Net.Key;
import com.example.andy.connectutil.entity.Net.LoginUtil;
import com.example.andy.connectutil.entity.Net.Url;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 95815 .
 * Date:2017/4/9.
 * Writter: waiwen .
 * E-mail:iwaiwen@163.com .
 */

public class ResetPasswordActivity extends BarActivity {

    private static final int REGISTER_MODE_PHONE=0;
    private static final int REGISTER_MODE_MAIL=1;
    @Bind(R.id.radio_group)
    HorizontalRadioGroup radioGroup;
    @Bind(R.id.et_account)
    EditText etAccount;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.btn_get_code)
    Button btnGetCode;
    @Bind(R.id.auth_code_item)
    LinearLayout authCodeItem;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.password_item)
    LinearLayout passwordItem;
    @Bind(R.id.btn_reset_password)
    Button btnResetPassword;

    @Override
    protected void initView() {
        changeRegisterMode(REGISTER_MODE_PHONE);
        radioGroup.setItems(getResources().getStringArray(R.array.register_tab));
        radioGroup.setSelection(0);
        radioGroup.setOnItemSelectedListener(new HorizontalRadioGroup.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                changeRegisterMode(index);
            }
        });
    }

    @Override
    protected void initData() {
         getTvTitle().setText("找回密码");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_password;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);


    }

    @OnClick({R.id.btn_get_code, R.id.btn_reset_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_code:
                forgetPassword();
                break;
            case R.id.btn_reset_password:
                if(radioGroup.getSelection()==REGISTER_MODE_MAIL){
                    forgetPassword();
                }else{
                    resetPassword();
                }
                break;
        }
    }

    private void changeRegisterMode(int mode){
        switch(mode){
            case REGISTER_MODE_MAIL:
                etAccount.setHint(R.string.hint_mail);
                authCodeItem.setVisibility(View.GONE);
                passwordItem.setVisibility(View.GONE);

                break;
            case REGISTER_MODE_PHONE:
                etAccount.setHint(R.string.hint_phone);
                authCodeItem.setVisibility(View.VISIBLE);
                passwordItem.setVisibility(View.VISIBLE);

                break;
        }
    }

    private void forgetPassword(){
        String account=etAccount.getText().toString();


        LoginUtil.forgetPassword(account, new HttpUtils.HttpUtilsListner() {
            @Override
            public void onSuccess(String content) {

                if(radioGroup.getSelection()==REGISTER_MODE_MAIL){

                }else{

                }
            }

            @Override
            public void onFailed(int code, String msg) {

                showToast(msg);
            }
        });
    }
    //忘记密码
    public static void forgetPassword(String account, HttpUtils.HttpUtilsListner listener){
        try{
            JSONObject data=new JSONObject();
            data.put(Key.CORP_ID, Content.CORP_ID);
            if(account.contains("@")){
                data.put(Key.EMAIL,account);
            }else{
                data.put(Key.PHONE,account);
            }

            HttpUtils.postJson(Url.FORGET_PASSWORD, data.toString(),null, listener);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }
    private void resetPassword(){
        String account=etAccount.getText().toString();
        String code=etCode.getText().toString();
        String password=etPassword.getText().toString();


        LoginUtil.resetPassword(account, code, password, new HttpUtils.HttpUtilsListner() {
            @Override
            public void onSuccess(String content) {
            }

            @Override
            public void onFailed(int code, String msg) {
                showToast(msg);
            }
        });
    }







}
