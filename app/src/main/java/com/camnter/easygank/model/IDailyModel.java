package com.camnter.easygank.model;

import com.camnter.easygank.bean.GankDaily;

import rx.Observable;

/**
 * Description：IDailyModel
 * Created by：CaMnter
 * Time：2016-01-06 17:57
 */
public interface IDailyModel {
    /**
     * 查询每日数据
     *
     * @param year  year
     * @param month month
     * @param day   day
     * @return Observable<GankDaily>
     */
    Observable<GankDaily> getDaily(int year, int month, int day);
}
