package com.camnter.easygank.gank;

import android.util.SparseArray;

import com.camnter.easygank.R;

import java.util.HashMap;

/**
 * Description：GankTypeDict
 * Created by：CaMnter
 * Time：2016-01-06 16:49
 */
public class GankTypeDict {

    public static final int DONT_SWITCH = -1;

    public static int[] menuIds = new int[]{
            R.id.navigation_daily,
            R.id.navigation_welfare,
            R.id.navigation_android,
            R.id.navigation_ios,
            R.id.navigation_js,
            R.id.navigation_video,
            R.id.navigation_resources
    };
    public static final SparseArray<GankType> menuId2TypeDict = new SparseArray<>();
    public static final HashMap<GankType, String> type2UrlTypeDict = new HashMap<>();

    static {
        menuId2TypeDict.put(R.id.navigation_daily, GankType.daily);
        menuId2TypeDict.put(R.id.navigation_welfare, GankType.welfare);
        menuId2TypeDict.put(R.id.navigation_android, GankType.android);
        menuId2TypeDict.put(R.id.navigation_ios, GankType.ios);
        menuId2TypeDict.put(R.id.navigation_js, GankType.js);
        menuId2TypeDict.put(R.id.navigation_resources, GankType.resources);
        menuId2TypeDict.put(R.id.navigation_video, GankType.video);


        type2UrlTypeDict.put(GankType.welfare, "福利");
        type2UrlTypeDict.put(GankType.android, "Android");
        type2UrlTypeDict.put(GankType.ios, "iOS");
        type2UrlTypeDict.put(GankType.video, "休息视频");
        type2UrlTypeDict.put(GankType.resources, "拓展资源");
        type2UrlTypeDict.put(GankType.js, "前端");
    }
}
