package com.dukang.customeview.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/3/2 17:47
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/3/2 17:47
 * @LastCheckBy :wdk
 */
public class WalterLayoutParams extends ViewGroup.LayoutParams {

    public int left = 0;
    public int top = 0;
    public int right = 0;
    public int bottom = 0;

    public WalterLayoutParams(Context c, AttributeSet attrs) {
        super(c, attrs);
    }

    public WalterLayoutParams(int width, int height) {
        super(width, height);
    }

    public WalterLayoutParams(ViewGroup.LayoutParams source) {
        super(source);
    }


}
