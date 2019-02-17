package com.dukang.customeview.ui.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * @Description :波浪效果的view
 * @Author : wdk
 * @CretaTime : 2019/1/29 14:44
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/1/29 14:44
 * @LastCheckBy :wdk
 */
public class WaveView extends View {

    private Paint paint;
    private Path path = new Path();
    private int mItemLength = 700;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();

        int originY = 300;
        int halfLength = mItemLength / 2;
        path.moveTo(-mItemLength + dx, originY);
        for (int i = -mItemLength; i < getWidth() + mItemLength; i = i + mItemLength) {
            path.rQuadTo(halfLength / 2, -100, halfLength, 0);
            path.rQuadTo(halfLength / 2, 100, halfLength, 0);
        }

        path.lineTo(getWidth(), getHeight());
        path.lineTo(0, getHeight());
        path.close();
        canvas.drawPath(path, paint);

    }

    private int dx;

    public void startAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, mItemLength);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();
    }
}
