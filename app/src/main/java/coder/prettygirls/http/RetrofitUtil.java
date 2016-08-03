package coder.prettygirls.http;

import coder.prettygirls.app.Constants;
import coder.prettygirls.app.MyApplication;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gaohailong on 2016/5/17.
 */
public class RetrofitUtil {

    private static volatile Retrofit instance;

    private RetrofitUtil() {
    }

    public static Retrofit getInstance() {
        if (instance == null) {
            synchronized (RetrofitUtil.class) {
                if (instance == null) {
                    instance = new Retrofit.Builder()
                            .baseUrl(Constants.GANHUO_API)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(MyApplication.defaultOkHttpClient())
                            .build();
                }
            }
        }
        return instance;
    }
}
