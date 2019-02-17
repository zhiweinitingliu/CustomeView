package com.dukang.customeview.common.recyclerview;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/2/11 16:54
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/2/11 16:54
 * @LastCheckBy :wdk
 */
class ColorDrawer extends Drawer {

    public ColorDrawer(int color, int width, int height) {
        super(new ColorDrawable(opaqueColor(color)), width, height);
    }

    /**
     * The target color is packaged in an opaque color.
     *
     * @param color color.
     * @return color.
     */
    @ColorInt
    public static int opaqueColor(@ColorInt int color) {
        int alpha = Color.alpha(color);
        if (alpha == 0) return color;
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(255, red, green, blue);
    }
}
