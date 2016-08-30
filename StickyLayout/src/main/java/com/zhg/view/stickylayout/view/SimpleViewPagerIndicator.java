package com.zhg.view.stickylayout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by nyzhang on 16-8-30.
 */
public class SimpleViewPagerIndicator extends LinearLayout {
    private Paint mPaint;
    private int TEXT_COLOR_NORMAL=0xFF000000;
    private int INDICATOR_COLOR= Color.GREEN;
    private String[]mTitles;
    private int mIndicatorColor=INDICATOR_COLOR;

    private int mTabCount;
    private float mTranslationX;
    private int mTabWidth;

    public SimpleViewPagerIndicator(Context context) {
        this(context,null);
    }

    public SimpleViewPagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SimpleViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint=new Paint();
        mPaint.setColor(INDICATOR_COLOR);
        mPaint.setStrokeWidth(9.0F);
    }
    public void setTitles(String[]titles){
        mTitles=titles;
        mTabCount=mTitles.length;
        generateTextView();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTabWidth=w/mTabCount;
    }

    public void scroll(int position, float positionOffset){
        mTranslationX=mTabWidth*(position+positionOffset);
        invalidate();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        canvas.translate(mTranslationX,getHeight()-2);
        canvas.drawLine(0,0,mTabWidth,0,mPaint);
        canvas.restore();
    }

    private void generateTextView() {
        if(getChildCount()>0){
            removeAllViews();
        }
        int count=mTitles.length;
        setWeightSum(count);
        for(int i=0;i<count;i++){
            TextView textView=new TextView(getContext());
            LayoutParams params=new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight=1;
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(TEXT_COLOR_NORMAL);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            textView.setText(mTitles[i]);
            textView.setLayoutParams(params);
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            addView(textView);
        }
    }
}
