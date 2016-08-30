package com.zhg.view.custombehavior.view;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

/**
 * Created by nyzhang on 16-8-30.
 */
public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {
    private Interpolator INTERPOLATOR=new FastOutSlowInInterpolator();
    private boolean mIsAnimatingOut=false;//是否正在退出
    public ScrollAwareFABBehavior() {
    }

    public ScrollAwareFABBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes& ViewCompat.SCROLL_AXIS_VERTICAL)!=0||super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if(dyConsumed>0&&!mIsAnimatingOut&&child.getVisibility()==View.VISIBLE){
            animateOut(child);
        }else if(dyConsumed<0&&child.getVisibility()!=View.VISIBLE){
            animateIn(child);
        }
    }

    private void animateIn(FloatingActionButton button) {
        button.setVisibility(View.VISIBLE);
        if(Build.VERSION.SDK_INT>=14){
            ViewCompat.animate(button).translationY(0).setInterpolator(INTERPOLATOR).withLayer()
                    .setListener(null).start();
        }
    }

    private void animateOut(final FloatingActionButton button) {
        if(Build.VERSION.SDK_INT>=14) {
            ViewCompat.animate(button).translationY(button.getHeight() + getBottomMargin(button)).withLayer().setInterpolator(INTERPOLATOR)
                    .setListener(new ViewPropertyAnimatorListener() {
                        @Override
                        public void onAnimationStart(View view) {
                            mIsAnimatingOut = true;
                        }

                        @Override
                        public void onAnimationEnd(View view) {
                            mIsAnimatingOut = false;
                            button.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel(View view) {
                            mIsAnimatingOut = false;
                        }
                    }).start();
        }else{
            button.setVisibility(View.GONE);
        }
    }

    private int getBottomMargin(FloatingActionButton button) {
        int bottomMargin=0;
        ViewGroup.LayoutParams params=button.getLayoutParams();
        if(params instanceof ViewGroup.MarginLayoutParams){
            ViewGroup.MarginLayoutParams marginLayoutParams= (ViewGroup.MarginLayoutParams) params;
            bottomMargin=marginLayoutParams.bottomMargin;
        }
        return bottomMargin;
    }

}
