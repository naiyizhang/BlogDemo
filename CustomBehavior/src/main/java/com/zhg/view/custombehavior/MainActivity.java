package com.zhg.view.custombehavior;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.id_textView)
    public void onClick() {
        Intent intent = new Intent(this, TextViewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.id_scrollView)
    public void goToScrollView() {
        Intent intent = new Intent(this, ScrollActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.id_fab)
    public void goToFab() {
        Intent intent=new Intent(this,FloatingActionButtonActivity.class);
        startActivity(intent);
    }
}
