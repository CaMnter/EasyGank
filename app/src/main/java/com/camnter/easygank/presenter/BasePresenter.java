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

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Description：BasePresenter
 * Created by：CaMnter
 * Time：2016-01-03 18:10
 */
public abstract class BasePresenter {

    private List<Subscription> subscriptions = new ArrayList<>();

    /**
     * Activity 销毁时，请调用次方法销毁对应的 Presenter
     */
    protected abstract void onDestroy();


    private void recycle() {
        for (Subscription subscription : subscriptions) {
            subscription.unsubscribe();
        }
    }

}
