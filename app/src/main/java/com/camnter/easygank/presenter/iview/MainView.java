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

package com.camnter.easygank.presenter.iview;

import com.camnter.easygank.bean.BaseGankData;
import com.camnter.easygank.bean.GankDaily;
import com.camnter.easygank.core.mvp.MvpView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：MainView
 * Created by：CaMnter
 * Time：2016-01-04 16:38
 */
public interface MainView extends MvpView {

    /**
     * 查询 每日干货 成功
     *
     * @param dailyData dailyData
     * @param refresh   是否刷新
     */
    void onGetDailySuccess(List<GankDaily> dailyData, boolean refresh);

    /**
     * 查询 ( Android、iOS、前端、拓展资源、福利、休息视频 ) 成功
     *
     * @param data    data
     * @param refresh 是否刷新
     */
    void onGetDataSuccess(List<BaseGankData> data, boolean refresh);


    /**
     * 切换数据源成功
     *
     * @param type type
     */
    void onSwitchSuccess(int type);

    /**
     * 获取每日详情数据
     *
     * @param title  title
     * @param detail detail
     */
    void getDailyDetail(String title, ArrayList<ArrayList<BaseGankData>> detail);

}
