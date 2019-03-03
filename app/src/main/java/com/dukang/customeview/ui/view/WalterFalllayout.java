package com.dukang.customeview.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/3/2 10:10
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/3/2 10:10
 * @LastCheckBy :wdk
 */
public class WalterFalllayout extends ViewGroup {

    private int columns = 3;
    private int wSpace = 20;
    private int hSpace = 20;
    private int childWdith = 0;
    private int[] columnsHeight;


    public WalterFalllayout(Context context) {
        this(context, null);
    }

    public WalterFalllayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WalterFalllayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new WalterLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new WalterLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new WalterLayoutParams(getContext(), attrs);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof WalterLayoutParams;
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        columnsHeight = new int[columns];
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取控件的宽度
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);

        //测量子控件,viewGroup自带的方法
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        //计算每列的宽度
        childWdith = (sizeWidth - (columns - 1)) / columns;


        //计算宽度
        int wrapWidth;
        int childCount = getChildCount();
        if (childCount < columns) {
            wrapWidth = childWdith * childCount + (childCount - 1) * wSpace;
        } else {
            wrapWidth = sizeWidth;
        }


        //计算高度
        clearTop();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            int childHeight = childWdith * child.getMeasuredHeight() / child.getMeasuredWidth();


            int minColumn = getMinColumn();
            int mLeft = minColumn * (childWdith + wSpace);
            int mTop = columnsHeight[minColumn];
            int mRight = mLeft + childWdith;
            int mBottom = mTop + childHeight;
            columnsHeight[minColumn] += childHeight + hSpace;

            WalterLayoutParams walterLayoutParams = (WalterLayoutParams) child.getLayoutParams();
            walterLayoutParams.left = mLeft;
            walterLayoutParams.top = mTop;
            walterLayoutParams.right = mRight;
            walterLayoutParams.bottom = mBottom;
            child.setLayoutParams(walterLayoutParams);
        }

        int wrapHeight = columnsHeight[getMaxColumn()];
        setMeasuredDimension(widthMode == MeasureSpec.AT_MOST ? wrapWidth : sizeWidth, wrapHeight);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();

//        clearTop();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

//            int childHeight = childWdith * child.getMeasuredHeight() / child.getMeasuredWidth();
//            int minCloumn = getMinColumn();
//
//            int mLeft = minCloumn * (childWdith + wSpace);
//            int mRight = mLeft + childWdith;
//            int mTop = columnsHeight[minCloumn];
//            int mBottom = mTop + childHeight;
//            columnsHeight[minCloumn] = mBottom + hSpace;
            WalterLayoutParams params = (WalterLayoutParams) child.getLayoutParams();

            child.layout(params.left, params.top, params.right, params.bottom);

        }

    }


    /**
     * 重置集合的值
     */
    private void clearTop() {
        for (int i = 0; i < columnsHeight.length; i++) {
            columnsHeight[i] = 0;
        }
    }

    /**
     * 获取高度最低的列坐标
     *
     * @return 列坐标
     */
    private int getMinColumn() {
        int minColumn = 0;

        for (int i = 0; i < columnsHeight.length; i++) {
            if (columnsHeight[i] < columnsHeight[minColumn]) {
                minColumn = i;
            }
        }

        return minColumn;
    }

    /**
     * 高度最高的列
     *
     * @return 列坐标
     */
    private int getMaxColumn() {
        int maxColumn = 0;

        for (int i = 0; i < columnsHeight.length; i++) {
            if (columnsHeight[i] > columnsHeight[maxColumn]) {
                maxColumn = i;
            }
        }

        return maxColumn;
    }
}
