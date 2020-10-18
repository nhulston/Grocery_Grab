package com.smithbois.grocerygrab.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.smithbois.grocerygrab.R;
import com.smithbois.grocerygrab.adapters.DashboardAdapter;
import com.smithbois.grocerygrab.dialogs.CheckoutDialog;

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

        ImageButton btn = findViewById(R.id.cart_button);
        btn.setOnClickListener(v -> {
            Dialog dialog = CheckoutDialog.onCreateDialog(this);
            dialog.getWindow().setLayout(10, 500);
            dialog.show();
        });
    }
}
