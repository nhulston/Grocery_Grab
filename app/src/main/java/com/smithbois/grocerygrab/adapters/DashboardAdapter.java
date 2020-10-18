package com.smithbois.grocerygrab.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.smithbois.grocerygrab.fragments.ARFragment;
import com.smithbois.grocerygrab.fragments.ShoppingListFragment;

public class DashboardAdapter extends FragmentPagerAdapter {

    private Context context;
    int totalTabs;

    public DashboardAdapter(FragmentManager fm, Context context, int totalTabs){
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    public Fragment getItem(int position){
        switch(position){
            case 0:
                ShoppingListFragment shoppingListFragment = new ShoppingListFragment();
                return shoppingListFragment;
            case 1:
                ARFragment arFragment = new ARFragment();
                return arFragment;
            default:
                return null;
        }
    }

}
