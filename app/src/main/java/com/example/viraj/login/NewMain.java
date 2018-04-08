package com.example.viraj.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class NewMain extends AppCompatActivity {

    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);
    t = (TextView)findViewById(R.id.textView);

        checkFirstRun();
    }

     public void login(View view) {
        Intent e = new Intent(NewMain.this,FirstActivity.class);
        startActivity(e);
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public void check(View view) {
        String c = "";
        if(isNetworkConnected())
            c= "Connected";
        else
            c= "Not Connected";
        t.setText(c);
    }

    private void checkFirstRun() {

        final String PREFS_NAME = "MyPrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;

        // Get current version code
        int currentVersionCode = BuildConfig.VERSION_CODE;

        // Get saved version code
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);

        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {

            Toast.makeText(getApplicationContext(),"This is a normal run",Toast.LENGTH_LONG).show();
            // TODO This is just a normal run
            return;

        } else if (savedVersionCode == DOESNT_EXIST) {

            // TODO This is a new install (or the user cleared the shared preferences)

            Toast.makeText(getApplicationContext(),"This is a new install",Toast.LENGTH_LONG).show();

        } else if (currentVersionCode > savedVersionCode) {

            Toast.makeText(getApplicationContext(),"This is an upgrade",Toast.LENGTH_LONG).show();

            // TODO This is an upgrade
        }

        // Update the shared preferences with the current version code
        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
    }


}
