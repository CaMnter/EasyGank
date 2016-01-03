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
package com.camnter.easygank.presenter;

import com.camnter.easygank.model.callback.DailyModelCallback;
import com.camnter.easygank.model.impl.DailyModel;

/**
 * Description：MainPresenter
 * Created by：CaMnter
 * Time：2016-01-03 18:09
 */
public class MainPresenter extends BasePresenter {

    private DailyModel dailyModel;

    public MainPresenter() {
        this.dailyModel = new DailyModel(new DailyModelCallback() {
            @Override
            protected void getDailySuccess() {

            }

            @Override
            public void failure(String msg) {

            }
        });
    }

    /**
     * Activity 销毁时，请调用次方法销毁对应的 Presenter
     */
    @Override
    protected void onDestroy() {

    }

    /**
     * 查询每日数据
     *
     * @param year  year
     * @param month month
     * @param day   day
     */
    public void getDaily(int year, int month, int day) {
        this.dailyModel.getDaily(year, month, day);
    }

}
