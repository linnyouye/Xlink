package com.example.andy.connectutil.entity.Net;

/**
 * Created by andy on 2017/3/24.
 */

public class Url {

    private static final String XLINK_SERVER = "http://api2.xlink.cn";

    public static final String GET_WEATHER_INFO="http://www.x-exploring.com/index.php?m=HeFeng&c=Weather&a=index&city=%s";


    //获取验证码
    public static final String GET_AUTH_CODE = XLINK_SERVER + "/v2/user_register/verifycode";
    //校验验证码
    public static final String VERIFY_CODE = XLINK_SERVER + "/v2/user/verifycode/verify";
    //注册
    public static final String REGISTER = XLINK_SERVER + "/v2/user_register";
    //登录
    public static final String LOGIN = XLINK_SERVER + "/v2/user_auth";
    //忘记密码
    public static final String FORGET_PASSWORD=XLINK_SERVER+"/v2/user/password/forgot";
    //重置密码
    public static final String RESET_PASSWORD=XLINK_SERVER+"/v2/user/password/foundback";

    //获取用户公开信息
    public static final String GET_USER_INFO=XLINK_SERVER+"/v2/user/%s/open_info";

    //注册设备
    public static final String REGISTER_DEVICE= XLINK_SERVER +"/v2/user/%s/register_device";

    //获取设备列表
    public static final String GET_DEVICE_LIST= XLINK_SERVER +"/v2/user/%s/subscribe/devices";

    //获取设备地理位置
    public static final String GET_DEVICE_LOCATION= XLINK_SERVER +"/v2/product/%s/device/%d/geography";
}
