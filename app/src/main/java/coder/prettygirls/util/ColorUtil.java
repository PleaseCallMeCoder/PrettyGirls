package coder.prettygirls.util;

import android.graphics.Color;

/**
 * 颜色处理工具类
 * Created by coder on 2016/7/5.
 */
public final class ColorUtil {

    private ColorUtil() {
        //not called
    }

    /**
     * 颜色加深处理
     *
     * @param RGBValues RGB的值
     */
    public static int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }

}
