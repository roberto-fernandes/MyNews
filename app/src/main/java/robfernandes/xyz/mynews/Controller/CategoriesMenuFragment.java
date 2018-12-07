package robfernandes.xyz.mynews.Controller;


import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import robfernandes.xyz.mynews.R;

/**
 * Created by Roberto Fernandes on 07/12/2018.
 */
public class CategoriesMenuFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        setPreferencesFromResource(R.xml.preferences, s);
    }
}
