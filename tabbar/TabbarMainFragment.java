package com.example.edithapp.tabbar;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edithapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;


public class TabbarMainFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    private PackageTabAdapter adapter;
    public FloatingActionButton floatingActionButton;

    public TabbarMainFragment() { }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // create tab fragment
    private void createTabFragment(){
        adapter = new PackageTabAdapter(getActivity().getSupportFragmentManager(), tabLayout);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        TextView tabOne = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.tabbar_custom_tab, null);
        tabOne.setText("CATEGORY");
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.tabbar_custom_tab, null);
        tabTwo.setText("SPENDING");
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.tabbar_custom_tab, null);
        tabThree.setText("ACCOUNTS");
        tabLayout.getTabAt(2).setCustomView(tabThree);


        TextView tabFour = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.tabbar_custom_tab, null);
        tabFour.setText("TRANSECTIONS");
        tabLayout.getTabAt(3).setCustomView(tabFour);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //Toast.makeText(getContext(), tab.getText().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.tabbar_fragment_main, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(2);
        tabLayout = (TabLayout) view.findViewById(R.id.packagetablayout);
        createTabFragment();
        return view;
    }

}