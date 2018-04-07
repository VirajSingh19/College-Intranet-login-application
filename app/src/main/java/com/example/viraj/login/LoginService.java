package com.example.viraj.login;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Viraj on 07/04/2018.
 */

public class LoginService extends Service{


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)//gets executed whenever we start a service!
    {
        Toast.makeText(getApplicationContext(),"Service Started",Toast.LENGTH_LONG).show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"Service stopped after 4 seconds",Toast.LENGTH_LONG).show();
                stopSelf();
            }
        }, 4000);

        return super.onStartCommand(intent, flags, startId);

    }
}
