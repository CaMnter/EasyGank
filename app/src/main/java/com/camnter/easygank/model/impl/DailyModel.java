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

package com.camnter.easygank.model.impl;

import com.camnter.easygank.bean.GankDaily;
import com.camnter.easygank.gank.EasyGank;
import com.camnter.easygank.model.IDailyModel;

import rx.Observable;

/**
 * Description：DailyModel
 * Created by：CaMnter
 * Time：2016-01-03 18:02
 */
public class DailyModel implements IDailyModel {

    private static final DailyModel ourInstance = new DailyModel();

    public static DailyModel getInstance() {
        return ourInstance;
    }

    private DailyModel() {
    }

    /**
     * 查询每日数据
     *
     * @param year  year
     * @param month month
     * @param day   day
     * @return Observable<GankDaily>
     */
    @Override
    public Observable<GankDaily> getDaily(int year, int month, int day) {
        return EasyGank.getInstance().getGankService().getDaily(year, month, day);
    }

}
