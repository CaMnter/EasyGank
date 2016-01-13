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

package com.camnter.easygank.utils;

import android.util.Log;

import com.anupcowkur.reservoir.Reservoir;
import com.anupcowkur.reservoir.ReservoirDeleteCallback;
import com.anupcowkur.reservoir.ReservoirGetCallback;
import com.anupcowkur.reservoir.ReservoirPutCallback;

import java.lang.reflect.Type;

import rx.Observable;

/**
 * Description：ReservoirRxUtils
 * Created by：CaMnter
 * Time：2016-01-13 14:06
 */
public class ReservoirUtils {

    private static final String TAG = "ReservoirUtils";

    private static ReservoirUtils instance;

    public synchronized static ReservoirUtils getInstance() {
        if (instance == null) {
            instance = new ReservoirUtils();
        }
        return instance;
    }

    public void put(String key, Object object) {
        if (object == null) return;
        Reservoir.putAsync(key, object, new ReservoirPutCallback() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "Put success: key=" + key + " object=" + object.getClass());
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        });
    }

    public boolean contains(String key) {
        try {
            return Reservoir.contains(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void delete(String key) {
        if (this.contains(key))
            Reservoir.deleteAsync(key);
    }

    public void refresh(String key, Object object) {
        if (this.contains(key)) {
            Reservoir.deleteAsync(key, new ReservoirDeleteCallback() {
                @Override
                public void onSuccess() {
                    ReservoirUtils.this.put(key, object);
                }

                @Override
                public void onFailure(Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            ReservoirUtils.this.put(key, object);
        }
    }

    public <T> Observable<T> get(String key, Class<T> clazz) {
        return Reservoir.getAsync(key, clazz);

    }

    public <T> Observable<T> get(Class<T> clazz) {
        String key = clazz.getSimpleName();
        return get(key, clazz);
    }

    public <T> void get(final String key, final Type typeOfT, final ReservoirGetCallback<T> callback) {
        Reservoir.getAsync(key, typeOfT, callback);
    }


}
