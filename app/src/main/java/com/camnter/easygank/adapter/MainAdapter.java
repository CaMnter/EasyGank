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

import android.widget.ImageView;
import android.widget.TextView;

import com.camnter.easygank.R;
import com.camnter.easygank.bean.DailyData;
import com.camnter.easygank.bean.GankData;
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

    private AdapterType type;

    public enum AdapterType {
        daily,
    }

    public MainAdapter(AdapterType type) {
        this.type = type;
    }

    @Override
    public int[] getItemLayouts() {
        return new int[]{R.layout.item_daily};
    }

    @Override
    public void onBindRecycleViewHolder(EasyRecyclerViewHolder easyRecyclerViewHolder, int i) {
        int layoutType = this.getRecycleViewItemType(i);
        switch (layoutType) {
            case LAYOUT_TYPE_DAILY:
                this.loadingDaily(easyRecyclerViewHolder, i);
                break;
        }
    }

    /**
     * 不需要一个RecyclerView里item不一样的情况
     *
     * @param i i
     * @return int
     */
    @Override
    public int getRecycleViewItemType(int i) {
        switch (this.type) {
            case daily:
                return 0;
            default:
                return 0;
        }
    }

    private void loadingDaily(EasyRecyclerViewHolder easyRecyclerViewHolder, int i) {
        DailyData dailyData = this.getItem(i);
        if (dailyData == null) return;
        ImageView dailyIV = easyRecyclerViewHolder.findViewById(R.id.daily_iv);
        TextView dailyDateTV = easyRecyclerViewHolder.findViewById(R.id.daily_date_tv);
        TextView dailyTitleTV = easyRecyclerViewHolder.findViewById(R.id.daily_title_tv);

        /*
         * 如果没有视频的title和date就找福利的title和date，实在没有就，完！
         */
        if (dailyData.results.videoData != null && dailyData.results.videoData.size() > 0) {
            GankData video = dailyData.results.videoData.get(0);
            dailyTitleTV.setText(video.desc);
            dailyDateTV.setText(DateUtils.string2String(video.publishedAt, "yyyy-MM-dd", "yyyy.MM.dd"));
        } else if (dailyData.results.welfareData != null && dailyData.results.welfareData.size() > 0) {
            GankData welfare = dailyData.results.welfareData.get(0);
            dailyTitleTV.setText(welfare.desc);
            dailyDateTV.setText(DateUtils.string2String(welfare.publishedAt, "yyyy-MM-dd", "yyyy.MM.dd"));
        } else {
            dailyTitleTV.setText("");
        }

        if (dailyData.results.welfareData != null && dailyData.results.welfareData.size() > 0) {
            GlideUtils.display(dailyIV, dailyData.results.welfareData.get(0).url);
        }

    }

}
