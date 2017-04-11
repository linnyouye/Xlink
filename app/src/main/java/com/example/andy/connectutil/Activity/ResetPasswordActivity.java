package com.example.andy.connectutil.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.andy.connectutil.R;
import com.example.andy.connectutil.SharePrefrence.Account;
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

public class ResetPasswordActivity extends RegisBasicActivity {

    private Account mAccount;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);

        mAccount=new Account(this);
        String user=mAccount.getUser();
        etAccount.setText(user);
        if(user.contains("@")){
            changeRegisterMode(REGISTER_MODE_MAIL);
        }else{
            changeRegisterMode(REGISTER_MODE_PHONE);
        }


        radioGroup.setItems(getResources().getStringArray(R.array.register_tab));
        radioGroup.setSelection(0);
        radioGroup.setOnItemSelectedListener(new HorizontalRadioGroup.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                changeRegisterMode(index);
            }
        });
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
                    finish();
                }else{
                }
            }

            @Override
            public void onFailed(int code, String msg) {

                showToast(msg);
            }
        });
    }


    private void resetPassword() {
        final String account = etAccount.getText().toString();
        String code = etCode.getText().toString();
        final String password = etPassword.getText().toString();
        if (password.equals("")) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
        } else if (account.equals("")) {
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
        } else if (code.equals("")) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
        } else {
            LoginUtil.resetPassword(account, code, password, new HttpUtils.HttpUtilsListner() {
                @Override
                public void onSuccess(String content) {
                    Toast.makeText(getApplicationContext(), "重置密码成功", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    mAccount.setAccount(account,password);
                    finish();
                }

                @Override
                public void onFailed(int code, String msg) {
                    Toast.makeText(getApplicationContext(), "重置密码失败"+msg, Toast.LENGTH_SHORT).show();
                    showToast(msg);
                }
            });
        }

    }


}
