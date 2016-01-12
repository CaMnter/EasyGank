package com.camnter.easygank.core;

import android.os.Bundle;

import rx.subscriptions.CompositeSubscription;

/**
 * Description：BusDrawerLayoutActivity
 * Created by：CaMnter
 * Time：2016-01-12 15:19
 */
public abstract class BusDrawerLayoutActivity extends BaseDrawerLayoutActivity {

    public CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mCompositeSubscription = null;
    }

}
