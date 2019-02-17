package com.dukang.customeview.common.utils;

import android.widget.Toast;

import com.dukang.customeview.base.MyApplication;

/**
 * @Description :Toast util
 * @Author : wdk
 * @CretaTime : 2019/2/11 16:15
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/2/11 16:15
 * @LastCheckBy :wdk
 */
public class ToastUtil {

    public static void toast(String msg) {
        Toast.makeText(MyApplication.getApplication(), msg, Toast.LENGTH_SHORT).show();
    }

}
