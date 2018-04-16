package com.example.viraj.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class SharedPref extends AppCompatActivity {
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    EditText id;
    EditText pssd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref);

        //Window w =getWindow();
        //w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        id=(EditText)findViewById(R.id.editText);
        pssd=(EditText)findViewById(R.id.editText2);

    }

    public void save(View view) {
        setDefaults("id",id.getText().toString(),this);
        setDefaults("pssd",pssd.getText().toString(),this);
        Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
    }

    public void ba(View view) {
        if(id.getText().toString().length()!=0 && pssd.getText().toString().length()!=0) {
            Intent e = new Intent(SharedPref.this, NewMain.class);
            startActivity(e);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"One or more fields are empty",Toast.LENGTH_SHORT).show();
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
