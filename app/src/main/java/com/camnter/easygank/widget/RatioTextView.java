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

package com.camnter.easygank.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.camnter.easygank.R;

/**
 * Description：RatioTextView
 * Created by：CaMnter
 * Time：2016-01-07 16:02
 */
public class RatioTextView extends TextView {

    public static final int RATIO_WIDTH_HEIGHT = 1266;
    public static final int RATIO_HEIGHT_WIDTH = 2266;

    private static final float DEFAULT_RATIO = 1.0f;

    // 默认设置宽高比
    public int textRatioType;
    private float textRatio;

    public RatioTextView(Context context) {
        super(context);
    }

    public RatioTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RatioTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RatioTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatioImageView);
        this.textRatioType = typedArray.getInteger(R.styleable.RatioTextView_textRatioType, RATIO_WIDTH_HEIGHT);
        this.textRatio = typedArray.getFloat(R.styleable.RatioTextView_textRatio, DEFAULT_RATIO);
        typedArray.recycle();
    }

    /**
     * 设置宽高比,高度要跟宽度保持一致
     *
     * @param ratio width / height
     */
    public void setWidthHeightRatio(float ratio) {
        this.textRatioType = RATIO_WIDTH_HEIGHT;
        this.textRatio = ratio;
        this.requestLayout();
    }

    /**
     * 设置高宽比,宽度要跟高度保持一致
     *
     * @param ratio height / width
     */
    public void setHeightWidthRatio(float ratio) {
        this.textRatioType = RATIO_HEIGHT_WIDTH;
        this.textRatio = ratio;
        this.requestLayout();
    }

    public void setTextRatio(float ratio) {
        this.textRatio = ratio;
        this.requestLayout();
    }

    public void setTextRatioType(int type) {
        this.textRatioType = type;
        this.requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.textRatio > 0) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = MeasureSpec.getSize(heightMeasureSpec);
            if (this.textRatioType == RatioImageView.RATIO_WIDTH_HEIGHT) {
                // 依照宽高比,重算高度
                if (width > 0) {
                    height = (int) ((float) width / this.textRatio);
                }
            } else {
                // 依照高宽比,重算宽度
                if (height > 0) {
                    width = (int) ((float) height / this.textRatio);
                }
            }
            this.setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

}
