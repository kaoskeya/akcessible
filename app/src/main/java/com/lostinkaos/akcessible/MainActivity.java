package com.lostinkaos.akcessible;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    public static String DEBUG_TAG = "[Akcessible App]";
    SharedPreferences sharedPreferences;
    Spinner languageSpinner;
    FloatingMessage floatingMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("com.lostinkaos.akcessible", MODE_APPEND);

        sharedPreferences.getString("language", "hindi");

        languageSpinner = (Spinner) findViewById(R.id.languageSpinner);
        String[] codes = getResources().getStringArray(R.array.languages_code);
        int i = 0;
        for(String code: codes) {
            if( sharedPreferences.getString("language", "hindi").equals(code) ) {
                languageSpinner.setSelection(i);
            }
            i++;
        }

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sharedPreferences.edit().putString("language", getResources().getStringArray(R.array.languages_code)[position]).apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sharedPreferences.edit().putString("language", "hindi").apply();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.set_accessibility:
                Intent i = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivityForResult(i, 0);
                return true;
            case R.id.enable_float:
                Intent intent = new Intent(MainActivity.this, FloatingFaceBubbleService.class);
                startService(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
