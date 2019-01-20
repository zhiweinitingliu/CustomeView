package com.dukang.customeview.common.utils;

import android.content.res.AssetManager;
import android.util.Log;

import com.dukang.customeview.base.MyApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Description : 通用的工具类
 * @Author : wdk
 * @CretaTime : 2019/1/19 14:43
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/1/19 14:43
 * @LastCheckBy :wdk
 */
public class Util {

    public static String getJson(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager =MyApplication.getApplication().getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
