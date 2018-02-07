package coder.prettygirls.app;

import coder.prettygirls.util.FileUtil;

/**
 * Created by coder on 2016/7/11.
 * 保存项目中用到的常量
 */
public final class Constants {

    private Constants() {
        // no instance
    }

    public static final String dir = FileUtil.getDiskCacheDir(MyApplication.getIntstance())
            + "/girls";
}
