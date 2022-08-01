package com.example.edithapp.tabbar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edithapp.R;

public class TabAccountsFragment extends Fragment {

    public TabAccountsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view   =inflater.inflate(R.layout.tabbar_fragment_tab_accounts, container, false);

        return view;
    }
}