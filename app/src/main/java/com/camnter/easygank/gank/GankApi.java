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

/**
 * Description：GankApi - http://gank.io/api
 * <p>
 * 分类数据: http://gank.avosapps.com/api/data/数据类型/请求个数/第几页
 * 数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
 * 请求个数： 数字，大于0
 * 第几页：数字，大于0
 * <p>
 * Created by：CaMnter
 * Time：2016-01-03 15:49
 */
public class GankApi {
    public static final String GANK_HOME_PAGE_NAME = "干货集中营";
    public static final String GANK_HOME_PAGE_URL = "http://gank.io/";

    public static final String BASE_URL = "http://gank.avosapps.com/api/";

    public static final String DATA_TYPE_WELFARE = "福利";
    public static final String DATA_TYPE_ANDROID = "Android";
    public static final String DATA_TYPE_IOS = "iOS";
    public static final String DATA_TYPE_REST_VIDEO = "休息视频";
    public static final String DATA_TYPE_EXTEND_RESOURCES = "拓展资源";
    public static final String DATA_TYPE_JS = "前端";
    public static final String DATA_TYPE_APP = "App";
    public static final String DATA_TYPE_RECOMMEND = "瞎推荐";
    public static final String DATA_TYPE_ALL = "all";

    public static final int DEFAULT_DATA_SIZE = 10;
    public static final int DEFAULT_DAILY_SIZE = 15;

    public static final String GANK_DATA_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

}
