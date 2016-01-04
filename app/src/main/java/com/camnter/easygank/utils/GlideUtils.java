package com.camnter.easygank.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.camnter.easygank.R;
import com.orhanobut.logger.Logger;

/**
 * Description：GlideUtils
 * Created by：CaMnter
 * Time：2016-01-04 22:19
 */
public class GlideUtils {

    private static final String TAG = "GlideUtils";


    /**
     * glide加载图片
     *
     * @param view view
     * @param url  url
     */
    public static void display(ImageView view, String url) {
        display(view, url, R.mipmap.img_default_gray);
    }


    /**
     * glide加载图片
     *
     * @param view         view
     * @param url          url
     * @param defaultImage defaultImage
     */
    private static void display(final ImageView view, String url, @DrawableRes int defaultImage) {
        // 不能崩
        if (view == null) {
            Logger.e("GlideUtils -> display -> imageView is null");
            return;
        }
        Context context = view.getContext();
        // View你还活着吗？
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        try {
            Glide.with(context)
                    .load(url)
                    .placeholder(defaultImage)
                    .crossFade()
                    .centerCrop()
                    .into(view)
                    .getSize(new SizeReadyCallback() {
                        @Override
                        public void onSizeReady(int width, int height) {
                            if (!view.isShown()) {
                                view.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
