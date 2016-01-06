package com.camnter.easygank.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Description：GankVideo （休息视频）
 * Created by：CaMnter
 * Time：2016-01-06 14:49
 */
public class GankVideo extends Error implements Serializable {

    @SerializedName("results")
    public ArrayList<GankData> results;

}
