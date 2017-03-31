package com.example.andy.connectutil.SharePrefrence;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by andy on 2017/3/30.
 */

public class Account {
    private static final String FILE_NAME = "account";

    private static final String KEY_USER="user";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NICKNAME="nickname";

    private static final String KEY_DATA_DEVICE="data_device";

    private static final String KEY_LOGO_PATH="logo_path";

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    public Account(Context context) {
        mPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }


    public void setAccount(String user,String password){
        mEditor.putString(KEY_USER,user);
        mEditor.putString(KEY_PASSWORD,password);
        mEditor.commit();
    }

    public String getUser(){
        return mPreferences.getString(KEY_USER,"");
    }
    public String getPassword(){
        return mPreferences.getString(KEY_PASSWORD,"");
    }


    public void setDataDevice(String device){
        mEditor.putString(KEY_DATA_DEVICE,device);
        mEditor.commit();
    }
    public String getDataDevice(){
        return mPreferences.getString(KEY_DATA_DEVICE,"");
    }


    public void setLogoPath(String logoPath){
        mEditor.putString(KEY_LOGO_PATH,logoPath);
        mEditor.commit();
    }
    public String getLogoPath(){
        return mPreferences.getString(KEY_LOGO_PATH,"");
    }



    public void setNickname(String name){
        mEditor.putString(KEY_NICKNAME,name);
        mEditor.commit();
    }
    public String getNickname(){
        return mPreferences.getString(KEY_NICKNAME,"");
    }
}
