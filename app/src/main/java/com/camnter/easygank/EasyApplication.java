package com.camnter.easygank;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Description：EasyApplication
 * Created by：CaMnter
 * Time：2016-01-03 23:14
 */
public class EasyApplication extends Application {
    private static EasyApplication ourInstance = new EasyApplication();

    public static EasyApplication getInstance() {
        return ourInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        Logger.init();
    }
}
