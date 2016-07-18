package com.zhg.demo.vector;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by nyzhang on 2016/7/18.
 */
public class TestVectorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_vector);
        ImageView imageView= (ImageView) findViewById(R.id.id_image);

        imageView.setOnClickListener(v -> {
            ImageView imageView1= (ImageView) v;
            AnimatedVectorDrawable drawable= (AnimatedVectorDrawable) imageView1.getDrawable();
            drawable.start();
        });
    }
}
