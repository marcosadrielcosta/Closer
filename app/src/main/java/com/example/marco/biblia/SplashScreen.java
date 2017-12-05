package com.example.marco.biblia;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.marco.biblia.Perfil.Perfil;

public class SplashScreen extends Activity {
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), Perfil.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}