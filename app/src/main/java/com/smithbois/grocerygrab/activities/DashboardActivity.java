package com.smithbois.grocerygrab.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.smithbois.grocerygrab.R;
import com.smithbois.grocerygrab.adapters.DashboardAdapter;
import com.smithbois.grocerygrab.adapters.LoginAdapter;
import com.smithbois.grocerygrab.util.Cart;

public class DashboardActivity extends AppCompatActivity {

    TabLayout dashboardTabs;
    ViewPager dashboardPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);

        System.out.println("loaded dashboard layout");

        dashboardTabs = findViewById(R.id.dashboard_tab_layout);
        dashboardPager = findViewById(R.id.dashboard_pager);

        dashboardTabs.addTab(dashboardTabs.newTab().setText("Tab 1"));
        dashboardTabs.addTab(dashboardTabs.newTab().setText("Tab 2"));
        dashboardTabs.addTab(dashboardTabs.newTab().setText("Tab 3"));

        final DashboardAdapter adapter = new DashboardAdapter(getSupportFragmentManager(), this, dashboardTabs.getTabCount());
        dashboardPager.setAdapter(adapter);
        dashboardPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(dashboardTabs));
        dashboardTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                dashboardPager.setCurrentItem(tab.getPosition());
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
