package com.dukang.customeview.common.utils;

import android.content.Context;
import android.content.Intent;

/**
 * @Description :activity跳转管理器
 * @Author : wdk
 * @CretaTime : 2019/1/20 11:25
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/1/20 11:25
 * @LastCheckBy :wdk
 */
public class IntentManager {

    public static void startActivity(Context context, Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }
}
