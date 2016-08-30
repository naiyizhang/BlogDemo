package com.zhg.view.stickylayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zhg.view.stickylayout.view.SimpleViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SimpleViewPagerIndicator mIndicator;
    private ViewPager mViewPager;
    private List<TabFragment> mFragList;
    private List<String> mTitles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init() {
        mFragList=new ArrayList<>();
        mTitles=new ArrayList<>();
        for(int i=0;i<3;i++){
            mFragList.add(TabFragment.newInstance());
            mTitles.add("标签"+i);
        }

        mIndicator= (SimpleViewPagerIndicator) findViewById(R.id.id_sticky_layout_indicator);
        mViewPager= (ViewPager) findViewById(R.id.id_sticky_layout_viewPager);
        mIndicator.setTitles(mTitles.toArray(new String[]{}));
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragList.get(position);
            }

            @Override
            public int getCount() {
                return mFragList.size();
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mIndicator.scroll(position,positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
