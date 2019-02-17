package com.dukang.customeview.ui.activity;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

import com.dukang.customeview.R;
import com.dukang.customeview.base.BaseActivity;
import com.dukang.customeview.common.utils.LogUtil;

/**
 * @Description :属性 动画
 * @Author : wdk
 * @CretaTime : 2019/1/21 14:54
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/1/21 14:54
 * @LastCheckBy :wdk
 */
public class ValueAnimatorActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_start_anim;
    private Button btn_remove_listener;
    private Button btn_add_listener;
    private Button btn_cancel_anim;
    private ImageView iv_control_img;
    private ValueAnimator valueAnimator;
    int ivControlImgX = 0;
    int ivControlImgY = 0;

    @Override
    public int getLayout() {
        return R.layout.activity_value_animator;
    }

    @Override
    public void initView() {
        btn_start_anim = findViewById(R.id.btn_start_anim);
        btn_remove_listener = findViewById(R.id.btn_remove_listener);
        btn_add_listener = findViewById(R.id.btn_add_listener);
        btn_cancel_anim = findViewById(R.id.btn_cancel_anim);
        iv_control_img = findViewById(R.id.iv_control_img);

        valueAnimator = ValueAnimator.ofInt(0, 400);
        valueAnimator.setDuration(3000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.addUpdateListener(animatorUpdateListener);
//
        ViewTreeObserver viewTreeObserver = iv_control_img.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                iv_control_img.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ivControlImgX = iv_control_img.getLeft();
                ivControlImgY = iv_control_img.getTop();
            }
        });
    }

    @Override
    public void initListener() {
        btn_start_anim.setOnClickListener(this);
        btn_remove_listener.setOnClickListener(this);
        btn_cancel_anim.setOnClickListener(this);
        btn_add_listener.setOnClickListener(this);
    }

    ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            int value = (int) animation.getAnimatedValue();
            iv_control_img.layout(ivControlImgX + value, ivControlImgY + value, ivControlImgX + value + iv_control_img.getWidth(), ivControlImgY + value + iv_control_img.getHeight());
            LogUtil.loge(value + "");
        }
    };

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_anim:
                //开始动画
                valueAnimator.start();
                break;
            case R.id.btn_remove_listener:
                //清除动画的监听
                valueAnimator.removeAllUpdateListeners();
                break;
            case R.id.btn_add_listener:
                //添加监听
                valueAnimator.removeAllUpdateListeners();
                valueAnimator.addUpdateListener(animatorUpdateListener);
                break;
            case R.id.btn_cancel_anim:
                //取消执行的动画
                valueAnimator.cancel();
                break;
        }
    }
}
