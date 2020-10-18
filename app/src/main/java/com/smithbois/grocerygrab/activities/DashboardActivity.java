package com.smithbois.grocerygrab.activities;

import android.app.Dialog;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.smithbois.grocerygrab.R;
import com.smithbois.grocerygrab.adapters.DashboardAdapter;
import com.smithbois.grocerygrab.dialogs.CheckoutDialog;

import org.json.JSONException;

import java.util.Objects;

public class DashboardActivity extends AppCompatActivity {

    TabLayout dashboardTabs;
    ViewPager dashboardPager;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);

        System.out.println("loaded dashboard layout");

        dashboardTabs = findViewById(R.id.dashboard_tab_layout);
        dashboardPager = findViewById(R.id.dashboard_pager);

        dashboardTabs.addTab(dashboardTabs.newTab().setText("Shopping List"));
        dashboardTabs.addTab(dashboardTabs.newTab().setText("Store Map"));

        dashboardTabs.getTabAt(0).setIcon(R.drawable.shopping_list_icon);
        dashboardTabs.getTabAt(1).setIcon(R.drawable.store_map_icon);
        Objects.requireNonNull(dashboardTabs.getTabAt(0).getIcon()).setColorFilter(new BlendModeColorFilter(getResources().getColor(R.color.black), BlendMode.SRC_ATOP));
        Objects.requireNonNull(dashboardTabs.getTabAt(1).getIcon()).setColorFilter(new BlendModeColorFilter(getResources().getColor(R.color.secondaryText), BlendMode.SRC_ATOP));

        final DashboardAdapter adapter = new DashboardAdapter(getSupportFragmentManager(), this, dashboardTabs.getTabCount());
        dashboardPager.setAdapter(adapter);
        dashboardPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(dashboardTabs));
        dashboardTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                dashboardPager.setCurrentItem(tab.getPosition());
                tab.getIcon().setColorFilter(new BlendModeColorFilter(getResources().getColor(R.color.black), BlendMode.SRC_ATOP));

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(new BlendModeColorFilter(getResources().getColor(R.color.secondaryText), BlendMode.SRC_ATOP));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        ImageButton btn = findViewById(R.id.cart_button);
        btn.setOnClickListener(v -> {
            Dialog dialog = null;
            try {
                dialog = CheckoutDialog.onCreateDialog(this);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.getWindow().setLayout(10, 500);
            dialog.show();
        });
    }
}
