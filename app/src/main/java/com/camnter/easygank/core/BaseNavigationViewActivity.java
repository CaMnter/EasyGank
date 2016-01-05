package com.camnter.easygank.core;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import com.camnter.easygank.R;

/**
 * Description：BaseNavigationViewActivity
 * Created by：CaMnter
 * Time：2016-01-06 00:29
 */
public abstract class BaseNavigationViewActivity extends BaseSwipeRefreshLayout {

    protected DrawerLayout mDrawerLayout;
    protected NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mDrawerLayout = this.findView(R.id.drawerLayout);
        this.mNavigationView = this.findView(R.id.navigation_view);
        if (this.getNavigationItemSelectedListener() != null)
            this.mNavigationView.setNavigationItemSelectedListener(this.getNavigationItemSelectedListener());
    }

    /**
     * Fill in NavigationView.OnNavigationItemSelectedListener
     *
     * @return NavigationView.OnNavigationItemSelectedListener
     */
    protected abstract NavigationView.OnNavigationItemSelectedListener getNavigationItemSelectedListener();

}
