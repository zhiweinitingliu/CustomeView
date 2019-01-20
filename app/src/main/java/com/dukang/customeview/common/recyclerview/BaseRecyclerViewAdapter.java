package com.dukang.customeview.common.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description :baseRecyclerViewAdapter
 * @Author : wdk
 * @CretaTime : 2019/1/18 11:15
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/1/18 11:15
 * @LastCheckBy :wdk
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerHolder> {

    public static final int HEAD_VIEW_TYPE = 1001;//头布局的type
    public static final int FOOT_VIEW_TYPE = 1002;//脚布局的type
    public static final int CONTENT_VIEW_TYPE = 1003;//条目的type

    private List<T> dataList;
    private Context context;
    private int layoutId;
    private List<View> headViewList = new ArrayList<>();
    private List<View> footViewList = new ArrayList<>();

    public BaseRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public BaseRecyclerViewAdapter(Context context, int layoutId) {
        this.context = context;
        this.layoutId = layoutId;
    }

    public void setData(List<T> dataList) {
        this.dataList = dataList;
    }

    public void addHead(View headView) {
        headViewList.add(headView);
    }

    public void addFoot(View footView) {
        footViewList.add(footView);
    }

    @Override
    public int getItemCount() {
        return dataList == null ? headViewList.size() + footViewList.size() : dataList.size() + headViewList.size() + footViewList.size();
    }

    @Override
    public int getItemViewType(int position) {
        int contentSize = dataList.size();
        int headViewSize = headViewList.size();

        if (position >= 0 && position < headViewSize) {
            return HEAD_VIEW_TYPE;
        } else if (position >= headViewSize && position < headViewSize + contentSize) {
            return CONTENT_VIEW_TYPE;
        } else {
            return FOOT_VIEW_TYPE;
        }
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HEAD_VIEW_TYPE) {
            return new RecyclerHolder(context, headViewList.get(0));
        } else if (viewType == FOOT_VIEW_TYPE) {
            return new RecyclerHolder(context, footViewList.get(0));
        }
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new RecyclerHolder(context, itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerHolder recyclerHolder, final int position) {
        int contentSize = dataList.size();
        int headViewSize = headViewList.size();
        if (position >= headViewSize && position < headViewSize + contentSize) {
            recyclerHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, position);
                    }
                }
            });
            convert(recyclerHolder, dataList.get(position), position);
        }


    }

    public abstract void convert(RecyclerHolder holder, T t, int position);


    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

        void onItemClick(View view, int position);
    }
}
