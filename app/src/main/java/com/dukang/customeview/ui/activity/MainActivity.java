package com.dukang.customeview.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dukang.customeview.R;
import com.dukang.customeview.base.BaseActivity;
import com.dukang.customeview.common.recyclerview.BaseRecyclerViewAdapter;
import com.dukang.customeview.common.recyclerview.ItemDivider;
import com.dukang.customeview.common.recyclerview.QuickItemDecoration;
import com.dukang.customeview.common.recyclerview.RecyclerViewUtil;
import com.dukang.customeview.common.utils.IntentManager;
import com.dukang.customeview.common.utils.JsonUtil;
import com.dukang.customeview.common.utils.Util;
import com.dukang.customeview.ui.adapter.ViewListAdapter;
import com.dukang.customeview.ui.bean.CustomeViewBean;
import com.dukang.customeview.ui.common.ActivityEnum;

import java.util.List;

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private QuickItemDecoration quickItemDecoration;
    private ViewListAdapter viewListAdapter;


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewUtil.initLinearLayoutManager(recyclerView, activity);
        quickItemDecoration = new QuickItemDecoration(new ItemDivider()
                .setColor(getResources().getColor(R.color.colorF6F6F6))
                .setWidth(40));
        recyclerView.addItemDecoration(quickItemDecoration);
        viewListAdapter = new ViewListAdapter(activity);
        recyclerView.setAdapter(viewListAdapter);
    }

    @Override
    public void initListener() {
        viewListAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                jumpToAnimPage(position);
            }
        });
    }

    List<CustomeViewBean> customeViewBeanList;

    @Override
    public void initData() {
        String json = Util.getJson("viewList.json");
        customeViewBeanList = JsonUtil.fastJsonToArray(json, CustomeViewBean.class);
        viewListAdapter.setData(customeViewBeanList);
        viewListAdapter.notifyDataSetChanged();
    }

    private void jumpToAnimPage(int position) {

        CustomeViewBean customeViewBean = customeViewBeanList.get(position);
        ActivityEnum activityEnum = ActivityEnum.getActivityEnum(customeViewBean.getType());

        switch (activityEnum) {
            case xmlAnimActivity:
                IntentManager.startActivity(this, XmlAnimActivity.class);
                break;
        }

    }
}
