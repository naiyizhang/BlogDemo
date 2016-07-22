package com.zhg.demo.aidl.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.zhg.demo.aidl.ICalcInterface;

public class MainActivity extends AppCompatActivity {
    private ICalcInterface iCalcInterface;

    private ServiceConnection mServiceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("info","aidl.Client.onServiceConnected()======");
            iCalcInterface=ICalcInterface.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("info","aidl.Client.onServiceDisconnected()==========");
            iCalcInterface=null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.id_bindService).setOnClickListener(v->{
            Intent intent=new Intent("com.zhg.demo.aidl.service");
            bindService(intent,mServiceConnection , Context.BIND_AUTO_CREATE);

        });

        findViewById(R.id.id_unBindService).setOnClickListener(v->unbindService(mServiceConnection));
        findViewById(R.id.id_add).setOnClickListener(v->{
            if(iCalcInterface!=null){
                try {
                    int result=iCalcInterface.add(100,200);
                    Toast.makeText(MainActivity.this,"result="+result,Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            }else{
                Toast.makeText(MainActivity.this,R.string.tips,Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.id_sub).setOnClickListener(v->{
            if(iCalcInterface!=null){
                try {
                    int result=iCalcInterface.sub(300,100);
                    Toast.makeText(MainActivity.this,"result="+result,Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            }else{
                Toast.makeText(MainActivity.this,R.string.tips,Toast.LENGTH_SHORT).show();
            }
        });
    }


}
