package com.camnter.easygank.utils;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

/**
 * Description：ViewUtil
 * Created by：CaMnter
 * Time：2015-12-04 22:33
 */
public class ViewUtils {

    /**
     * Set view alpha
     * 设置透明度
     *
     * @param view  view
     * @param alpha alpha value
     */
    public static void setAlpha(View view, int alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            view.setAlpha(alpha / 255.0f);
        } else {
            Drawable drawable = view.getBackground();
            if (drawable != null) {
                drawable.setAlpha(alpha);
            }
        }
    }

    /**
     * 根据Android系统版本，调用版本API中的设置View背景的方法
     * According to the Android version, calls the Settings in the View background method of version API
     *
     * @param view     view
     * @param drawable drawable
     */
    public static void setViewBackgroundDrawable(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    /**
     * 获取View在当前窗口内的绝对坐标
     * obtain the absolute coordinates to obtain the View within the current window
     *
     * @param view view
     * @return int[] x y
     */
    public static int[] getLocationInWindow(View view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        return location;
    }

    /**
     * 获取View在整个屏幕内的绝对坐标
     * obtain access to View the absolute coordinates of within the entire screen
     *
     * @param view view
     * @return int[] x y
     */
    public static int[] getLocationOnScreen(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return location;
    }

}
