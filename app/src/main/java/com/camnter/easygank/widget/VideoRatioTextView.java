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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.camnter.easygank.R;

/**
 * Description：VideoRatioImageView
 * Created by：CaMnter
 * Time：2016-01-07 13:45
 */
public class VideoRatioTextView extends RelativeLayout {

    private static final int DEFAULT_COVER_COLOR = 0x4b000000;

    private static final int DEFAULT_RATIO_TYPE = RatioImageView.RATIO_WIDTH_HEIGHT;
    private static final float DEFAULT_RATIO = 1.0f;

    private View mCoverView;
    private RatioTextView mRatioTextView;

    private int coverColor;
    private int videoRatioType;
    private float videoRatio;

    public VideoRatioTextView(Context context) {
        super(context);
    }

    public VideoRatioTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initAttributes(context, attrs);
    }

    public VideoRatioTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initAttributes(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public VideoRatioTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.initAttributes(context, attrs);
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_video_ratio, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.VideoRatioTextView);
        this.coverColor = typedArray.getColor(R.styleable.VideoRatioTextView_coverColor, DEFAULT_COVER_COLOR);
        this.videoRatioType = typedArray.getInteger(R.styleable.VideoRatioTextView_videoRatioType, DEFAULT_RATIO_TYPE);
        this.videoRatio = typedArray.getFloat(R.styleable.VideoRatioTextView_videoRatio, DEFAULT_RATIO);
        typedArray.recycle();
    }

    public void setCoverColor(int coverColor) {
        this.coverColor = coverColor;
        this.mCoverView.setBackgroundColor(coverColor);
    }

    public TextView getRatioTextView() {
        return this.mRatioTextView;
    }

    /**
     * Finalize inflating a view from XML.  This is called as the last phase
     * of inflation, after all child views have been added.
     * <p/>
     * <p>Even if the subclass overrides onFinishInflate, they should always be
     * sure to call the super method, so that we get called.
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mCoverView = this.findViewById(R.id.video_ratio_cover_v);
        this.mRatioTextView = (RatioTextView) this.findViewById(R.id.video_ratio_tv);
        this.mCoverView.setBackgroundColor(this.coverColor);
        this.mRatioTextView.setTextRatioType(this.videoRatioType);
        this.mRatioTextView.setTextRatio(this.videoRatio);
        this.mRatioTextView.requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
