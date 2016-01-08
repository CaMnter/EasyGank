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
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.widget.Toast;

import com.camnter.easygank.R;
import com.camnter.easygank.adapter.MainAdapter;
import com.camnter.easygank.bean.BaseGankData;
import com.camnter.easygank.bean.GankDaily;
import com.camnter.easygank.core.BaseAppCompatActivity;
import com.camnter.easygank.gank.GankType;
import com.camnter.easygank.gank.GankTypeDict;
import com.camnter.easygank.presenter.MainPresenter;
import com.camnter.easygank.presenter.iview.MainView;
import com.camnter.easyrecyclerview.widget.EasyRecyclerView;
import com.camnter.easyrecyclerview.widget.decorator.EasyBorderDividerItemDecoration;

import java.util.List;

public class MainActivity extends BaseAppCompatActivity implements MainView {

    private EasyRecyclerView mainRV;
    private EasyBorderDividerItemDecoration dataDecoration;
    private LinearLayoutManager mLinearLayoutManager;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private MainAdapter mainAdapter;
    private MainPresenter presenter;

    private int emptyCount = 0;
    private static final int EMPTY_LIMIT = 5;

    private GankType gankType;

    /**
     * Fill in layout id
     *
     * @return layout id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * 刷新的时候
     */
    @Override
    public void onSwipeRefresh() {
        this.refreshData(this.gankType);
    }


    /**
     * Initialize the view in the layout
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void initViews(Bundle savedInstanceState) {
        this.mainRV = this.findView(R.id.main_rv);
        this.dataDecoration = new EasyBorderDividerItemDecoration(
                this.getResources().getDimensionPixelOffset(R.dimen.data_border_divider_height),
                this.getResources().getDimensionPixelOffset(R.dimen.data_border_padding_infra_spans)
        );
        this.mainRV.addItemDecoration(this.dataDecoration);
        this.mLinearLayoutManager = (LinearLayoutManager) this.mainRV.getLayoutManager();
        this.mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    /**
     * Initialize the View of the listener
     */
    @Override
    protected void initListeners() {
        this.mainRV.addOnScrollListener(this.getRecyclerViewOnScrollListener());
    }

