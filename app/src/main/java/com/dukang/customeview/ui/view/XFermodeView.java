package com.dukang.customeview.ui.view;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.dukang.customeview.R;

/**
 * @Description :view中的xFermode方法
 * @Author : wdk
 * @CretaTime : 2019/2/16 11:24
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/2/16 11:24
 * @LastCheckBy :wdk
 */
public class XFermodeView extends View {

    private Paint paint;
    private Bitmap bitmap_girl;
    private Bitmap bitmap;
    private Bitmap bitmapEmpty;
    private Path path;
    private Paint mBitmapPaint;

    private Bitmap dstBmp;
    private Bitmap srcBmp;

    int width = 400;
    int height = 400;

    private PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;

    public XFermodeView(Context context) {
        this(context, null);
    }

    public XFermodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XFermodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setPorterDuffMode(PorterDuff.Mode mode) {
        this.mode = mode;
        postInvalidate();
    }

    private void init() {
        paint = new Paint();
        bitmap_girl = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_girl_2);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_girl_1);
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        bitmapEmpty = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        path = new Path();
        mBitmapPaint = new Paint();
        mBitmapPaint.setColor(Color.RED);
        mBitmapPaint.setStyle(Paint.Style.STROKE);
        mBitmapPaint.setStrokeWidth(80);
        //禁用硬件加速器
//        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        srcBmp = makeSrc(400, 400);
        dstBmp = makeDst(400, 400);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap_girl, 0, 0, mBitmapPaint);
        //1
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        //2
//        canvas.drawBitmap(dstBmp, 0, 0, paint);
//        paint.setXfermode(new PorterDuffXfermode(mode));
//        canvas.drawBitmap(srcBmp, width / 2, height / 2, paint);
//        paint.setXfermode(null);
        Canvas canvas1 = new Canvas(bitmapEmpty);
        canvas1.drawPath(path, mBitmapPaint);
        canvas.drawBitmap(bitmapEmpty, 0, 0, mBitmapPaint);
        mBitmapPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(bitmap, 0, 0, mBitmapPaint);
        mBitmapPaint.setXfermode(null);
        //3
        canvas.restoreToCount(layerId);
    }

    float startX;
    float startY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(event.getX(), event.getY());
                startX = event.getX();
                startY = event.getY();
                //return true  很重要*************************
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = (startX + event.getX()) / 2;
                float endY = (startY + event.getY()) / 2;
                path.quadTo(startX, startY, endX, endY);
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }

    public Bitmap makeDst(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xFFFFCC44);
        canvas.drawOval(new RectF(0, 0, width, height), paint);
        return bitmap;
    }

    public Bitmap makeSrc(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xFF66AAFF);
        canvas.drawRect(new RectF(0, 0, width, height), paint);
        return bitmap;
    }
}
