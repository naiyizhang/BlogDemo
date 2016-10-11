package com.zhg.demo.translucentstatusbar;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }



    private void windowTranlucent(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @OnClick({R.id.id_btn1, R.id.id_btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_btn1:
                Intent intent=new Intent(this,ImageActivity.class);
                startActivity(intent);
                break;
            case R.id.id_btn2:
                intent=new Intent(this,TextActivity.class);
                startActivity(intent);
                break;
        }
    }
}
