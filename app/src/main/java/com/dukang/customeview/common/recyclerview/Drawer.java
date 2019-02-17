package com.dukang.customeview.common.recyclerview;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/2/11 16:52
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/2/11 16:52
 * @LastCheckBy :wdk
 */
public class Drawer {
    private final Drawable mDivider;
    private final int mWidth;
    private final int mHeight;

    public Drawer(Drawable divider, int width, int height) {
        this.mDivider = divider;
        this.mWidth = width;
        this.mHeight = height;
    }

    /**
     * Draw the divider on the left side of the Item.
     */
    public void drawLeft(View view, Canvas c) {
        int left = view.getLeft() - mWidth;
        int top = view.getTop() - mHeight;
        int right = left + mWidth;
        int bottom = view.getBottom() + mHeight;
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);
    }

    /**
     * Draw the divider on the top side of the Item.
     */
    public void drawTop(View view, Canvas c) {
        int left = view.getLeft() - mWidth;
        int top = view.getTop() - mHeight;
        int right = view.getRight() + mWidth;
        int bottom = top + mHeight;
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);
    }

    /**
     * Draw the divider on the top side of the Item.
     */
    public void drawRight(View view, Canvas c) {
        int left = view.getRight();
        int top = view.getTop() - mHeight;
        int right = left + mWidth;
        int bottom = view.getBottom() + mHeight;
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);
    }

    /**
     * Draw the divider on the top side of the Item.
     */
    public void drawBottom(View view, Canvas c) {
        int left = view.getLeft() - mWidth;
        int top = view.getBottom();
        int right = view.getRight() + mWidth;
        int bottom = top + mHeight;
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);
    }
}
