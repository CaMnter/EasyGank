package com.camnter.easygank.model.impl;

import com.camnter.easygank.bean.GankData;
import com.camnter.easygank.gank.EasyGank;
import com.camnter.easygank.model.IDataModel;

import rx.Observable;

/**
 * Description：DataModel
 * Created by：CaMnter
 * Time：2016-01-06 17:50
 */
public class DataModel implements IDataModel {

    private static final DataModel ourInstance = new DataModel();

    public static DataModel getInstance() {
        return ourInstance;
    }

    private DataModel() {
    }

    /**
     * 分页查询( Android、iOS、前端、拓展资源、福利、休息视频 )数据
     *
     * @param type 数据类型
     * @param size 数据个数
     * @param page 第几页
     * @return Observable<GankData>
     */
    @Override
    public Observable<GankData> getData(String type, int size, int page) {
        return EasyGank.getInstance().getGankService().getData(type, size, page);
    }
}
