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

package com.camnter.easygank.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.camnter.easygank.R;
import com.camnter.easygank.bean.BaseGankData;
import com.camnter.easygank.bean.GankDaily;
import com.camnter.easygank.gank.GankType;
import com.camnter.easygank.utils.DateUtils;
import com.camnter.easygank.utils.GlideUtils;
import com.camnter.easyrecyclerview.adapter.EasyRecyclerViewAdapter;
import com.camnter.easyrecyclerview.holder.EasyRecyclerViewHolder;

/**
 * Description：MainAdapter
 * Created by：CaMnter
 * Time：2016-01-03 15:24
 */
public class MainAdapter extends EasyRecyclerViewAdapter {

    public static final int LAYOUT_TYPE_DAILY = 0;
    public static final int LAYOUT_TYPE_TECHNOLOGY = 1;
    private GankType type;

    private static final String JS = "前端";
    private static final String IOS = "iOS";
    private static final String ANDROID = "Android";

    public MainAdapter(GankType type) {
        this.type = type;
    }

    @Override
    public int[] getItemLayouts() {
        return new int[]{R.layout.item_daily, R.layout.item_technology};
    }

    @Override
    public void onBindRecycleViewHolder(EasyRecyclerViewHolder easyRecyclerViewHolder, int position) {
        int layoutType = this.getRecycleViewItemType(position);
        switch (layoutType) {
            case LAYOUT_TYPE_DAILY:
                this.loadingDaily(easyRecyclerViewHolder, position);
                break;
            case LAYOUT_TYPE_TECHNOLOGY:
                this.loadingTechnology(easyRecyclerViewHolder, position);
                break;
        }
    }

    public GankType getType() {
        return type;
    }

    /**
     * 用于切换数据类型，从而切换数据源(url)
     *
     * @param type type
     */
    public void setType(GankType type) {
        this.type = type;
    }

    /**
     * 不需要一个RecyclerView里item不一样的情况
     *
     * @param position position
     * @return int
     */
    @Override
    public int getRecycleViewItemType(int position) {
        /*
         * android、ios、js、resource是一套布局
         */
        switch (this.type) {
            case daily:
                return 0;
            case android:
            case ios:
            case js:
            case resources:
                return 1;
            default:
                return 0;
        }
    }

    /**
     * 加载每日干货类型数据
     *
     * @param easyRecyclerViewHolder easyRecyclerViewHolder
     * @param position               position
     */
    private void loadingDaily(EasyRecyclerViewHolder easyRecyclerViewHolder, int position) {
        GankDaily dailyData = this.getItem(position);
        if (dailyData == null) return;
        ImageView dailyIV = easyRecyclerViewHolder.findViewById(R.id.daily_iv);
        TextView dailyDateTV = easyRecyclerViewHolder.findViewById(R.id.daily_date_tv);
        TextView dailyTitleTV = easyRecyclerViewHolder.findViewById(R.id.daily_title_tv);

        TextView androidTagTV = easyRecyclerViewHolder.findViewById(R.id.daily_android_tag_tv);
        TextView iOSTagTV = easyRecyclerViewHolder.findViewById(R.id.daily_ios_tag_tv);
        TextView jsTagTV = easyRecyclerViewHolder.findViewById(R.id.daily_js_tag_tv);


        /*
         * 标题 和 日期
         *
         * 如果没有视频的title和date就找福利的title和date，实在没有就，完！
         */
        if (dailyData.results.videoData != null && dailyData.results.videoData.size() > 0) {
            BaseGankData video = dailyData.results.videoData.get(0);
            dailyTitleTV.setText(video.desc);
            dailyDateTV.setText(DateUtils.string2String(video.publishedAt, "yyyy-MM-dd", "yyyy.MM.dd"));
        } else if (dailyData.results.welfareData != null && dailyData.results.welfareData.size() > 0) {
            BaseGankData welfare = dailyData.results.welfareData.get(0);
            dailyTitleTV.setText(welfare.desc);
            dailyDateTV.setText(DateUtils.string2String(welfare.publishedAt, "yyyy-MM-dd", "yyyy.MM.dd"));
        } else {
            dailyTitleTV.setText("这期没福利了，安心学习吧！");
            dailyDateTV.setText("");
        }

        /*
         * 图片
         */
        if (dailyData.results.welfareData != null && dailyData.results.welfareData.size() > 0) {
            GlideUtils.display(dailyIV, dailyData.results.welfareData.get(0).url);
        } else {
            GlideUtils.displayNative(dailyIV, R.mipmap.img_default_gray);
        }

        /*
         * 标签 ListView 和 RecyclerView 都要写else 因为复用问题
         * 忧伤
         */
        if (dailyData.category == null) {
            androidTagTV.setVisibility(View.GONE);
            iOSTagTV.setVisibility(View.GONE);
            jsTagTV.setVisibility(View.GONE);
        } else {
            if (dailyData.category.contains(ANDROID)) {
                androidTagTV.setVisibility(View.VISIBLE);
            } else {
                androidTagTV.setVisibility(View.GONE);
            }
            if (dailyData.category.contains(IOS)) {
                iOSTagTV.setVisibility(View.VISIBLE);
            } else {
                iOSTagTV.setVisibility(View.GONE);
            }
            if (dailyData.category.contains(JS)) {
                jsTagTV.setVisibility(View.VISIBLE);
            } else {
                jsTagTV.setVisibility(View.GONE);
            }
        }

    }

    /**
     * 加载技术类型数据
     *
     * @param easyRecyclerViewHolder easyRecyclerViewHolder
     * @param position               position
     */
    private void loadingTechnology(EasyRecyclerViewHolder easyRecyclerViewHolder, int position) {
        BaseGankData baseGankData = this.getItem(position);
        if (baseGankData == null) return;
        TextView technologyDateTV = easyRecyclerViewHolder.findViewById(R.id.technology_date_tv);
        TextView technologyTitleTV = easyRecyclerViewHolder.findViewById(R.id.technology_title_tv);

        /*
         * 标题
         */
        if (TextUtils.isEmpty(baseGankData.desc)) {
            technologyTitleTV.setText("");
        } else {
            technologyTitleTV.setText(baseGankData.desc);
        }

        /*
         * 时间 2016-01-05T05:47:06.977Z
         */
        if (TextUtils.isEmpty(baseGankData.publishedAt)) {
            technologyDateTV.setText("");
        } else {
            technologyDateTV.setText(DateUtils.string2String(baseGankData.publishedAt, "yyyy-MM-dd'T'HH:mm:ss", "yyyy.MM.dd HH:mm"));
        }

    }


}
