package com.dukang.customeview.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/1/19 16:29
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/1/19 16:29
 * @LastCheckBy :wdk
 */
public abstract class BaseActivity extends AppCompatActivity {

    public Activity activity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        activity = this;
        initView();
        initListener();
        initData();
    }

    public abstract int getLayout();

    public abstract void initView();

    public abstract void initListener();

    public abstract void initData();
}
