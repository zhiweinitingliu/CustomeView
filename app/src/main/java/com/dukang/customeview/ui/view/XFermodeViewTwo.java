package com.dukang.customeview.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class XFermodeViewTwo extends View {

    private int width = 400;
    private int height = 400;
    private Bitmap dstBmp;
    private Bitmap srcBmp;
    private Paint mPaint;

    public XFermodeViewTwo(Context context) {
        this(context, null);
    }

    public XFermodeViewTwo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XFermodeViewTwo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        dstBmp = makeDst(width,height);
        srcBmp = makeSrc(width,height);
        mPaint = new Paint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerID = canvas.saveLayer(0,0,width*2,height*2,mPaint,Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(srcBmp, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        canvas.drawBitmap(dstBmp, 0, 0, mPaint);
        mPaint.setXfermode(null);

        canvas.restoreToCount(layerID);
    }

    static Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFFFFCC44);
        c.drawOval(new RectF(0, 0, w, h), p);
        return bm;
    }

    static Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

        p.setColor(0xFF66AAFF);
        c.drawRect(0, 0,200,200, p);
        return bm;
    }


}
