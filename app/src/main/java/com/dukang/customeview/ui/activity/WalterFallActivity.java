package com.dukang.customeview.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.dukang.customeview.R;
import com.dukang.customeview.base.BaseActivity;
import com.dukang.customeview.ui.view.WalterFalllayout;

import java.util.Random;

/**
 * @Description :瀑布流
 * @Author : wdk
 * @CretaTime : 2019/3/2 16:36
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/3/2 16:36
 * @LastCheckBy :wdk
 */
public class WalterFallActivity extends BaseActivity {

    private Button btn_add_photo;
    private WalterFalllayout walterFalllayout;

    @Override
    public int getLayout() {
        return R.layout.activity_walter_fall;
    }

    @Override
    public void initView() {
        btn_add_photo = findViewById(R.id.btn_add_photo);
        walterFalllayout = findViewById(R.id.walterFallLayout);
    }

    @Override
    public void initListener() {
        btn_add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView(walterFalllayout);
            }
        });
    }

    @Override
    public void initData() {

    }

    int IMG_COUNT = 3;
    int[] imgs = new int[]{R.mipmap.ic_girl_1, R.mipmap.ic_girl_2, R.mipmap.ic_girl_3, R.mipmap.ic_girl_4, R.mipmap.ic_girl_5,
            R.mipmap.ic_girl_6, R.mipmap.ic_girl_7, R.mipmap.ic_girl_8, R.mipmap.ic_girl_9};

    public void addView(WalterFalllayout waterfallLayout) {
        Random random = new Random();
        Integer num = Math.abs(random.nextInt());
        WalterFalllayout.LayoutParams layoutParams = new WalterFalllayout.LayoutParams(WalterFalllayout.LayoutParams.WRAP_CONTENT,
                WalterFalllayout.LayoutParams.WRAP_CONTENT);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(imgs[num % imgs.length]);
//        if (num % IMG_COUNT == 0) {
//            imageView.setImageResource(R.mipmap.ic_girl_1);
//        } else if (num % IMG_COUNT == 1) {
//            imageView.setImageResource(R.mipmap.ic_girl_2);
//        } else if (num % IMG_COUNT == 2) {
//            imageView.setImageResource(R.mipmap.ic_girl_3);
//        }
//        else if (num % IMG_COUNT == 3) {
//            imageView.setImageResource(R.drawable.pic_4);
//        } else if (num % IMG_COUNT == 4) {
//            imageView.setImageResource(R.drawable.pic_5);
//        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        waterfallLayout.addView(imageView, layoutParams);

//        waterfallLayout.setOnItemClickListener(new com.harvic.BlogWaterfallLayout.WaterfallLayout.OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int index) {
//                Toast.makeText(MyActivity.this, "item=" + index, Toast.LENGTH_SHORT).show();
//            }
//        });
    }


}
