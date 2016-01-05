/*
 * {EasyGank}  Copyright (C) {2015}  {CaMnter}
 *
 * This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
 * This is free software, and you are welcome to redistribute it
 * under certain conditions; type `show c' for details.
 *
 * The hypothetical commands `show w' and `show c' should show the appropriate
 * parts of the General Public License.  Of course, your program's commands
 * might be different; for a GUI interface, you would use an "about box".
 *
 * You should also get your employer (if you work as a programmer) or school,
 * if any, to sign a "copyright disclaimer" for the program, if necessary.
 * For more information on this, and how to apply and follow the GNU GPL, see
 * <http://www.gnu.org/licenses/>.
 *
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs.  If your program is a subroutine library, you
 * may consider it more useful to permit linking proprietary applications with
 * the library.  If this is what you want to do, use the GNU Lesser General
 * Public License instead of this License.  But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 */

package com.camnter.easygank.views;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.camnter.easygank.R;
import com.camnter.easygank.adapter.MainAdapter;
import com.camnter.easygank.bean.DailyData;
import com.camnter.easygank.core.BaseAppCompatActivity;
import com.camnter.easygank.presenter.MainPresenter;
import com.camnter.easygank.presenter.iview.MainView;
import com.camnter.easygank.utils.ToastUtils;
import com.camnter.easyrecyclerview.widget.EasyRecyclerView;
import com.camnter.easyrecyclerview.widget.decorator.EasyDividerItemDecoration;

import java.util.List;

public class MainActivity extends BaseAppCompatActivity implements MainView {

    private EasyRecyclerView mainRV;
    private MainAdapter mainAdapter;
    private MainPresenter presenter;

    private int emptyCount = 0;
    private static final int EMPTY_LIMIT = 5;

    /**
     * Fill in layout id
     *
     * @return layout id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onSwipeRefresh() {
        this.refreshData();
    }

    /**
     * Initialize the view in the layout
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void initViews(Bundle savedInstanceState) {
        this.mainRV = this.findView(R.id.main_rv);
        this.mainRV.addItemDecoration(new EasyDividerItemDecoration(this, EasyDividerItemDecoration.VERTICAL_LIST));
    }

    /**
     * Initialize the View of the listener
     */
    @Override
    protected void initListeners() {
        this.mainRV.addOnScrollListener(this.getRecyclerViewOnScrollListener());
    }

    public RecyclerView.OnScrollListener getRecyclerViewOnScrollListener() {
        return new RecyclerView.OnScrollListener() {
            private boolean toLast = false;

            /**
             * @param recyclerView The RecyclerView which scrolled.
             * @param dx           The amount of horizontal scroll.
             * @param dy           The amount of vertical scroll.
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                /*
                 * dy 表示y轴滑动方向
                 * dx 表示x轴滑动方向
                 */
                if (dy > 0) {
                    // 正在向下滑动
                    this.toLast = true;
                } else {
                    // 停止滑动或者向上滑动
                    this.toLast = false;
                }
            }

            /**
             * @param recyclerView The RecyclerView whose scroll state has changed.
             * @param newState     The updated scroll state. One of {#SCROLL_STATE_IDLE},
             *                     {#SCROLL_STATE_DRAGGING} or {#SCROLL_STATE_SETTLING}.
             */
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();

                // 不滚动
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 最后完成显示的item的position 正好是 最后一条数据的index
                    if (manager.findLastCompletelyVisibleItemPosition() == (manager.getItemCount() - 1) && toLast) {

                        // 没数据了
                        if (MainActivity.this.emptyCount >= EMPTY_LIMIT) {
                            ToastUtils.show(MainActivity.this, MainActivity.this.getString(R.string.main_empty_data), Toast.LENGTH_LONG);
                            return;
                        }



                        // 如果没在刷新
                        if (!MainActivity.this.isRefreshStatus()) {
                            // 加载更多
                            MainActivity.this.presenter.setPage(MainActivity.this.presenter.getPage() + 1);
                            MainActivity.this.setRefreshStatus(false);
                            MainActivity.this.presenter.getDaily(false);
                            MainActivity.this.refresh(true);
                        }

                    }
                }
            }
        };
    }

    /**
     * Initialize the Activity data
     */
    @Override
    protected void initData() {
        this.presenter = new MainPresenter();
        this.presenter.attachView(this);
        this.mainAdapter = new MainAdapter(MainAdapter.AdapterType.daily);
        this.mainRV.setAdapter(this.mainAdapter);

        this.refreshData();
    }

    /**
     * 刷新 or 下拉刷新
     */
    private void refreshData() {
        this.presenter.setPage(1);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                MainActivity.this.refresh(true);
            }
        });
        this.presenter.getDaily(true);
    }

    /**
     * 查询每日干货成功
     *
     * @param dailyData dailyData
     * @param refresh   是否刷新
     */
    @Override
    public void onGetDailySuccess(List<DailyData> dailyData, boolean refresh) {
        if (refresh) {
            this.mainAdapter.clear();
            this.mainAdapter.setList(dailyData);
        } else {
            this.mainAdapter.addAll(dailyData);
        }
        this.mainAdapter.notifyDataSetChanged();
        this.refresh(false);
        if (dailyData.size() == 0) this.emptyCount++;
    }

    /**
     * 发生错误
     *
     * @param e e
     */
    @Override
    public void onFailure(Throwable e) {
        this.setRefreshStatus(true);
    }

    @Override
    protected void onDestroy() {
        this.presenter.detachView();
        super.onDestroy();
    }

}
