package com.smithbois.grocerygrab.activities;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.smithbois.grocerygrab.R;
import com.smithbois.grocerygrab.adapters.LoginAdapter;

public class LoginActivity extends AppCompatActivity {

    private ConstraintLayout loginLayout;
    TabLayout loginTabLayout;
    ViewPager loginViewPager;
    FloatingActionButton fb, google, phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //mAuth = FirebaseAuth.getInstance();

        //set content view to login layout
        setContentView(R.layout.login_layout);

        // transition login layout into view
        loginLayout = findViewById(R.id.login_layout);
        ((ViewGroup) findViewById(R.id.login_layout_parent)).getLayoutTransition().enableTransitionType(LayoutTransition.CHANGE_APPEARING);
        ((ViewGroup) findViewById(R.id.login_layout_parent)).getLayoutTransition().setDuration(750);

        Handler h = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                loginLayout.setVisibility(View.VISIBLE);
            }
        };
        h.postDelayed(r, 1000);

        // set up login tab layout
        loginTabLayout = findViewById(R.id.login_tab_layout);
        loginViewPager = findViewById(R.id.login_pager);
        fb = findViewById(R.id.fab_facebook);
        google = findViewById(R.id.fab_google);
        phone = findViewById(R.id.fab_phone);

        loginTabLayout.addTab(loginTabLayout.newTab().setText("Log In"));
        loginTabLayout.addTab(loginTabLayout.newTab().setText("Sign Up"));
        loginTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), this, loginTabLayout.getTabCount());
        loginViewPager.setAdapter(adapter);
        loginViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(loginTabLayout));
        loginTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                loginViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

}
