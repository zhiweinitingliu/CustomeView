package com.dukang.customeview.ui.common;

import com.dukang.customeview.ui.activity.GraphViewActivity;
import com.dukang.customeview.ui.activity.ValueAnimatorActivity;
import com.dukang.customeview.ui.activity.XFermodeActivity;
import com.dukang.customeview.ui.activity.XmlAnimActivity;

/**
 * @Description :动画列表判断、跳转的枚举
 * @Author : wdk
 * @CretaTime : 2019/1/20 11:16
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/1/20 11:16
 * @LastCheckBy :wdk
 */
public enum ActivityEnum {

    xmlAnimActivity("xml_anim", XmlAnimActivity.class),
    valueAnimatorActivity("value_animator", ValueAnimatorActivity.class),
    graphViewActivity("graph_view", GraphViewActivity.class),
    xFermodeActivity("paint_xfermode", XFermodeActivity.class),
    error("error", null);

    private String type;
    private Class clazz;

    ActivityEnum(String type, Class clazz) {
        this.type = type;
        this.clazz = clazz;
    }

    public String getType() {
        return type;
    }

    public Class getClazz() {
        return clazz;
    }

    public static ActivityEnum getActivityEnum(String type) {
        for (ActivityEnum functionEnum : ActivityEnum.values()) {
            if (functionEnum.type.equals(type)) {
                return functionEnum;
            }
        }
        return error;
    }

}
