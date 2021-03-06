package com.example.viraj.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import im.delight.android.webview.AdvancedWebView;

public class FirstActivity extends AppCompatActivity {

    AdvancedWebView wb;
    String url="https://10.1.1.1:8090/httpclient.html";
    ProgressBar p;
     String id="",pssd="";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        p = (ProgressBar)findViewById(R.id.progressBar);
        wb = (AdvancedWebView) findViewById(R.id.wb);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.setMixedContentAllowed(true);
        wb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        id= getDefaults("id",this);
        pssd =getDefaults("pssd",this);
        wb.loadUrl(url);

        wb.setWebViewClient(
                new MyWebViewClient()
        );

        wb.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {

                p.setProgress(progress);
                if (progress == 100) {
                    wb.loadUrl("javascript:(function(){document.getElementById('loginboxtitle').innerHTML='SK returns'})()");
                    wb.loadUrl("javascript:(function(){let paragraph = document.getElementsByTagName('input');for (elt of paragraph){elt.style['backgroundColor']='yellow';}})()");
                    wb.loadUrl("javascript:(function(){var s='"+id+"'; var urn = document.getElementsByName('username');for (elt of urn){elt.value=s;}})()");
                    wb.loadUrl("javascript:(function(){var s='"+pssd+"'; var pssd = document.getElementsByName('password');for (elt of pssd){elt.value=s;}})()");
                    wb.loadUrl("javascript:(function(){var btn= document.getElementsByClassName('button');for (elt of btn){elt.style['color']='green';elt.click();}})()");
         //           Toast.makeText(getApplicationContext(),"Successfully login",Toast.LENGTH_SHORT).show();
                   finish();
                }
            }
        });


    }

    @Override
    public void finish() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.finishAndRemoveTask();
        }
        else {
            super.finish();
        }
    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

    }
    public void setDefaults(String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }


    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }
}
