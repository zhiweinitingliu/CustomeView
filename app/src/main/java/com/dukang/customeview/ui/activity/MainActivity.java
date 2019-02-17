package com.dukang.customeview.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dukang.customeview.R;
import com.dukang.customeview.base.BaseActivity;
import com.dukang.customeview.common.recyclerview.BaseRecyclerViewAdapter;
import com.dukang.customeview.common.recyclerview.DefaultItemDecoration;
import com.dukang.customeview.common.recyclerview.RecyclerViewUtil;
import com.dukang.customeview.common.utils.IntentManager;
import com.dukang.customeview.common.utils.JsonUtil;
import com.dukang.customeview.common.utils.ToastUtil;
import com.dukang.customeview.common.utils.Util;
import com.dukang.customeview.ui.adapter.ViewListAdapter;
import com.dukang.customeview.ui.bean.CustomeViewBean;
import com.dukang.customeview.ui.common.ActivityEnum;
import com.dukang.customeview.ui.view.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private SwipeRecyclerView recyclerView;
    private ViewListAdapter viewListAdapter;


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewUtil.initLinearLayoutManager(recyclerView, activity);
        recyclerView.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.colorF6F6F6)));
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

        recyclerView.setLoadMoreListener(new SwipeRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                ToastUtil.toast("我在加载更多...");
                loadData();
            }
        });
    }

    private void loadData() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                String json = Util.getJson("viewList.json");
                List<CustomeViewBean> resultCustomeViewBeanList = JsonUtil.fastJsonToArray(json, CustomeViewBean.class);
                customerViewBeanList.addAll(resultCustomeViewBeanList);
                recyclerView.loadMoreFinish(false, true);
                viewListAdapter.setData(customerViewBeanList);
                viewListAdapter.notifyDataSetChanged();
            }
        }, 1000);
    }

    List<CustomeViewBean> customerViewBeanList;

    @Override
    public void initData() {
        customerViewBeanList = new ArrayList<>();
        loadData();
//        String json = Util.getJson("viewList.json");
//        customeViewBeanList = JsonUtil.fastJsonToArray(json, CustomeViewBean.class);
//        viewListAdapter.setData(customeViewBeanList);
//        viewListAdapter.notifyDataSetChanged();
    }

    private void jumpToAnimPage(int position) {
        CustomeViewBean customeViewBean = customerViewBeanList.get(position);
        ActivityEnum activityEnum = ActivityEnum.getActivityEnum(customeViewBean.getType());
        if (activityEnum != ActivityEnum.error) {
            IntentManager.startActivity(activity, activityEnum.getClazz());
        }
    }
}
