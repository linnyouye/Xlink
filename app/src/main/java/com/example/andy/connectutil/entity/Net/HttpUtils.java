package com.example.andy.connectutil.entity.Net;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by andy on 2017/3/24.
 */

public class HttpUtils {
    private static  final String TAG="HttpUtils";
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient myokhttp;
    private static Handler myHandler;

    public static void init()
    {
        if (myHandler == null) {
            myHandler = new Handler();
        }
        if (myokhttp == null) {
            myokhttp = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .build();
        }
    }
    public static interface HttpUtilsListner{
        void onSuccess(String content);
        void onFailed(int code, String msg);
    }
//发送成功的回调
    private static void listenerOnSuccess(final HttpUtilsListner listener, final String msg) {
        if (listener != null) {
            myHandler.post(new Runnable() {
                @Override
                public void run() {
                    listener.onSuccess(msg);
                    Log.i(TAG, "success: " + msg);
                }
            });
        }
    }


//发送失败的回调
    private static void listenerOnFailure(final HttpUtilsListner listener, final int code, String msg) {
        if (listener != null) {
            final String errMsg = TextUtils.isEmpty(msg) ? ApiCode.getErrorMsg(code) : msg;
            myHandler.post(new Runnable() {
                @Override
                public void run() {
                    listener.onFailed(code, errMsg);
                    Log.i(TAG, "failed:" + code + "," + errMsg);
                }
            });
        }
    }



    public static void get(String url, Map<String,String> content, final HttpUtilsListner listner)//content就是传过来的Json数据
    {
        init();
        Log.i(TAG, "Okhttp3 doget: url = " + url);
        Request.Builder builder = new Request.Builder();

        builder.url(url);

        if (content != null) {

            for (String key : content.keySet()) {
                builder.addHeader(key, content.get(key));
            }
        }
        builder.get();
        HandleCallback(builder.build(),listner);
    }

    public static void postJson(String url, String json, Map<String, String> header, HttpUtilsListner listener) {
        init();

        Log.i(TAG, "post_json: url = " + url);
        Log.i(TAG, "post_json: json = " + json);
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);

        Request.Builder builder = new Request.Builder();

        builder.url(url).post(body);

        if (header != null) {

            for (String key : header.keySet()) {
                builder.addHeader(key, header.get(key));
            }
        }
        HandleCallback(builder.build(),listener);
    }
    public static void putJson(String url, String json, Map<String, String> header, HttpUtilsListner listener)
    {
        Log.i(TAG, "put_json: url = " + url);
        Log.i(TAG, "put_json: json = " + json);
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);

        Request.Builder builder = new Request.Builder();

        builder.url(url).put(body);

        if (header != null) {
            for (String key : header.keySet()) {
                builder.addHeader(key, header.get(key));
            }
        }
        HandleCallback(builder.build(),listener);
    }

    private static void HandleCallback(final Request build, final HttpUtilsListner listener) {
        myokhttp.newCall(build).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                listenerOnFailure(listener, ApiCode.NET_ERROR, null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final int code = response.code();
                final String result = response.body().string();


                Log.i(TAG, "onResponse: " + code + ":" + result);


                if (code == 200) {
                    listenerOnSuccess(listener, result);

                } else {
                    int xlinkCode = 0;
                    String errorMsg = null;
                    try {
                        JSONObject obj = new JSONObject(result);
                        JSONObject errorObj = obj.getJSONObject(Key.ERROR);
                        xlinkCode = errorObj.getInt(Key.CODE);
                        errorMsg = errorObj.getString(Key.MSG);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (xlinkCode > 0) {
                        listenerOnFailure(listener, xlinkCode, errorMsg);
                    } else {
                        listenerOnFailure(listener, code, null);
                    }


                }
            }
        });
        /*            @Override
            public void onFailure(Call call, IOException e) {
                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "onFailure: Connect Error");
                        listner.OnFail();
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int code=response.code();
                String content=response.body().string();
                if(code==200)//请求成功Htttp status 为200
                {
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.d(TAG, "onResponse: Succees");
                            listner.OnSuccess();
                        }
                    });

                }else
                {
                    int XlinkCode=0;
                    String msg=null;
                    try {
                        JSONObject json=new JSONObject(content);
                        XlinkCode=json.getInt(Key.CODE);
                        msg=json.getString(Key.MSG);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if(XlinkCode>0)
                    {
                        final int finalXlinkCode = XlinkCode;
                        final String finalMsg = msg;
                        myHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.d(TAG, "run: ERRORCODE"+ finalXlinkCode +"  ERROEMSG:"+ finalMsg);
                                listner.OnFail();
                            }
                        });
                    }else
                    {
                        final String finalMsg1 = msg;
                        myHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.d(TAG, "run: ERRORCODE:NET_ERROR"+"MSG"+ finalMsg1);
                            }
                        });
                    }
                }
            }*/
    }
}
