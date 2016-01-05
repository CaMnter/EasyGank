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
import android.support.v4.widget.SwipeRefreshLayout;

import com.camnter.easygank.R;

/**
 * Description：BaseSwipeRefreshLayout
 * Created by：CaMnter
 * Time：2016-01-05 11:39
 */
public abstract class BaseSwipeRefreshLayout extends BaseToolbarActivity {

    protected SwipeRefreshLayout swipeRefreshLayout;

    private boolean refreshStatus = false;

    /**
     * Fill in layout id
     *
     * @return layout id
     */
    protected abstract int getLayoutId();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.swipeRefreshLayout = this.findView(R.id.swipe_refresh_layout);
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
        if (this.swipeRefreshLayout != null)
            this.swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        // 在刷新时，关闭刷新开关
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onSwipeRefresh();
            }
        });

    }

    public void onSwipeRefresh() {
        this.refreshStatus = true;
    }

    public void setRefreshStatus(boolean status) {
        this.refreshStatus = status;
    }

    public boolean getRefreshStatus() {
        return this.refreshStatus;
    }

    public void refresh(boolean refresh) {
        if (this.swipeRefreshLayout == null)
            return;
        if (!refresh) {
            this.refreshStatus = false;
            // 防止刷新消失太快，让子弹飞一会儿.
            this.swipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (BaseSwipeRefreshLayout.this.swipeRefreshLayout != null) {
                        BaseSwipeRefreshLayout.this.swipeRefreshLayout.setRefreshing(false);
                    }
                }
            }, 1200);
        } else {
            this.swipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    BaseSwipeRefreshLayout.this.swipeRefreshLayout.setRefreshing(true);
                }
            }, 1000);
        }
    }


//    public void refresh(final boolean refresh) {
//        if (this.mMultiSwipeRefreshLayout == null) return;
//        /*
//         * refresh 只要进来是false 就不考虑 refreshing
//         * 所以用了短路&&，则直接关掉
//         */
//        this.mMultiSwipeRefreshLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                BaseSwipeRefreshLayout.this.mMultiSwipeRefreshLayout.setProgressViewOffset(false, 0,
//                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
//                BaseSwipeRefreshLayout.this.mMultiSwipeRefreshLayout.setRefreshing(refresh);
//            }
//        });
//        if (!refresh && this.refreshing) {
//            // 延时关闭，防止刷新太快
//            this.mMultiSwipeRefreshLayout.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    BaseSwipeRefreshLayout.this.mMultiSwipeRefreshLayout.setRefreshing(refresh);
//                    BaseSwipeRefreshLayout.this.refreshing = false;
//                }
//            }, 1200);
//        } else if (this.refreshing) {
//            //到这了，refresh==true，refreshing==true
////            this.mMultiSwipeRefreshLayout.setRefreshing(refresh);
//            this.refreshing = true;
//        } else {
//            //到这了，refresh==true，refreshing==false
////            this.mMultiSwipeRefreshLayout.setRefreshing(refresh);
//            this.refreshing = true;
//        }

//}

}
