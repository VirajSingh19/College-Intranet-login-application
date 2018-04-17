package com.example.viraj.login;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
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
 * Created by Viraj on 17/04/2018.
 */

public class Service2  extends Service{

int sid;
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(Service2.this,"onCreate()",Toast.LENGTH_SHORT).show();
     }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       sid =startId;
        login();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(Service2.this,"Service Destroyed",Toast.LENGTH_SHORT).show();
    }




    void login()
    {
        if(isNetworkAvailable()==true) {

            if(isConnected(100)==false)
            {
                String ssid= getWifiName(Service2.this);

                if(ssid.substring(0,5).equalsIgnoreCase("\"ABES")== true)
                {
                    Intent i = new Intent();
                    i.setClass(Service2.this, FirstActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                }
            }
        }

        stopSelf(sid);
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
