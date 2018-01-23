package coder.prettygirls.app;

import coder.prettygirls.util.LogUtil;
import rx.functions.Action1;

/**
 * used for Rx network error handling
 * Created by coder on 2018/1/23.
 */

public final class RxUtils {

    private RxUtils() {
        // no instance
    }

    public static final Action1<Throwable> NetErrorProcessor = throwable -> {
        //log it
        LogUtil.e(throwable.getMessage(), "RxUtils.NetErrorProcessor");
        throwable.printStackTrace();
    };

    public static final Action1<Throwable> IgnoreErrorProcessor = throwable -> {
        // ignore it
        LogUtil.e(throwable.getMessage(), "RxUtils.IgnoreErrorProcessor");
    };

    public static <T> Action1<T> idleAction() {
        return IdleAction;
    }

    private static final Action1 IdleAction = t -> {
        // do nothing
    };
}
