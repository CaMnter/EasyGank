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

import com.camnter.easygank.utils.annotation.Exclude;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Description：MapUtils
 * Created by：CaMnter
 * Time：2016-01-03 15:11
 */
public class MapUtils {

    public static Map<String, Object> getFieldValue(Object o) {
        Map<String, Object> mapValue = new HashMap<>();
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            Exclude exclude = field.getAnnotation(Exclude.class);
            if (exclude != null) {
                continue;
            }
            //针对gson
            SerializedName serializedName = field.getAnnotation(SerializedName.class);
            String key;
            if (serializedName != null) {
                key = serializedName.value();
            } else {
                key = field.getName();
            }
            Object value = null;
            try {
                value = field.get(o);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value == null) continue;

            mapValue.put(key, value);
        }
        return mapValue;
    }

}
