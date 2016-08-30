package com.zhg.view.stickylayout.view;

import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import com.zhg.view.stickylayout.R;

/**
 * Created by nyzhang on 16-8-29.
 */
public class StickyLinearLayout extends LinearLayout implements NestedScrollingParent {
    public static final String TAG="StickyLinearLayout";

    private int mTopViewHeight;//顶部view的高度
    private OverScroller mScroller;
    private View mTopView;
    private View mNav;//导航栏
    private ViewPager mViewPager;
    private VelocityTracker mVelocityTracker;
    private int mTouchSlop;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    public StickyLinearLayout(Context context) {
        this(context,null);
    }

    public StickyLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StickyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        mScroller=new OverScroller(context);
        mVelocityTracker=VelocityTracker.obtain();
        mTouchSlop= ViewConfiguration.get(context).getScaledTouchSlop();
        mMaximumVelocity=ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
        mMinimumVelocity=ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTopView=findViewById(R.id.id_sticky_layout_top_view);
        mNav=findViewById(R.id.id_sticky_layout_indicator);
        View view=findViewById(R.id.id_sticky_layout_viewPager);
        if(!(view instanceof ViewPager)){
            throw new RuntimeException("id_sticky_layout_viewPager should used by viewpager ");
        }
        mViewPager= (ViewPager) view;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTopViewHeight=mTopView.getMeasuredHeight();
        Log.e("info","mTopViewHeight="+mTopViewHeight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewGroup.LayoutParams params = mViewPager.getLayoutParams();
        params.height=getMeasuredHeight()-mNav.getMeasuredHeight();
        setMeasuredDimension(getMeasuredWidth(),getMeasuredHeight()+mTopView.getMeasuredHeight());
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.i(TAG,"nestScrollAxes="+nestedScrollAxes);
        return (nestedScrollAxes&ViewCompat.SCROLL_AXIS_VERTICAL)!=0;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        Log.i(TAG,"==onNestedScrollAccepted==");
    }

    @Override
    public void onStopNestedScroll(View target) {
        Log.i(TAG,"==onStopNestedScroll==");
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.i(TAG,"==onNestedScroll==");
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        boolean hiddenTop=dy>0&&getScrollY()<mTopViewHeight;
        boolean showTop=dy<0&&getScrollY()>0&&!ViewCompat.canScrollVertically(target,-1);
        if(hiddenTop||showTop){
            scrollBy(0,dy);
            consumed[1]=dy;
        }
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        Log.i(TAG,"==onNestedFling==");
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        if(getScrollY()>=mTopViewHeight)return false;
        fling((int) velocityY);
        return true;
    }

    private void fling(int velocityY) {
        mScroller.fling(0,getScrollY(),0,velocityY,0,0,0,mTopViewHeight);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(0,mScroller.getCurrY());
            invalidate();
        }
    }


    @Override
    public int getNestedScrollAxes() {
        Log.e(TAG,"==getNestedScrollAxes==");
        return ViewCompat.SCROLL_AXIS_VERTICAL;
    }
}
