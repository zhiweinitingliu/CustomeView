package com.dukang.customeview.ui.activity;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.dukang.customeview.R;
import com.dukang.customeview.base.BaseActivity;

/**
 * @Description :xml中动画的使用
 * @Author : wdk
 * @CretaTime : 2019/1/20 11:03
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/1/20 11:03
 * @LastCheckBy :wdk
 */
public class XmlAnimActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_control_img;
    private Button btn_alpha;
    private Button btn_scale;
    private Button btn_rotate;
    private Button btn_translate;
    private Button btn_set;
    private Button btn_reset;
    private Button btn_java_complete;

    private Animation scaleAnimation;
    private Animation alphaAnimation;
    private Animation rotateAnimation;
    private Animation translateAnimation;
    private Animation setAnimation;

    private boolean isJavaComplete = false;

    @Override
    public int getLayout() {
        return R.layout.activity_xml_anim;
    }

    @Override
    public void initView() {
        iv_control_img = findViewById(R.id.iv_control_img);
        btn_alpha = findViewById(R.id.btn_alpha);
        btn_scale = findViewById(R.id.btn_scale);
        btn_rotate = findViewById(R.id.btn_rotate);
        btn_translate = findViewById(R.id.btn_translate);
        btn_set = findViewById(R.id.btn_set);
        btn_reset = findViewById(R.id.btn_reset);
        btn_java_complete = findViewById(R.id.btn_java_complete);

        btn_alpha.setOnClickListener(this);
        btn_scale.setOnClickListener(this);
        btn_rotate.setOnClickListener(this);
        btn_translate.setOnClickListener(this);
        btn_set.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
        btn_java_complete.setOnClickListener(this);

        //加载布局文件
        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_xml_scale);
        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_xml_alpha);
        rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_xml_rotate);
        translateAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_xml_translate);
        setAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_xml_set);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_alpha:
                //透明度
                iv_control_img.startAnimation(alphaAnimation);
                break;
            case R.id.btn_scale:
                //缩放
                iv_control_img.startAnimation(scaleAnimation);
                break;
            case R.id.btn_rotate:
                //旋转
                iv_control_img.startAnimation(rotateAnimation);
                break;
            case R.id.btn_translate:
                //平移
                iv_control_img.startAnimation(translateAnimation);
                break;
            case R.id.btn_set:
                //动画集合
                iv_control_img.startAnimation(setAnimation);
                break;
            case R.id.btn_reset:
                //清除动画
                iv_control_img.clearAnimation();
                break;
            case R.id.btn_java_complete:
                if (isJavaComplete) {
                    //加载布局文件
                    scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_xml_scale);
                    alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_xml_alpha);
                    rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_xml_rotate);
                    translateAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_xml_translate);
                    setAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_xml_set);
                    isJavaComplete = false;
                    btn_java_complete.setText("java代码实现");
                } else {
                    //动画代码实现
                    scaleAnimation = new ScaleAnimation(1, 3, 1, 3, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0);
                    scaleAnimation.setFillAfter(true);
                    scaleAnimation.setDuration(1500);

                    alphaAnimation = new AlphaAnimation(0, 1);
                    alphaAnimation.setDuration(3000);
                    alphaAnimation.setFillAfter(true);

                    rotateAnimation = new RotateAnimation(0, -330, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
                    rotateAnimation.setDuration(1500);
                    rotateAnimation.setFillAfter(true);

                    translateAnimation = new TranslateAnimation(0, 100, 0, 100);
                    translateAnimation.setDuration(1500);
                    translateAnimation.setFillAfter(true);

                    setAnimation = new AnimationSet(true);
                    ((AnimationSet) setAnimation).addAnimation(scaleAnimation);
                    ((AnimationSet) setAnimation).addAnimation(alphaAnimation);
                    ((AnimationSet) setAnimation).addAnimation(rotateAnimation);
                    ((AnimationSet) setAnimation).addAnimation(translateAnimation);
                    setAnimation.setFillAfter(true);

                    isJavaComplete = true;
                    btn_java_complete.setText("xml布局文件实现");
                }
                break;
        }
    }
}
