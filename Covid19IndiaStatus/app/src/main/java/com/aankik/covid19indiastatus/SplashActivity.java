package com.aankik.covid19indiastatus;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(2 * 1000);
                    if(isOnline()){
                    Intent i = new Intent(getBaseContext(), FrontView.class);
                    startActivity(i);

                    finish();}
                    else{
                        SplashActivity.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Toast.makeText(SplashActivity.this,
                                        "No Internet Connection.", 3000).show();
                            }
                        });
                        finish();
                    }
                } catch (Exception e) {
                }
            }
        };
        background.start();
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
