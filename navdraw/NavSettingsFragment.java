package com.example.edithapp.navdraw;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.edithapp.R;

public class NavSettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}