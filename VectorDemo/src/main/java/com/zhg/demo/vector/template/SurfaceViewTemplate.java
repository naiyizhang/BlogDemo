package com.zhg.demo.vector.template;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by nyzhang on 2016/7/18.
 */
public class SurfaceViewTemplate extends SurfaceView {
    private SurfaceHolder holder;//控制surfaceView的holder

    private Canvas mCanvas;//与SurfaceHolder绑定的canvas，用于绘图

    private boolean isRunning;//控制线程是否运行

    private Thread thread;//绘图Thread

    public SurfaceViewTemplate(Context context) {
        this(context,null);
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        holder=getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                isRunning=true;
                thread=new Thread(){
                    @Override
                    public void run() {
                        while(isRunning){
                            draw();
                        }
                    }
                };
                thread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                isRunning=false;
            }
        });

        holder.setFormat(PixelFormat.TRANSLUCENT);//设置画布背景半透明

        setZOrderOnTop(true);//设置置顶
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);

    }

    private void draw() {
        try {
            mCanvas=holder.lockCanvas();
            if(mCanvas!=null){
                //draw something
            }
        } finally {
            holder.unlockCanvasAndPost(mCanvas);
        }
    }


}
