package com.dukang.customeview.common.utils;

import android.util.Log;

import com.dukang.customeview.BuildConfig;

/**
 * @Description :打日志的工具类
 * @Author : wdk
 * @CretaTime : 2019/1/21 16:13
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/1/21 16:13
 * @LastCheckBy :wdk
 */
public class LogUtil {

    public static void loge(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void loge(String msg) {
        if (BuildConfig.DEBUG) {
            LogUtil.loge("loge:", "日志：" + msg);
        }
    }

}
