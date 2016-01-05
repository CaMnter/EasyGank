package com.camnter.easygank.core;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.camnter.easygank.R;
import com.camnter.easygank.widget.MultiSwipeRefreshLayout;

/**
 * Description：BaseSwipeRefreshLayout
 * Created by：CaMnter
 * Time：2016-01-05 11:39
 */
public abstract class BaseSwipeRefreshLayout extends BaseToolbarActivity {

    protected MultiSwipeRefreshLayout mMultiSwipeRefreshLayout;

    private boolean refreshing = false;

    /**
     * Fill in layout id
     *
     * @return layout id
     */
    protected abstract int getLayoutId();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mMultiSwipeRefreshLayout = this.findView(R.id.multi_swipe_refresh_layout);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.initMultiSwipeRefreshLayout();
    }

    /**
     * 初始化MultiSwipeRefreshLayout
     */
    private void initMultiSwipeRefreshLayout() {
        // 下拉刷新的颜色
        if (this.mMultiSwipeRefreshLayout != null)
            this.mMultiSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        // 在刷新时，关闭刷新开关
        this.mMultiSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                BaseSwipeRefreshLayout.this.refreshing = true;
            }
        });

    }

    public void refresh(boolean refresh) {
        if (this.mMultiSwipeRefreshLayout == null) return;
        /*
         * refresh 只要进来是false 就不考虑 refreshing
         * 所以用了短路&，则直接关掉
         */
        if (!refresh & this.refreshing) {
            // 延时关闭，防止刷新太快
            this.mMultiSwipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    BaseSwipeRefreshLayout.this.mMultiSwipeRefreshLayout.setRefreshing(false);
                    BaseSwipeRefreshLayout.this.refreshing = false;
                }
            }, 1600);
        } else if (this.refreshing) {
            //到这了，refresh==true，refreshing==true
            this.mMultiSwipeRefreshLayout.setRefreshing(true);
            this.refreshing = true;
        } else {
            //到这了，refresh==true，refreshing==false
            this.mMultiSwipeRefreshLayout.setRefreshing(true);
            this.refreshing = true;
        }

    }

}
