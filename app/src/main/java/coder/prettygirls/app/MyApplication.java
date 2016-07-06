package coder.prettygirls.app;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import coder.prettygirls.util.LogUtil;
import coder.prettygirls.util.ToastUtil;
import okhttp3.OkHttpClient;


/**
 * Created by oracleen on 2016/6/28.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //配置是否显示log
        LogUtil.isDebug = true;

        //配置时候显示toast
        ToastUtil.isShow = true;
    }

    public static OkHttpClient defaultOkHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();
        return client;
    }
}
