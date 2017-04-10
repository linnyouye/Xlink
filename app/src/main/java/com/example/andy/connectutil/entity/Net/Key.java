package com.example.andy.connectutil.entity.Net;

/**
 * Created by andy on 2017/3/24.
 */

public class Key {
    public static final String Device_id="device_id";
    public static final String corp_id="corp_id";
    public static final String phone="phone";
    public static final String verifycode = "verifycode";//验证码
    public static final String password="password";
    public static final String email="email";
    public static final String nickname="nickname";
    public static final String create_date="create_date";
    public static final int  status=1;//1:正常可用状态   2:停用状态
    public static final int source=2; //1:Web  2:Android客户端  3:IOS客户端  4:微信  5:QQ  6:微博  10:其他XLink身份验证来源
    public static final String region_id="region_id";
    public static final boolean is_vaild=true;
    public static final boolean passwd_inited=true;
    public static final int age=0;
    public static final String setting_message="setting.message";
    public static final int setting_message_type=2;//1:设备警告  2:设备通知  3:系统广播（如系统推送）  4:p2p用户与用户（如好友分享）  5:家庭留言
    public static final boolean setting_message_receive=true;
    public static final String setting_message_inform="setting.message_inform";
    public static final int setting_message_inform_type=1;//1:提示音  2:震动


    public static final String ERROR="error";
    public static final String CODE="code";
    public static final String MSG="msg";

    public static final String CORP_ID="corp_id";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";
    public static final String NICKNAME = "nickname";
    public static final String VERIFY_CODE = "verifycode";
    public static final String PASSWORD = "password";
    public static final String NEW_PASSWORD = "new_password";
    public static final String SOURCE = "source";

    public static final int SOURCE_WEB=1;
    public static final int SOURCE_ANDROID=2;

    public static final String LOCAL_LANG = "local_lang";
    public static final String LANG_CN="zh-cn";

    public static final String AUTHORIZE="authorize";
    public static final String EXPIRE_IN="expire_in";
    public static final String USER_ID="user_id";
    public static final String REFRESH_TOKEN="refresh_token";
    public static final String ACCESS_TOKEN="access_token";

    public static final String PRODUCT_ID="product_id";
    public static final String MAC="mac";


    public static final String AQI="aqi";
    public static final String PM25="pm25";
    public static final String HUM="hum";
    public static final String TMP="tmp";
    public static final String IS_ONLINE="is_online";
}
