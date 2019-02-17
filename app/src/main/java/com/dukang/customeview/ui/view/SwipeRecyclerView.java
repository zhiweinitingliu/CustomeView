package com.dukang.customeview.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/2/11 14:34
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/2/11 14:34
 * @LastCheckBy :wdk
 */
public class SwipeRecyclerView extends RecyclerView {

    private int mScrollState = -1;

    private boolean isLoadMore = false;
    private boolean isAutoLoadMore = true;
    private boolean isLoadError = false;

    private boolean mDataEmpty = true;
    private boolean mHasMore = false;

    private LoadMoreView mLoadMoreView;
    private LoadMoreListener mLoadMoreListener;

    public SwipeRecyclerView(@NonNull Context context) {
        super(context);
    }

    public SwipeRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onScrollStateChanged(int state) {
        mScrollState = state;
    }

    @Override
    public void onScrolled(int dx, int dy) {

        LayoutManager layoutManager = getLayoutManager();
        if (layoutManager != null && layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int itemCount = linearLayoutManager.getItemCount();
            if (itemCount <= 0) {
                return;
            }
            int lastVisiblePosition = linearLayoutManager.findLastVisibleItemPosition();
            if (itemCount - 1 == lastVisiblePosition && (mScrollState == SCROLL_STATE_DRAGGING || mScrollState == SCROLL_STATE_SETTLING)) {
                dispatchLoadMore();
            }
        }
    }

    /**
     * 加载更多
     */
    public void dispatchLoadMore() {

        if (isLoadError) return;

        if (!isAutoLoadMore) {
            if (mLoadMoreView != null) mLoadMoreView.onWaitToLoadMore(mLoadMoreListener);
        } else {
            //加载中  空数据  没有更多数据
            if (isLoadMore || mDataEmpty || !mHasMore) return;

            //正在加载中
            isLoadMore = true;

            if (mLoadMoreView != null) mLoadMoreView.onLoading();

            if (mLoadMoreListener != null) mLoadMoreListener.onLoadMore();
        }
    }


    /**
     * Load more view.
     */
    public void setLoadMoreView(LoadMoreView loadMoreView) {
        mLoadMoreView = loadMoreView;
    }

    /**
     * Load more listener.
     */
    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        mLoadMoreListener = loadMoreListener;
    }

    /**
     * Load more done.
     *
     * @param dataEmpty data is empty ?
     * @param hasMore has more data ?
     */
    public final void loadMoreFinish(boolean dataEmpty, boolean hasMore) {
        isLoadMore = false;
        isLoadError = false;

        mDataEmpty = dataEmpty;
        mHasMore = hasMore;

        if (mLoadMoreView != null) {
            mLoadMoreView.onLoadFinish(dataEmpty, hasMore);
        }
    }

    public interface LoadMoreView {

        /**
         * Show progress.
         */
        void onLoading();

        /**
         * Load finish, handle result.
         */
        void onLoadFinish(boolean dataEmpty, boolean hasMore);

        /**
         * Non-auto-loading mode, you can to click on the item to load.
         */
        void onWaitToLoadMore(LoadMoreListener loadMoreListener);

        /**
         * Load error.
         */
        void onLoadError(int errorCode, String errorMessage);
    }

    public interface LoadMoreListener {

        /**
         * More data should be requested.
         */
        void onLoadMore();
    }
}
