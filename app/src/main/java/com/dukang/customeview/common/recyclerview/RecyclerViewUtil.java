package com.dukang.customeview.common.recyclerview;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @Description :封装了RecyclerView 使用的一些通用方法
 * @Author : wdk
 * @CretaTime : 2019/1/19 16:55
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/1/19 16:55
 * @LastCheckBy :wdk
 */
public class RecyclerViewUtil {

    public static void initLinearLayoutManager(RecyclerView recyclerView, Activity activity) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

}
