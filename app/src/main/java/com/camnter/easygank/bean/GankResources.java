package com.camnter.easygank.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Description：GankResources （扩展资源）
 * Created by：CaMnter
 * Time：2016-01-06 14:45
 */
public class GankResources extends Error implements Serializable {

    @SerializedName("results")
    public ArrayList<GankData> results;

}
