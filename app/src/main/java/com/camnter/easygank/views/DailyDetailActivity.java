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

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.camnter.easygank.R;
import com.camnter.easygank.adapter.DailyDetailAdapter;
import com.camnter.easygank.bean.BaseGankData;
import com.camnter.easygank.core.BaseToolbarActivity;
import com.camnter.easygank.utils.IntentUtils;
import com.camnter.easyrecyclerview.widget.EasyRecyclerView;
import com.camnter.easyrecyclerview.widget.decorator.EasyBorderDividerItemDecoration;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Description：DailyDetailActivity
 * Created by：CaMnter
 * Time：2016-01-09 19:01
 */
public class DailyDetailActivity extends BaseToolbarActivity implements DailyDetailAdapter.onCardItemClickListener {

    @Bind(R.id.daily_detail_rv)
    EasyRecyclerView dailydailyDetailRv;
    private DailyDetailAdapter detailAdapter;

    private static final String EXTRA_DETAIL = "com.camnter.easygank.EXTRA_DETAIL";
    private static final String EXTRA_TITLE = "com.camnter.easygank.EXTRA_TITLE";

    public static void startActivity(Context context, String title, ArrayList<ArrayList<BaseGankData>> detail) {
        Intent intent = new Intent(context, DailyDetailActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_DETAIL, detail);
        context.startActivity(intent);
    }

    /**
     * Fill in layout id
     *
     * @return layout id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_daily_detail;
    }

    /**
     * Initialize the view in the layout
     *
     * @param savedInstanceState savedInstanceState
     */
    @SuppressLint("InflateParams")
    @Override
    protected void initViews(Bundle savedInstanceState) {
        EasyBorderDividerItemDecoration detailDecoration = new EasyBorderDividerItemDecoration(
                this.getResources().getDimensionPixelOffset(R.dimen.data_border_divider_height),
                this.getResources().getDimensionPixelOffset(R.dimen.data_border_padding_infra_spans)
        );
        this.dailydailyDetailRv.addItemDecoration(detailDecoration);
        this.detailAdapter = new DailyDetailAdapter(this);
        this.detailAdapter.setOnCardItemClickListener(this);
        this.dailydailyDetailRv.setAdapter(this.detailAdapter);
        this.showBack();
        this.setTitle(this.getDetailTitle());
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
        this.detailAdapter.setList(this.getDetail());
        this.detailAdapter.notifyDataSetChanged();
    }

    @SuppressWarnings("unchecked")
    private ArrayList<ArrayList<BaseGankData>> getDetail() {
        return (ArrayList<ArrayList<BaseGankData>>) IntentUtils.getSerializableExtra(this.getIntent(), EXTRA_DETAIL);
    }

    private String getDetailTitle() {
        return IntentUtils.getStringExtra(this.getIntent(), EXTRA_TITLE);
    }

    @Override
    public void onCardItemOnClick(String urlType, String title, String url) {
        EasyWebViewActivity.toUrl(this, url, title, urlType);
    }

    @Override
    public void onWelfareOnClick(String url, String title, View v) {
        PictureActivity.startActivityByActivityOptionsCompat(this, url, title, v);
    }

    /*********
     * Umeng *
     *********/

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    /*********
     * Umeng *
     *********/

}
