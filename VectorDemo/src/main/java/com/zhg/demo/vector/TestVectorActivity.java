package com.zhg.demo.vector;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by nyzhang on 2016/7/18.
 */
public class TestVectorActivity extends AppCompatActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);//开启Appcompat类支持vector图片资源

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_vector);
        ImageView imageView= (ImageView) findViewById(R.id.id_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView1= (ImageView) v;
                AnimatedVectorDrawableCompat drawable= (AnimatedVectorDrawableCompat) imageView1.getDrawable();
                drawable.start();
            }
        });
    }
}
