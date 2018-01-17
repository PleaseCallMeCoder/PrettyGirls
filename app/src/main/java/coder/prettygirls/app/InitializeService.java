package coder.prettygirls.app;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * 这个服务用来初始化一些异步初始化不影响工作的SDK，缩短冷启动时间
 * Created by coder on 2018/01/17.
 */

public class InitializeService extends IntentService {

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        context.startService(intent);
    }

    public InitializeService() {
        super("InitializeService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            //todo delay init
        }
    }
}