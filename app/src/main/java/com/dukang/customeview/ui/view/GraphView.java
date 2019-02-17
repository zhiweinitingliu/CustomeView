package com.dukang.customeview.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @Description :基础的图形绘制
 * @Author : wdk
 * @CretaTime : 2019/1/26 17:17
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/1/26 17:17
 * @LastCheckBy :wdk
 */
public class GraphView extends View {

    private Paint paintFill;
    private Paint paintStoken;


    public GraphView(Context context) {
        this(context, null);
    }

    public GraphView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paintFill = new Paint();
        paintFill.setStyle(Paint.Style.FILL);
        paintFill.setColor(Color.RED);

        paintStoken = new Paint();
        paintStoken.setColor(Color.BLUE);
        paintStoken.setStyle(Paint.Style.STROKE);
        paintStoken.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawCircle(100, 100, 100, paintFill);
//        canvas.drawCircle(220, 100, 100, paintStoken);
//
//        Path path = new Path();
//        path.moveTo(400, 400);
//        path.quadTo(500, 300, 600, 400);
//        path.quadTo(700, 500, 800, 400);
//        canvas.drawPath(path, paintStoken);
        canvas.drawPath(mPath, paintStoken);
    }

    private Path mPath = new Path();
    private float mPreX, mPreY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                mPreX = event.getX();
                mPreY = event.getY();

                return true;
            case MotionEvent.ACTION_MOVE:
//                mPath.lineTo(event.getX(), event.getY());
                float endX = (mPreX + event.getX()) / 2;
                float endY = (mPreY + event.getY()) / 2;
                mPath.quadTo(mPreX, mPreY, endX, endY);
                mPreX = event.getX();
                mPreY = event.getY();
                postInvalidate();
                return true;
            default:
                break;

        }
        return super.onTouchEvent(event);
    }

    public void reset() {
        mPath.reset();
        invalidate();
    }


}
