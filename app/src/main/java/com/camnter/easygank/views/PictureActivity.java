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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.camnter.easygank.R;
import com.camnter.easygank.core.BaseToolbarActivity;
import com.camnter.easygank.utils.GlideUtils;
import com.camnter.easygank.utils.IntentUtils;

/**
 * Description：PictureActivity
 * Created by：CaMnter
 * Time：2016-01-11 19:25
 */
public class PictureActivity extends BaseToolbarActivity {

    private static final String EXTRA_URL = "com.camnter.easygank.EXTRA_URL";
    private static final String EXTRA_TITLE = "com.camnter.easygank.EXTRA_TITLE";

    private ImageView pictureIV;

    public static void startActivity(Context context, String url, String title) {
        Intent intent = new Intent(context, PictureActivity.class);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_TITLE, title);
        context.startActivity(intent);
    }

    /**
     * Fill in layout id
     *
     * @return layout id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_picture;
    }

    /**
     * Initialize the view in the layout
     *
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void initViews(Bundle savedInstanceState) {
        this.pictureIV = this.findView(R.id.picture_iv);
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
        this.showBack();
        this.setTitle(this.getUrlTitle());
        GlideUtils.display(this.pictureIV, this.getUrl());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_picture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_picture_download:
                return true;
            case R.id.menu_picture_copy:
                return true;
            case R.id.menu_picture_share:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String getUrl() {
        return IntentUtils.getStringExtra(this.getIntent(), EXTRA_URL);
    }

    private String getUrlTitle() {
        return IntentUtils.getStringExtra(this.getIntent(), EXTRA_TITLE);
    }

}
