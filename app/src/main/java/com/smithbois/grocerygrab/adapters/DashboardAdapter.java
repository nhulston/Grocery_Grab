package com.smithbois.grocerygrab.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.smithbois.grocerygrab.fragments.PostTestFragment;
import com.smithbois.grocerygrab.fragments.ShoppingListFragment;
import com.smithbois.grocerygrab.fragments.login.LoginTabFragment;
import com.smithbois.grocerygrab.fragments.login.SignUpTabFragment;

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
                PostTestFragment postTestFragment = new PostTestFragment();
                return postTestFragment;
            case 2:
                ShoppingListFragment shoppingListFragment3 = new ShoppingListFragment();
                return shoppingListFragment3;
            default:
                return null;
        }
    }

}
