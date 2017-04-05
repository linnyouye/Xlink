package com.example.andy.connectutil.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;


import com.example.andy.connectutil.R;
import com.example.andy.connectutil.SharePrefrence.Account;
import com.example.andy.connectutil.XlinkConnect;
import com.example.andy.connectutil.entity.Net.HttpUtils;
import com.example.andy.connectutil.entity.Net.LoginUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.xlink.wifi.sdk.XlinkAgent;


public class RegisterAndLoginActivity extends Activity {

    private static final boolean D = true;
    private static final String TAG = "lyy-RegisterAndLogin";

    private static final int HD_COUNT = 1;

    private static final int REGISTER_MODE_PHONE=0;
    private static final int REGISTER_MODE_MAIL=1;

    private static final int RQ_RESET_PASSWORD=1;

    @Bind(R.id.radio_group)
    HorizontalRadioGroup mRadioGroup;
    @Bind(R.id.launcher_view)
    View launchView;

    private Account mAccount;

    private View layoutRegister;
    private View layoutLogin;

    private EditText rEtAccount;
    private EditText rEtCode;
    private EditText rEtPassword;
    private EditText rEtUserName;
    private Button btnRegister;
    private Button btnGetCode;
    private Button btnBack;
    private View authCodeItem;

    private EditText lEtAccount;
    private EditText lEtPassword;
    private Button btnLogin;
    private Button btnToRegister;
    private Button btnResetPassword;

    private String userAccount;
    private String userPassword;
    private String authCode;



    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HD_COUNT:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_and_login);
        ButterKnife.bind(this);

        layoutRegister=findViewById(R.id.layout_register);
        layoutLogin=findViewById(R.id.layout_login);

        rEtAccount=(EditText)layoutRegister.findViewById(R.id.et_account);
        rEtCode=(EditText)layoutRegister.findViewById(R.id.et_code);
        rEtPassword=(EditText)layoutRegister.findViewById(R.id.et_password);
        rEtUserName=(EditText)layoutRegister.findViewById(R.id.et_user_name);
        btnRegister=(Button)layoutRegister.findViewById(R.id.btn_register);
        btnGetCode=(Button)layoutRegister.findViewById(R.id.btn_get_code);
        btnBack=(Button)layoutRegister.findViewById(R.id.btnBack);
        authCodeItem=layoutRegister.findViewById(R.id.auth_code_item);

        lEtAccount=(EditText)layoutLogin.findViewById(R.id.et_account);
        lEtPassword=(EditText)layoutLogin.findViewById(R.id.et_password);
        btnLogin=(Button)layoutLogin.findViewById(R.id.btn_login);
        btnToRegister=(Button)layoutLogin.findViewById(R.id.to_register);
        btnResetPassword=(Button)layoutLogin.findViewById(R.id.btn_reset_password);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showProgressDialog();
                if(mRadioGroup.getSelection()==REGISTER_MODE_MAIL){
                    registerByMail();
                }else{
                    registerByPhone();
                    //verifyCode();
                }
            }
        });

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivityForResult(ResetPasswordActivity.class,RQ_RESET_PASSWORD);
            }
        });

        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAuthCode();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showProgressDialog();
                login();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutRegister.setVisibility(View.GONE);
            }
        });

        btnToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutRegister.setVisibility(View.VISIBLE);
            }
        });



        changeRegisterMode(REGISTER_MODE_PHONE);
        layoutRegister.setVisibility(View.INVISIBLE);
        mRadioGroup.setItems(getResources().getStringArray(R.array.register_tab));
        mRadioGroup.setSelection(0);
        mRadioGroup.setOnItemSelectedListener(new HorizontalRadioGroup.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                changeRegisterMode(index);
            }
        });


        mAccount=new Account(this);
        final String user=mAccount.getUser();
        final String password=mAccount.getPassword();
        lEtAccount.setText(user);
        lEtPassword.setText(password);

        launchView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!TextUtils.isEmpty(user)&&!TextUtils.isEmpty(password)){
                    login();
                }else{
                    hideLauncherView();
                }
            }
        },2000);

    }

    private void changeRegisterMode(int mode){
        switch(mode){
            case REGISTER_MODE_MAIL:
                rEtAccount.setHint(R.string.hint_mail);
                authCodeItem.setVisibility(View.GONE);

                break;
            case REGISTER_MODE_PHONE:
                rEtAccount.setHint(R.string.hint_phone);
                authCodeItem.setVisibility(View.VISIBLE);

                break;
        }
    }



    private void getAuthCode(){
        String phone=rEtAccount.getText().toString();

        //showProgressDialog();
        LoginUtil.getAuthCode(phone, new HttpUtils.HttpUtilsListner() {
            @Override
            public void onSuccess(String content) {
               // cancelProgressDialog();
              //  showToast(R.string.get_auth_code_success);

            }

            @Override
            public void onFailed(int code, String msg) {
               // cancelProgressDialog();
              //  showToast(msg);
            }
        });
    }


    private void registerByPhone(){
        final String phone=rEtAccount.getText().toString();
        final String password=rEtPassword.getText().toString();
        String userName=rEtUserName.getText().toString();
        String code=rEtCode.getText().toString();
        LoginUtil.register(phone, password, code, userName, new HttpUtils.HttpUtilsListner() {
            @Override
            public void onSuccess(String content) {
                Log.i(TAG, "onSuccess: "+content);
                lEtAccount.setText(phone);
                lEtPassword.setText(password);
                login();
            }

            @Override
            public void onFailed(int code, String msg) {
                //cancelProgressDialog();
               // showToast(msg);
            }
        });
    }

    private void registerByMail(){
        final String mail=rEtAccount.getText().toString();
        final String password=rEtPassword.getText().toString();
        String userName=rEtUserName.getText().toString();

       // showProgressDialog();

        LoginUtil.registerByMail(mail, password, userName, new HttpUtils.HttpUtilsListner()  {
            @Override
            public void onSuccess(String content) {
              //  cancelProgressDialog();
                lEtAccount.setText(mail);
                lEtPassword.setText(password);
                layoutRegister.setVisibility(View.GONE);
              //  showToast(R.string.register_by_mail_suceess);
            }

            @Override
            public void onFailed(int code, String msg) {
               // cancelProgressDialog();
               // showToast(msg);
            }
        });
    }

    private void login(){
        final String account=lEtAccount.getText().toString();
        final String password=lEtPassword.getText().toString();
        LoginUtil.login(account, password, new HttpUtils.HttpUtilsListner()  {
            @Override
            public void onSuccess(String content) {
             //   cancelProgressDialog();

                mAccount.setAccount(account,password);

                XlinkConnect.getLoginResult(content);
                XlinkConnect.init(getApplicationContext());
                Intent intent=new Intent(RegisterAndLoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailed(int code, String msg) {
               // cancelProgressDialog();
               // showToast(msg);
                if(launchView.getVisibility()== View.VISIBLE){
                    hideLauncherView();
                }
            }
        });
    }

    private void hideLauncherView(){
        Animation fadeOutAnim= AnimationUtils.loadAnimation(this,R.anim.fade_out);
        fadeOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                launchView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        launchView.startAnimation(fadeOutAnim);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!=RESULT_OK){

        }
    }
}
