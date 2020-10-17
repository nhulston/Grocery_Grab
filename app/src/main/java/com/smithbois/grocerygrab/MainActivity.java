package com.smithbois.grocerygrab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.smithbois.grocerygrab.activities.LoginActivity;
import com.smithbois.grocerygrab.activities.DashboardActivity;

public class MainActivity extends AppCompatActivity {

    public static int SPLASH_SCREEN_DURATION = 5000;

    Animation topAnim, bottomAnim;
    ImageView splashLogo;
    TextView versionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // load the splash screen

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashscreen_layout);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        splashLogo = findViewById(R.id.splash_logo);
        versionText = findViewById(R.id.version_text);

        splashLogo.setAnimation(topAnim);
        versionText.setAnimation(bottomAnim);

    }

    @Override
    protected void onStart() {
        super.onStart();
        // check if user is signed in
        int result = 0;
        if (result == 1) {
            Handler h = new Handler();
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            };
            h.postDelayed(r, SPLASH_SCREEN_DURATION);
        } else {
            Handler h = new Handler();
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            };
            h.postDelayed(r, SPLASH_SCREEN_DURATION);

        }
    }
}