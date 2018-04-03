package com.example.viraj.login;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import im.delight.android.webview.AdvancedWebView;

public class FirstActivity extends AppCompatActivity {

    AdvancedWebView wb;
    String url = "https://10.1.1.1:8090/httpclient.html";
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        wb = (AdvancedWebView) findViewById(R.id.wb);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.setMixedContentAllowed(true);
        wb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);


        wb.loadUrl(url);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                wb.loadUrl("javascript:(function(){document.getElementById('loginboxtitle').innerHTML='SK returns'})()");
                wb.loadUrl("javascript:(function(){let paragraph = document.getElementsByTagName('input');for (elt of paragraph){elt.style['backgroundColor']='yellow';}})()");
                wb.loadUrl("javascript:(function(){var s='2015bit1077'; var urn = document.getElementsByName('username');for (elt of urn){elt.value=s;}})()");
                wb.loadUrl("javascript:(function(){var s='baja'; var pssd = document.getElementsByName('password');for (elt of pssd){elt.value=s;}})()");
                wb.loadUrl("javascript:(function(){var btn= document.getElementsByClassName('button');for (elt of btn){elt.style['color']='green';elt.click();}})()");
                Toast.makeText(getApplicationContext(),"Successfully login",Toast.LENGTH_LONG).show();
                finish();
            }
        }, 4000);

        wb.setWebViewClient(
                new MyWebViewClient()
        );
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

    }




}
