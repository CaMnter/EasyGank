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

package com.camnter.easygank.core;

import android.os.Bundle;

import com.camnter.easygank.R;
import com.camnter.easygank.widget.MultiSwipeRefreshLayout;

import butterknife.Bind;

/**
 * Description：BaseSwipeRefreshLayoutActivity
 * Created by：CaMnter
 * Time：2016-01-05 11:39
 */
public abstract class BaseSwipeRefreshLayoutActivity extends BaseToolbarActivity {

    @Bind(R.id.multi_swipe_refresh_layout)
    protected MultiSwipeRefreshLayout mMultiSwipeRefreshLayout;

    private boolean refreshStatus = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        if (this.mMultiSwipeRefreshLayout != null)
            this.mMultiSwipeRefreshLayout.setOnRefreshListener(() -> onSwipeRefresh());

    }

    /**
     * 刷新的时候
     */
    public abstract void onSwipeRefresh();

    /**
     * 设置刷新状态
     *
     * @param status status
     */
    public void setRefreshStatus(boolean status) {
        this.refreshStatus = status;
    }

    /**
     * 获取当前刷新状态
     *
     * @return boolean
     */
    public boolean isRefreshStatus() {
        return refreshStatus;
    }

    /**
     * 刷新 true false
     *
     * @param refresh refresh
     */
    public void refresh(final boolean refresh) {
        if (this.mMultiSwipeRefreshLayout == null) return;
        /*
         * refresh 只要进来是false 就不考虑 refreshStatus
         * 所以用了短路&&，则直接关掉
         */
        if (!refresh && this.refreshStatus) {
            // 到这了 refresh==false && refreshStatus==true
            this.mMultiSwipeRefreshLayout.postDelayed(() -> {
                BaseSwipeRefreshLayoutActivity.this.mMultiSwipeRefreshLayout.setRefreshing(false);
                BaseSwipeRefreshLayoutActivity.this.refreshStatus = false;
            }, 1666);
        } else if (!this.refreshStatus) {
            /*
             * 到这了，refresh==true，refreshStatus==false
             * 排除了refreshStatus==true的情况
             */
            this.mMultiSwipeRefreshLayout.post(() -> BaseSwipeRefreshLayoutActivity.this.mMultiSwipeRefreshLayout.setRefreshing(true));
            this.refreshStatus = true;
        }

    }

}
