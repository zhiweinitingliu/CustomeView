package com.dukang.customeview.ui.adapter;

import android.content.Context;

import com.dukang.customeview.R;
import com.dukang.customeview.common.recyclerview.BaseRecyclerViewAdapter;
import com.dukang.customeview.common.recyclerview.RecyclerHolder;
import com.dukang.customeview.ui.bean.CustomeViewBean;

/**
 * @Description : 自定义列表 view名称的adapter
 * @Author : wdk
 * @CretaTime : 2019/1/19 17:25
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/1/19 17:25
 * @LastCheckBy :wdk
 */
public class ViewListAdapter extends BaseRecyclerViewAdapter<CustomeViewBean> {
    public ViewListAdapter(Context context) {
        super(context, R.layout.item_custome_view_list);
    }

    @Override
    public void convert(RecyclerHolder holder, CustomeViewBean customeViewBean, int position) {
        holder.setText(R.id.tv_view_name,customeViewBean.getName());
    }
}
