package com.dukang.customeview.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @Description :图片加载工具类
 * @Author : wdk
 * @CretaTime : 2019/1/18 11:19
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/1/18 11:19
 * @LastCheckBy :wdk
 */
public class LoadImage {

    public static void loadRemoteImg(Context context, ImageView view, String url) {
        Glide.with(context)
                .load(url)
                .into(view);
    }

    public static void loadRemoteGoodsImg(Context context, ImageView view, String url) {

    }

    public static void loadRemoteCircleImg(Context context, ImageView view, String url) {

    }

    public static void loadRemoteCircleImg2(Context context, ImageView view, String url, int defResourceId) {

    }
}
