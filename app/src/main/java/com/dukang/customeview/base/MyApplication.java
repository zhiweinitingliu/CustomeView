package com.dukang.customeview.base;

import android.app.Application;

import com.alibaba.fastjson.parser.ParserConfig;
import com.dukang.customeview.common.fastjson.FJsonMapDeserializer;
import com.dukang.customeview.common.fastjson.FJsonObjectDeserializer;

import java.util.Map;

/**
 * @Description :application
 * @Author : wdk
 * @CretaTime : 2019/1/19 14:45
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/1/19 14:45
 * @LastCheckBy :wdk
 */
public class MyApplication extends Application {

    public static MyApplication myApplication;

    public static MyApplication getApplication() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        init();
    }

    private void init() {
        fastJsonConfg();
    }

    private void fastJsonConfg() {
        //设置解析某字段异常时忽略
        ParserConfig.getGlobalInstance()
                .putDeserializer(Map.class, new FJsonMapDeserializer());
        ParserConfig.getGlobalInstance()
                .putDeserializer(Object.class, new FJsonObjectDeserializer());
    }
}
