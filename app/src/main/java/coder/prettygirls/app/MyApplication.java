package coder.prettygirls.app;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import coder.prettygirls.app.exception.LocalFileHandler;
import coder.prettygirls.util.LogUtil;
import coder.prettygirls.util.ToastUtil;
import okhttp3.OkHttpClient;


/**
 * Created by oracleen on 2016/6/28.
 */
public class MyApplication extends Application {

    private static MyApplication mApplication;
    public static String currentGirl = "http://ww2.sinaimg.cn/large/610dc034jw1f5k1k4azguj20u00u0421.jpg";

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;

        //配置是否显示log
        LogUtil.isDebug = true;

        //配置时候显示toast
        ToastUtil.isShow = true;

        //配置程序异常退出处理
        Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(this));
    }

    public static OkHttpClient defaultOkHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();
        return client;
    }

    public static MyApplication getIntstance() {
        return mApplication;
    }
}
