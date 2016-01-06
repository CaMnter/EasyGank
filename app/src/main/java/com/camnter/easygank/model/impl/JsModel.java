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

import com.camnter.easygank.bean.GankJS;
import com.camnter.easygank.gank.EasyGank;
import com.camnter.easygank.model.IJsModel;

import rx.Observable;

/**
 * Description：JsModel
 * Created by：CaMnter
 * Time：2016-01-06 15:03
 */
public class JsModel implements IJsModel {

    private static final JsModel ourInstance = new JsModel();

    public static JsModel getInstance() {
        return ourInstance;
    }

    private JsModel() {
    }


    /**
     * 分页查询前端数据
     *
     * @param size 数据个数
     * @param page 第几页
     * @return Observable<GankJS>
     */
    @Override
    public Observable<GankJS> getJs(int size, int page) {
        return EasyGank.getInstance().getGankService().getJs(size, page);
    }

}
