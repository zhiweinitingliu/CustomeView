package com.dukang.customeview.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dukang.customeview.R;

/**
 * @Description :qq拖拽红点
 * @Author : wdk
 * @CretaTime : 2019/2/21 10:35
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/2/21 10:35
 * @LastCheckBy :wdk
 */
public class QQRedPointView extends FrameLayout {

    private PointF startPointF, curPointF;
    private float DEFAULT_RADIUS=20;
    private float mRadius = DEFAULT_RADIUS;
    private Paint mPaint;
    private Path mPath;
    private boolean mTouch = false;

    private TextView mTipTextView;

    public QQRedPointView(Context context) {
        this(context, null);
    }

    public QQRedPointView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQRedPointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        startPointF = new PointF(100, 100);
        curPointF = new PointF();
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPath = new Path();

        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mTipTextView = new TextView(context);
        mTipTextView.setLayoutParams(layoutParams);
        mTipTextView.setPadding(10, 10, 10, 10);
        mTipTextView.setBackgroundResource(R.drawable.tv_bg);
        mTipTextView.setTextColor(Color.GREEN);
        mTipTextView.setText("99+");
        MarginLayoutParams marginLayoutParams= (MarginLayoutParams) mTipTextView.getLayoutParams();
        addView(mTipTextView);

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(new RectF(0, 0, getWidth(), getHeight()), mPaint, Canvas.ALL_SAVE_FLAG);

        //第一阶段
//        canvas.drawCircle(startPointF.x, startPointF.y, mRadius, mPaint);
//        if (mTouch) {
//            calculatePath();
//            canvas.drawCircle(curPointF.x, curPointF.y, mRadius, mPaint);
//            canvas.drawPath(mPath, mPaint);
//        }

        //第二阶段
        if (mTouch) {
            calculatePath();
            canvas.drawCircle(startPointF.x, startPointF.y, mRadius, mPaint);
            canvas.drawCircle(curPointF.x, curPointF.y, mRadius, mPaint);
            canvas.drawPath(mPath, mPaint);
            mTipTextView.setX(curPointF.x - mTipTextView.getWidth() / 2);
            mTipTextView.setY(curPointF.y - mTipTextView.getHeight() / 2);

        } else {
            mTipTextView.setX(startPointF.x - mTipTextView.getWidth() / 2);
            mTipTextView.setY(startPointF.y - mTipTextView.getHeight() / 2);
        }

        canvas.restore();

        super.dispatchDraw(canvas);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mTouch = true;
//                break;
//
////            case MotionEvent.ACTION_MOVE:
////
////                break;
//
//            case MotionEvent.ACTION_POINTER_UP:
//                mTouch = false;
////                break;
//        }
//        curPointF.set(event.getX(), event.getY());
//        postInvalidate();
//        return true;
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //第一阶段
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN: {
//                mTouch = true;
//            }
//            break;
//            case MotionEvent.ACTION_UP: {
//                mTouch = false;
//            }
//        }
//        curPointF.set(event.getX(), event.getY());
//        postInvalidate();
//        return true;

        //第二阶段
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                RectF rectF = new RectF();
                int[] location = new int[2];
                mTipTextView.getLocationOnScreen(location);
                rectF.left = location[0];
                rectF.top = location[1];
                rectF.right = location[0] + mTipTextView.getWidth();
                rectF.bottom = location[1] + mTipTextView.getHeight();

                if (rectF.contains((int) event.getRawX(), (int) event.getRawY())) {
                    mTouch = true;
                }


            }
            break;
            case MotionEvent.ACTION_UP: {
                mTouch = false;
            }
        }
        curPointF.set(event.getX(), event.getY());
        postInvalidate();
        return true;
    }


    private void calculatePath() {
        float x = curPointF.x;
        float y = curPointF.y;
        float startx = startPointF.x;
        float starty = startPointF.y;

        float dx = x - startx;
        float dy = y - starty;

        double a = Math.atan(dy / dx);

        //算出P0到圆中心点的x、y距离
        float offsetX = (float) (mRadius * Math.sin(a));
        float offsetY = (float) (mRadius * Math.cos(a));

        //获取四个点的坐标
        float x1 = x + offsetX;
        float y1 = y - offsetY;

        float x2 = startx + offsetX;
        float y2 = starty - offsetY;

        float x3 = x - offsetX;
        float y3 = y + offsetY;

        float x4 = startx - offsetX;
        float y4 = starty + offsetY;

        //获取贝塞尔曲线控制点
        float anchorX = (startx + x) / 2;
        float anchorY = (starty + y) / 2;

        //画path
        mPath.reset();

        mPath.moveTo(x1, y1);
        mPath.quadTo(anchorX, anchorY, x2, y2);
        mPath.lineTo(x4, y4);
        mPath.quadTo(anchorX, anchorY, x3, y3);
        mPath.lineTo(x1, y1);

        float distance = (float) Math.sqrt(Math.pow(y-starty, 2) + Math.pow(x-startx, 2));
        mRadius = DEFAULT_RADIUS - distance/15;
        if(mRadius<9){
            mRadius = 9;
        }
    }
}