    /**
     * LinearLayoutManager 时的滚动监听
     *
     * @return RecyclerView.OnScrollListener
     */
    public RecyclerView.OnScrollListener getRecyclerViewOnScrollListener() {
        return new RecyclerView.OnScrollListener() {
            private boolean toLast = false;

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

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
                    // 不滚动
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        // 最后完成显示的item的position 正好是 最后一条数据的index
                        if (toLast && manager.findLastCompletelyVisibleItemPosition() == (manager.getItemCount() - 1)) {
                            MainActivity.this.loadMoreRequest();
                        }
                    }
                } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                    StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
                    // 不滚动
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        /*
                         * 由于是StaggeredGridLayoutManager
                         * 取最底部数据可能有两个item，所以判断这之中有一个正好是 最后一条数据的index
                         * 就OK
                         */
                        int[] bottom = manager.findLastCompletelyVisibleItemPositions(new int[2]);
                        int lastItemCount = manager.getItemCount() - 1;
                        if (toLast && (bottom[0] == lastItemCount || bottom[1] == lastItemCount)) {
                            MainActivity.this.loadMoreRequest();
                        }
                    }
                }

            }
        };
    }

    /**
     * 请求加载更多
     */
    private void loadMoreRequest() {
        // 没数据了
        if (this.emptyCount >= EMPTY_LIMIT) {
            this.showToast(MainActivity.this.getString(R.string.main_empty_data), Toast.LENGTH_LONG);
            return;
        }

        // 如果没在刷新
        if (!MainActivity.this.isRefreshStatus()) {
            // 加载更多
            this.presenter.setPage(MainActivity.this.presenter.getPage() + 1);
            this.setRefreshStatus(false);
            this.loadMore(MainActivity.this.gankType);
            this.refresh(true);
        }
    }

    /**
     * 加载更多
     *
     * @param gankType gankType
     */
    private void loadMore(GankType gankType) {
        switch (gankType) {
            case daily:
                this.presenter.getDaily(false, GankTypeDict.DONT_SWITCH);
                break;
            case android:
            case ios:
            case js:
            case resources:
            case welfare:
            case video:
                this.presenter.getData(this.gankType, false, GankTypeDict.DONT_SWITCH);
                break;
        }
    }

    /**
     * Initialize the Activity data
     */
    @Override
    protected void initData() {
        this.presenter = new MainPresenter();
        this.presenter.attachView(this);
        this.gankType = GankType.daily;
        // 默认是每日干货
        this.mainAdapter = new MainAdapter(this, this.gankType);
        this.mainRV.setAdapter(this.mainAdapter);

        this.refreshData(this.gankType);
    }

    /**
     * 刷新 or 下拉刷新
     */
    private void refreshData(GankType gankType) {
        this.presenter.setPage(1);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                MainActivity.this.refresh(true);
            }
        });
        switch (gankType) {
            case daily:
                this.presenter.getDaily(true, GankTypeDict.DONT_SWITCH);
                break;
            case android:
            case ios:
            case js:
            case resources:
            case welfare:
            case video:
                this.presenter.getData(this.gankType, true, GankTypeDict.DONT_SWITCH);
                break;
        }
    }

    /**
     * 查询每日干货成功
     *
     * @param dailyData dailyData
     * @param refresh   是否刷新
     */
    @Override
    public void onGetDailySuccess(List<GankDaily> dailyData, boolean refresh) {
        if (refresh) {
            this.emptyCount = 0;
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
     * 查询 ( Android、iOS、前端、拓展资源、福利、休息视频 ) 成功
     *
     * @param data    data
     * @param refresh 是否刷新
     */
    @Override
    public void onGetDataSuccess(List<BaseGankData> data, boolean refresh) {
        if (refresh) {
            this.emptyCount = 0;
            this.mainAdapter.clear();
            this.mainAdapter.setList(data);
        } else {
            this.mainAdapter.addAll(data);
        }
        this.mainAdapter.notifyDataSetChanged();
        this.refresh(false);
        if (data.size() == 0) this.emptyCount++;
    }

    /**
     * 切换数据源成功
     *
     * @param type type
     */
    @Override
    public void onSwitchSuccess(GankType type) {
        this.emptyCount = 0;
        this.mainAdapter.setType(type);
        this.mainAdapter.clear();
        this.gankType = type;

        // 重置LayoutManager 和 分割线
        switch (gankType) {
            case daily:
            case android:
            case ios:
            case js:
            case resources:
            case video:
                // 防止重复添加一样的
                this.mainRV.removeItemDecoration(this.dataDecoration);

                this.mainRV.setLayoutManager(this.mLinearLayoutManager);
                this.mainRV.addItemDecoration(this.dataDecoration);
                break;
            case welfare:
                this.mainRV.removeItemDecoration(this.dataDecoration);
                this.mainRV.setLayoutManager(this.mStaggeredGridLayoutManager);
                break;
        }
    }

    /**
     * 发生错误
     *
     * @param e e
     */
    @Override
    public void onFailure(Throwable e) {
        this.refresh(false);
        this.setRefreshStatus(true);
        Snackbar.make(this.mainRV, R.string.main_load_error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        this.presenter.detachView();
        super.onDestroy();
    }

    /**
     * Fill in NavigationView.OnNavigationItemSelectedListener
     *
     * @return NavigationView.OnNavigationItemSelectedListener
     */
    @Override
    protected NavigationView.OnNavigationItemSelectedListener getNavigationItemSelectedListener() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                return MainActivity.this.menuItemChecked(item.getItemId());
            }
        };
    }

    /**
     * Fill in NavigationView menu ids
     *
     * @return int[]
     */
    @Override
    protected int[] getMenuItemIds() {
        return GankTypeDict.menuIds;
    }

    /**
     * Fill in your menu operation on click
     * <p/>
     * 走到这，就不会有两次点击都一样的情况
     * Come to this, there would be no two clicks are all the same
     *
     * @param now Now you choose the item
     */
    @Override
    protected void onMenuItemOnClick(MenuItem now) {
        if (GankTypeDict.menuId2TypeDict.indexOfKey(now.getItemId()) >= 0)
            this.changeGankType(GankTypeDict.menuId2TypeDict.get(now.getItemId()));
    }

    /**
     * 走到这，就不会有两次点击都一样的情况
     * Come to this, there would be no two clicks are all the same
     *
     * @param gankType gankType
     */
    private void changeGankType(GankType gankType) {
        this.refresh(true);
        this.presenter.switchType(gankType);
    }

}
