/*
 * Copyright 2014 Google Inc. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.camnter.easygank.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.camnter.easygank.R;

/**
 * Description：MultiSwipeRefreshLayout
 * Created by：CaMnter
 * Time：2016-01-04 23:39
 */
public class MultiSwipeRefreshLayout extends SwipeRefreshLayout {
    private CanChildScrollUpCallback mCanChildScrollUpCallback;

    private Drawable mForegroundDrawable;

    public MultiSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public MultiSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        final TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MultiSwipeRefreshLayout, 0, 0);

        this.mForegroundDrawable = a.getDrawable(R.styleable.MultiSwipeRefreshLayout_foreground);
        if (this.mForegroundDrawable != null) {
            this.mForegroundDrawable.setCallback(this);
            setWillNotDraw(false);
        }

        a.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (this.mForegroundDrawable != null) {
            this.mForegroundDrawable.setBounds(0, 0, w, h);
        }
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);
        if (this.mForegroundDrawable != null) {
            this.mForegroundDrawable.draw(canvas);
        }
    }

    public void setCanChildScrollUpCallback(CanChildScrollUpCallback canChildScrollUpCallback) {
        this.mCanChildScrollUpCallback = canChildScrollUpCallback;
    }

    public static interface CanChildScrollUpCallback {
        public boolean canSwipeRefreshChildScrollUp();
    }

    @Override
    public boolean canChildScrollUp() {
        if (mCanChildScrollUpCallback != null) {
            return mCanChildScrollUpCallback.canSwipeRefreshChildScrollUp();
        }
        return super.canChildScrollUp();
    }

}
