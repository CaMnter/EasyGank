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

package com.camnter.easygank.gank;

import android.util.SparseArray;

import com.camnter.easygank.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Description：GankTypeDict
 * Created by：CaMnter
 * Time：2016-01-06 16:49
 */
public class GankTypeDict {

    public static final int DONT_SWITCH = -1;

    public static int[] menuIds = new int[]{
            R.id.navigation_daily,
            R.id.navigation_welfare,
            R.id.navigation_android,
            R.id.navigation_ios,
            R.id.navigation_js,
            R.id.navigation_video,
            R.id.navigation_resources,
            R.id.navigation_app
    };
    public static final SparseArray<Integer> menuId2TypeDict = new SparseArray<>();

    public static final HashMap<Integer, String> type2UrlTypeDict = new HashMap<>();
    public static final HashMap<String, Integer> urlType2TypeDict = new HashMap<>();

    public static final HashMap<String, Integer> urlType2ColorDict = new HashMap<>();

    static {
        menuId2TypeDict.put(R.id.navigation_daily, GankType.daily);
        menuId2TypeDict.put(R.id.navigation_welfare, GankType.welfare);
        menuId2TypeDict.put(R.id.navigation_android, GankType.android);
        menuId2TypeDict.put(R.id.navigation_ios, GankType.ios);
        menuId2TypeDict.put(R.id.navigation_js, GankType.js);
        menuId2TypeDict.put(R.id.navigation_resources, GankType.resources);
        menuId2TypeDict.put(R.id.navigation_video, GankType.video);
        menuId2TypeDict.put(R.id.navigation_app, GankType.app);

        type2UrlTypeDict.put(GankType.welfare, GankApi.DATA_TYPE_WELFARE);
        type2UrlTypeDict.put(GankType.android, GankApi.DATA_TYPE_ANDROID);
        type2UrlTypeDict.put(GankType.ios, GankApi.DATA_TYPE_IOS);
        type2UrlTypeDict.put(GankType.video, GankApi.DATA_TYPE_REST_VIDEO);
        type2UrlTypeDict.put(GankType.resources, GankApi.DATA_TYPE_EXTEND_RESOURCES);
        type2UrlTypeDict.put(GankType.js, GankApi.DATA_TYPE_JS);
        type2UrlTypeDict.put(GankType.app, GankApi.DATA_TYPE_APP);
        type2UrlTypeDict.put(GankType.recommend, GankApi.DATA_TYPE_RECOMMEND);
        for (Map.Entry<Integer, String> entry : type2UrlTypeDict.entrySet()) {
            urlType2TypeDict.put(entry.getValue(), entry.getKey());
        }

        urlType2ColorDict.put(GankApi.DATA_TYPE_WELFARE, 0xffFFAEC9);
        urlType2ColorDict.put(GankApi.DATA_TYPE_ANDROID, 0xff388E3C);
        urlType2ColorDict.put(GankApi.DATA_TYPE_IOS, 0xff0377BC);
        urlType2ColorDict.put(GankApi.DATA_TYPE_REST_VIDEO, 0xff039588);
        urlType2ColorDict.put(GankApi.DATA_TYPE_EXTEND_RESOURCES, 0xff546E7A);
        urlType2ColorDict.put(GankApi.DATA_TYPE_JS, 0xffEF6C02);
        urlType2ColorDict.put(GankApi.DATA_TYPE_APP, 0xffC02E34);
        urlType2ColorDict.put(GankApi.DATA_TYPE_RECOMMEND, 0xff4527A0);

    }
}
