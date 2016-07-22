package com.zhg.demo.aidl.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.zhg.demo.aidl.ICalcInterface;

/**
 * Created by nyzhang on 2016/7/22.
 */
public class CalcService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("info","aidl.Server.onBind()=============");
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Info","aidl.Server.onCreate()========");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("info","aidl.Server.onStartCommand()========");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("info","aidl.Server.onDestroy()=========");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("info","aidl.Server.onUnbind()=========");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e("info","aidl.Server.onRebind()========");
    }

    private ICalcInterface.Stub myBinder=new ICalcInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int add(int a, int b) throws RemoteException {
            return a+b;
        }

        @Override
        public int sub(int a, int b) throws RemoteException {
            return a-b;
        }
    };

}
