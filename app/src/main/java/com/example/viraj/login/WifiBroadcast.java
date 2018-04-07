package com.example.viraj.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import im.delight.android.webview.AdvancedWebView;

/**
 * Created by Viraj on 03/04/2018.
 */

public class WifiBroadcast extends BroadcastReceiver {
    AdvancedWebView wb;
    String url = "https://10.1.1.1:8090/httpclient.html";
    private Intent loginIntent;

    @Override
    public void onReceive(final Context context, final Intent intent) {
        WifiManager wf = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        if (wf.isWifiEnabled()){
            loginIntent = new Intent(context.getApplicationContext(),FirstActivity.class);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(loginIntent);
        }



        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final android.net.NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        Toast.makeText(context, "WifiBroadCAst Triggered", Toast.LENGTH_LONG).show();
        if (wifi.isAvailable()) {
            String w = getWifiName(context);
            Toast.makeText(context, "Connected to :)"+w, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, "Wifi not available", Toast.LENGTH_LONG).show();
        }
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

