package edu.drake.pocketbotanist;

import android.os.Bundle;
import android.preference.PreferenceFragment;

<<<<<<< HEAD
=======

>>>>>>> origin/Database-Branch
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences); 
    }
}
