package com.example.ibrahimr.movieum.Screens;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ibrahimr.movieum.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            // Using handler with postDelayed called runnable run method
            @Override
            public void run() {
                Intent goToHomeIntent = new Intent(SplashScreen.this, HomeScreen.class);
                startActivity(goToHomeIntent);
                // close this activity
                finish();
            }
        }, 4*1000); // wait for 5 seconds

    }
}
