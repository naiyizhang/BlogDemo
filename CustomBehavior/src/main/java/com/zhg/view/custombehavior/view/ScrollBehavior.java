package com.zhg.view.custombehavior.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by nyzhang on 16-8-30.
 */
public class ScrollBehavior extends CoordinatorLayout.Behavior<View> {
    public static final String TAG = " ScrollBehavior ";

    public ScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.e(TAG, "directTargetChild." + directTargetChild.getTag());
        return (nestedScrollAxes&ViewCompat.SCROLL_AXIS_VERTICAL) !=0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout,child,target,dx,dy,consumed);
        int scrollY=target.getScrollY();
        child.setScrollY(scrollY);
    }


    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.e(TAG,"dyConsumed."+dyConsumed+",dyUnconsumed."+dyUnconsumed);
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY) {
        ((NestedScrollView)child).fling((int) velocityY);
        return true;
    }
}
