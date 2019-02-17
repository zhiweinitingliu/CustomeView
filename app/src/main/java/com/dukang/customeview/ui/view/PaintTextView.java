package com.dukang.customeview.ui.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * @Description :Paint 方法使用
 * @Author : wdk
 * @CretaTime : 2019/1/30 10:39
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/1/30 10:39
 * @LastCheckBy :wdk
 */
public class PaintTextView extends View {

    Paint paint;

    public PaintTextView(Context context) {
        this(context, null);
    }

    public PaintTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    Path path = new Path();
    int sload = 25;
    int dash = 15;
    int off = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        path.moveTo(100, 100);
        path.lineTo(500, 100);
        path.lineTo(100, 800);
//        paint.setPathEffect(new CornerPathEffect(100));
        paint.setPathEffect(new DashPathEffect(new float[]{sload, dash}, off));
        canvas.drawPath(path, paint);
//        canvas.drawLine(100,100,500,100,paint);
    }

    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, sload + dash);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                off = value;
                postInvalidate();
            }
        });
        animator.start();
    }
}
