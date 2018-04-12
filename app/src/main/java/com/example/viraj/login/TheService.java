package com.example.viraj.login;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Viraj on 09/04/2018.
 */

public class TheService extends Service {


    public final class TheThread implements Runnable
    {
        int serviceId;
        String ssid = getWifiName(TheService.this);
        TheThread(int serviceId)
        {
            this.serviceId = serviceId;
        }
        @Override
        public void run() {

            synchronized (this)
            {
                try {
                    if(isConnected(100)==false  && ssid.substring(0,5).equalsIgnoreCase("\"ABES")== true) {
                        Intent i = new Intent();
                        i.setClass(TheService.this, FirstActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }
                wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                   stopSelf(this.serviceId);
            }
        }

        private boolean isConnected(int timeOut) {
            InetAddress inetAddress = null;
            try {
                Future<InetAddress> future = Executors.newSingleThreadExecutor().submit(new Callable<InetAddress>() {
                    @Override
                    public InetAddress call() {
                        try {
                            return InetAddress.getByName("google.com");
                        } catch (UnknownHostException e) {
                            return null;
                        }
                    }
                });
                inetAddress = future.get(timeOut, TimeUnit.MILLISECONDS);
                future.cancel(true);
            } catch (InterruptedException e) {
            } catch (ExecutionException e) {
            } catch (TimeoutException e) {
            }
            return inetAddress!=null && !inetAddress.equals("");
        }

        public String getWifiName(Context context) {
            WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if (manager.isWifiEnabled()) {
                WifiInfo wifiInfo = manager.getConnectionInfo();
                if (wifiInfo != null) {
                    NetworkInfo.DetailedState state = WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState());
                    if (state == NetworkInfo.DetailedState.CONNECTED || state == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
                        return wifiInfo.getSSID();
                    }
                }
            }
            return null;
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
