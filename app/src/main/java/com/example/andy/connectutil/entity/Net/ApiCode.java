package com.example.andy.connectutil.entity.Net;

/**
 * Created by Lin Youye on 2016/4/11.
 */
public class ApiCode {

    public static final int OK = 0;
    public static final int NET_ERROR = 50000;
    public static final int JSON_EXCEPTION = 50001;

    public static final int ACCESS_TOKEN_INVALID = 3000;

    public static String getErrorMsg(int code) {
        switch (code) {
            case OK:
                return "操作成功";
            case NET_ERROR:
                return "网络连接失败";
            case JSON_EXCEPTION:
                return "json解析异常";
            case ACCESS_TOKEN_INVALID:
                return "请重新登录";
            default:
                return "操作失败:" + code;
        }
    }

}
