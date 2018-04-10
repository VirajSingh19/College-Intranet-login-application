package com.example.viraj.login;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Viraj on 09/04/2018.
 */

public class TheService extends Service {


    public final class TheThread implements Runnable
    {
        int serviceId;
        TheThread(int serviceId)
        {
            this.serviceId = serviceId;
        }
        @Override
        public void run() {

            synchronized (this)
            {
                try {
                    wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stopSelf(this.serviceId);
            }
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(TheService.this,"Service Started",Toast.LENGTH_LONG).show();
        Thread e = new Thread(new TheThread(startId));
        e.start();
        return START_STICKY;


    }

    @Override
    public void onDestroy() {
        Toast.makeText(TheService.this,"Service Destroyed",Toast.LENGTH_LONG).show();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {return null;}
}
