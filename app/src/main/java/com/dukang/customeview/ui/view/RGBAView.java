package com.dukang.customeview.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.dukang.customeview.R;

/**
 * @Description :图片颜色通道
 * @Author : wdk
 * @CretaTime : 2019/2/1 14:26
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/2/1 14:26
 * @LastCheckBy :wdk
 */
public class RGBAView extends View {
    private Paint paint;
    private Bitmap bitmap;


    public RGBAView(Context context) {
        this(context, null);
    }

    public RGBAView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_girl_2);
        paint = new Paint();

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        ColorMatrix colorMatrix = new ColorMatrix(new float[]{
                0, 0, 0, 0, 100,
                0, 1, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 1, 0,
        });
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, null, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), paint);
    }
}
