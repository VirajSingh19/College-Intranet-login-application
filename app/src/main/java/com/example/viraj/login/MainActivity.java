package com.example.viraj.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import im.delight.android.webview.AdvancedWebView;

public class MainActivity extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     btn = (Button)findViewById(R.id.b);
        Intent e = new Intent(MainActivity.this, FirstActivity.class);
        startActivity(e);

    }

    public void btn(View view) {
        Intent e = new Intent(MainActivity.this, FirstActivity.class);
        startActivity(e);
    }

    public void txt(View view) {

        Intent e = new Intent(MainActivity.this, FirstActivity.class);
        startActivity(e);

    }
    //android.intent.action.BOOT_COMPLETED
    // <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
   // <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

}
