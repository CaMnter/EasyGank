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
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.camnter.easygank.R;
import com.camnter.easygank.bean.BaseGankData;
import com.camnter.easygank.gank.GankType;
import com.camnter.easygank.gank.GankTypeDict;
import com.camnter.easygank.utils.GlideUtils;
import com.camnter.easygank.utils.ResourcesUtils;
import com.camnter.easygank.widget.RatioImageView;
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
    private int cardItemDivider;

    private int viaTextSize;
    private String viaModel;
    private String viaModelKey;
    private ColorStateList viaColorStateList;

    private static final int dividerColor = 0xffCCCCCC;


    private DailyDetailAdapter.onCardItemClickListener onCardItemClickListener;


    public DailyDetailAdapter(Context context) {
        this.context = context;
        Resources res = this.context.getResources();
        this.initCardItemStyle(res);
        this.initViaTextStyle(res);
    }

    private void initCardItemStyle(Resources res) {
        this.cardItemPadding = res.getDimensionPixelOffset(R.dimen.card_item_content_padding);
        this.cardCategoryPaddingTopBottom = res.getDimensionPixelOffset(R.dimen.card_category_padding_top_bottom);
        this.cardItemDivider = res.getDimensionPixelOffset(R.dimen.card_item_divider);
    }

    private void initViaTextStyle(Resources res) {
        int viaTextColor = ResourcesUtils.getColor(this.context, R.color.common_item_via);
        this.viaTextSize = res.getDimensionPixelSize(R.dimen.item_via_tv);
        this.viaModel = res.getString(R.string.common_via);
        this.viaModelKey = res.getString(R.string.common_via_key);
        this.viaColorStateList = ResourcesUtils.createColorStateList(viaTextColor, viaTextColor, viaTextColor, viaTextColor);
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
            final BaseGankData baseGankData = categoryData.get(i);
            if (i == 0) {
                TextView categoryTV = this.createCardCategory(baseGankData.type);
                detailLL.addView(categoryTV);
                detailLL.addView(this.createDivider());
            }
            if (GankTypeDict.urlType2TypeDict.get(baseGankData.type) == GankType.welfare) {
                RatioImageView welfareIV = this.createRatioImageView();
                GlideUtils.display(welfareIV, baseGankData.url);
                welfareIV.setOnClickListener(v -> {
                    if (DailyDetailAdapter.this.onCardItemClickListener != null)
                        DailyDetailAdapter.this.onCardItemClickListener.onWelfareOnClick(baseGankData.url, baseGankData.desc, v);
                });
                detailLL.addView(welfareIV);
            } else {
                TextView itemText = this.createCardItemText(baseGankData);
                detailLL.addView(itemText);
            }
        }
    }

    @Override
    public int getRecycleViewItemType(int i) {
        return 0;
    }

    private TextView createCardItemText(BaseGankData baseGankData) {
        TextView itemText = (TextView) LayoutInflater.from(this.context).inflate(R.layout.view_card_item, null);
        itemText.setPadding(
                this.cardItemPadding,
                this.cardItemPadding,
                this.cardItemPadding,
                this.cardItemPadding
        );
        String content = baseGankData.desc.trim() +
                "   " +
                String.format(this.viaModel, baseGankData.who);
        SpannableStringBuilder ssb = new SpannableStringBuilder(content);
        ssb.setSpan(new TextAppearanceSpan("serif", Typeface.ITALIC, this.viaTextSize, this.viaColorStateList, this.viaColorStateList),
                content.indexOf(this.viaModelKey),
                content.length(),
                Spanned.SPAN_INCLUSIVE_INCLUSIVE
        );
        itemText.setText(ssb);
        itemText.setTag(R.id.tag_card_item_url, baseGankData.url);
        itemText.setTag(R.id.tag_card_item_desc, baseGankData.desc.trim());
        itemText.setTag(R.id.tag_card_item_type, baseGankData.type);
        itemText.setOnClickListener(v -> {
            if (DailyDetailAdapter.this.onCardItemClickListener != null)
                DailyDetailAdapter.this.onCardItemClickListener.onCardItemOnClick(
                        (String) v.getTag(R.id.tag_card_item_type),
                        (String) v.getTag(R.id.tag_card_item_desc),
                        (String) v.getTag(R.id.tag_card_item_url)
                );
        });
        return itemText;
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
        categoryTV.setTextSize(20);
        categoryTV.setTextColor(GankTypeDict.urlType2ColorDict.get(urlType));
        categoryTV.setBackgroundResource(R.drawable.shape_card_background_default);
        return categoryTV;
    }

    private View createDivider() {
        View divider = new View(this.context);
        divider.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                this.cardItemDivider)
        );
        divider.setBackgroundColor(dividerColor);
        return divider;
    }

    private RatioImageView createRatioImageView() {
        return (RatioImageView) LayoutInflater.from(this.context).inflate(R.layout.view_card_radio_view, null);
    }

    public void setOnCardItemClickListener(DailyDetailAdapter.onCardItemClickListener onCardItemClickListener) {
        this.onCardItemClickListener = onCardItemClickListener;
    }

    public interface onCardItemClickListener {

        void onCardItemOnClick(String urlType, String title, String url);

        void onWelfareOnClick(String url, String title, View v);
    }

}
