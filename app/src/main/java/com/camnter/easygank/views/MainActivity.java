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

import com.camnter.easygank.R;
import com.camnter.easygank.adapter.MainAdapter;
import com.camnter.easygank.bean.DailyData;
import com.camnter.easygank.core.BaseAppCompatActivity;
import com.camnter.easygank.presenter.MainPresenter;
import com.camnter.easygank.presenter.iview.MainView;
import com.camnter.easyrecyclerview.widget.EasyRecyclerView;
import com.camnter.easyrecyclerview.widget.decorator.EasyDividerItemDecoration;

import java.util.List;

public class MainActivity extends BaseAppCompatActivity implements MainView {

    private EasyRecyclerView mainRV;

    private MainAdapter mainAdapter;

    private MainPresenter presenter;

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

        this.refresh();
    }

    /**
     * 刷新 or 下拉刷新
     */
    private void refresh() {
        this.presenter.getDaily();
    }

    /**
     * 查询每日干货成功
     *
     * @param dailyData dailyData
     */
    @Override
    public void onGetDailySuccess(List<DailyData> dailyData) {
        this.mainAdapter.setList(dailyData);
        this.mainAdapter.notifyDataSetChanged();
    }

    /**
     * 发生错误
     *
     * @param e e
     */
    @Override
    public void onFailure(Throwable e) {

    }

    @Override
    protected void onDestroy() {
        this.presenter.detachView();
        super.onDestroy();
    }
}
