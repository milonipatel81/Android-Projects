package com.example.edithapp.tabbar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class PackageTabAdapter extends FragmentStatePagerAdapter {
    TabLayout tabLayout;
    public PackageTabAdapter(FragmentManager fm, TabLayout _tabLayout) {
        super(fm);
        this.tabLayout = _tabLayout;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new TabCategoryFragment();
        }
        else if (position == 1)
        {
            fragment = new TabSpendingFragment();
        }
        else if (position == 2)
        {
            fragment = new TabAccountsFragment();
        }else if (position == 3)
        {
            fragment = new TabTransactionFragment();
        }
        return fragment;
    }
    @Override
    public int getCount() {
        return 4;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Category";
        }
        else if (position == 1)
        {
            title = "Spending";
        }else if (position == 2)
        {
            title = "Accounts";
        }else if (position == 3)
        {
            title = "Transections";
        }
        return title;
    }
}
