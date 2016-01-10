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

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.camnter.easygank.R;
import com.camnter.easygank.bean.BaseGankData;
import com.camnter.easygank.gank.GankTypeDict;
import com.camnter.easyrecyclerview.adapter.EasyRecyclerViewAdapter;
import com.camnter.easyrecyclerview.holder.EasyRecyclerViewHolder;

import java.util.List;

/**
 * Description：DailyDetailAdapter
 * Created by：CaMnter
 * Time：2016-01-10 15:52
 */
public class DailyDetailAdapter extends EasyRecyclerViewAdapter {

    private Context context;
    private int cardItemPadding;
    private int cardCategoryPaddingTopBottom;

    private DailyDetailAdapter.onCardItemClickListener onCardItemClickListener;

    public DailyDetailAdapter(Context context) {
        this.context = context;
        this.cardItemPadding = this.context.getResources().getDimensionPixelOffset(R.dimen.card_item_content_padding);
        this.cardCategoryPaddingTopBottom = this.context.getResources().getDimensionPixelOffset(R.dimen.card_category_padding_top_bottom);
    }

    @Override
    public int[] getItemLayouts() {
        return new int[]{R.layout.item_daily_detail};
    }

    @Override
    public void onBindRecycleViewHolder(EasyRecyclerViewHolder easyRecyclerViewHolder, int position) {
        List<BaseGankData> categoryData = this.getItem(position);
        if (categoryData == null || categoryData.size() <= 0) return;
        LinearLayout detailLL = easyRecyclerViewHolder.findViewById(R.id.daily_detail_ll);

        detailLL.removeAllViews();
        for (int i = 0; i < categoryData.size(); i++) {
            BaseGankData baseGankData = categoryData.get(i);
            TextView itemModel = this.createCardItemModel(baseGankData);
            if (i == 0) {
                TextView categoryTV = this.createCardCategory(baseGankData.type);
                detailLL.addView(categoryTV);
            }
            detailLL.addView(itemModel);
        }

    }

    @Override
    public int getRecycleViewItemType(int i) {
        return 0;
    }

    private TextView createCardItemModel(BaseGankData baseGankData) {
        TextView itemModel = (TextView) LayoutInflater.from(this.context).inflate(R.layout.view_card_item, null);
        itemModel.setPadding(
                this.cardItemPadding,
                this.cardItemPadding,
                this.cardItemPadding,
                this.cardItemPadding
        );
        itemModel.setText(baseGankData.desc.trim());
        itemModel.setTag(R.id.tag_card_item_url, baseGankData.url);
        itemModel.setTag(R.id.tag_card_item_desc, baseGankData.desc.trim());
        itemModel.setTag(R.id.tag_card_item_type, baseGankData.type);
        itemModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DailyDetailAdapter.this.onCardItemClickListener != null)
                    DailyDetailAdapter.this.onCardItemClickListener.onCardItemOnClick(
                            (String) v.getTag(R.id.tag_card_item_type),
                            (String) v.getTag(R.id.tag_card_item_desc),
                            (String) v.getTag(R.id.tag_card_item_url)
                    );
            }
        });
        return itemModel;
    }

    private TextView createCardCategory(String urlType) {
        TextView categoryTV = new TextView(this.context);
        categoryTV.setPadding(
                this.cardItemPadding,
                this.cardCategoryPaddingTopBottom,
                this.cardItemPadding,
                this.cardCategoryPaddingTopBottom
        );
        categoryTV.setText(urlType);
        categoryTV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        categoryTV.setTextSize(18);
        categoryTV.setTextColor(GankTypeDict.urlType2ColorDict.get(urlType));
        categoryTV.setBackgroundResource(R.drawable.shape_card_background_default);
        return categoryTV;
    }

    public void setOnCardItemClickListener(DailyDetailAdapter.onCardItemClickListener onCardItemClickListener) {
        this.onCardItemClickListener = onCardItemClickListener;
    }

    public interface onCardItemClickListener {
        void onCardItemOnClick(String urlType, String title, String url);
    }

}
