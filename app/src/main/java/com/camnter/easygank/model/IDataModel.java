package com.camnter.easygank.model;

import com.camnter.easygank.bean.GankData;

import rx.Observable;

/**
 * Description：IDataModel
 * 定义DataModel要实现的功能
 * Created by：CaMnter
 * Time：2016-01-06 17:50
 */
public interface IDataModel {
    /**
     * 分页查询( Android、iOS、前端、拓展资源、福利、休息视频 )数据
     *
     * @param type 数据类型
     * @param size 数据个数
     * @param page 第几页
     * @return Observable<GankData>
     */
    Observable<GankData> getData(String type, int size, int page);
}
