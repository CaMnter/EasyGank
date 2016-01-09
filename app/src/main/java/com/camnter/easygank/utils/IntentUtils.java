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

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Description：IntentUtil
 * Created by：CaMnter
 * Time：2015-12-12 18:22
 */
public class IntentUtils {

    public static boolean hasIntent(Intent intent) {
        return intent != null;
    }

    public static boolean hasExtra(Intent intent, String key) {
        return intent.hasExtra(key);
    }

    public static boolean getBooleanExtra(Intent intent, String name, boolean defaultValue) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return defaultValue;
        return intent.getBooleanExtra(name, defaultValue);
    }

    public static byte getByteExtra(Intent intent, String name, byte defaultValue) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return defaultValue;
        return intent.getByteExtra(name, defaultValue);
    }

    public static short getShortExtra(Intent intent, String name, short defaultValue) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return defaultValue;
        return intent.getShortExtra(name, defaultValue);
    }

    public static char getCharExtra(Intent intent, String name, char defaultValue) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return defaultValue;
        return intent.getCharExtra(name, defaultValue);
    }

    public static int getIntExtra(Intent intent, String name, int defaultValue) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return defaultValue;
        return intent.getIntExtra(name, defaultValue);
    }

    public long getLongExtra(Intent intent, String name, long defaultValue) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return defaultValue;
        return intent.getLongExtra(name, defaultValue);
    }

    public static float getFloatExtra(Intent intent, String name, float defaultValue) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return defaultValue;
        return intent.getFloatExtra(name, defaultValue);
    }

    public static double getDoubleExtra(Intent intent, String name, double defaultValue) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return defaultValue;
        return intent.getDoubleExtra(name, defaultValue);
    }

    public static String getStringExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getStringExtra(name);
    }

    public static CharSequence getCharSequenceExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getCharSequenceExtra(name);
    }

    public static <T extends Parcelable> T getParcelableExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getParcelableExtra(name);
    }

    public static Parcelable[] getParcelableArrayExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getParcelableArrayExtra(name);
    }

    public <T extends Parcelable> ArrayList<T> getParcelableArrayListExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getParcelableArrayListExtra(name);
    }

    // ********** Serializable **********//

    public static Serializable getSerializableExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getSerializableExtra(name);
    }

    public static ArrayList<Integer> getIntegerArrayListExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getIntegerArrayListExtra(name);
    }

    public ArrayList<String> getStringArrayListExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getStringArrayListExtra(name);
    }

    public static ArrayList<CharSequence> getCharSequenceArrayListExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getCharSequenceArrayListExtra(name);
    }

    public static String[] getStringArrayExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getStringArrayExtra(name);
    }

    // ********** Serializable **********//


    public static boolean[] getBooleanArrayExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getBooleanArrayExtra(name);
    }

    public static byte[] getByteArrayExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getByteArrayExtra(name);
    }

    public static short[] getShortArrayExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getShortArrayExtra(name);
    }

    public static char[] getCharArrayExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getCharArrayExtra(name);
    }

    public static int[] getIntArrayExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getIntArrayExtra(name);
    }

    public static long[] getLongArrayExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getLongArrayExtra(name);
    }

    public static float[] getFloatArrayExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getFloatArrayExtra(name);
    }

    public static double[] getDoubleArrayExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getDoubleArrayExtra(name);
    }

    public static CharSequence[] getCharSequenceArrayExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getCharSequenceArrayExtra(name);
    }

    public static Bundle getBundleExtra(Intent intent, String name) {
        if (!hasIntent(intent) || !hasExtra(intent, name)) return null;
        return intent.getBundleExtra(name);
    }

}
