package com.example.viraj.login;

import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import im.delight.android.webview.AdvancedWebView;

public class Attendance extends AppCompatActivity {

    private ProgressBar p;
    AdvancedWebView wb;
    String url="https://192.168.1.24/student/login/login.jsp";
    String id = "2015bit1077";
    String pssd = "nikhil";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        p = (ProgressBar)findViewById(R.id.progressBar1);
        wb = (AdvancedWebView)findViewById(R.id.wb1);

        wb.getSettings().setJavaScriptEnabled(true);
        wb.setMixedContentAllowed(true);
        wb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wb.loadUrl(url);
        wb.setWebViewClient(
                new Attendance.MyWebViewClient()
        );

        wb.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                p.setProgress(progress);
                if (progress == 100) {
                    wb.loadUrl("javascript:(function(){var s='2015bit1077'; var urn = document.getElementsByName('userName');for (elt of urn){elt.value=s;}})()");
                    wb.loadUrl("javascript:(function(){var s='nikhil'; var pssd = document.getElementsByName('password');for (elt of pssd){elt.value=s;}})()");
                 // wb.loadUrl("https://192.168.1.24/student/leaveAttendance/attndcDtlHomePage.html");


                    wb.setWebChromeClient(new WebChromeClient() {
                        public void onProgressChanged(WebView view, int progress) {
                            p.setProgress(progress);

                        }
                    });





                }
            }
        });


        wb.loadUrl("javascript:(function(){submitAction(this)})()");




    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

    }


}
