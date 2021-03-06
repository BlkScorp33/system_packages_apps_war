/*
 * Copyright (C) 2018 Invictrix ROM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.armory.settings.ui;

import android.content.ContentResolver;
import android.os.Bundle;
import android.os.UserHandle;
import android.provider.Settings;

import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;

import com.android.settings.R;

public class MiscSettings extends InvictrixSettingsFragment implements OnPreferenceChangeListener {

    public static String HEADSET_KEY = "headset_connect_player";
    private ListPreference headsetPreference;
    private ContentResolver resolver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getResources().getString(R.string.misc_settings_title);
        addPreferencesFromResource(R.xml.settings_misc);

        resolver = getActivity().getContentResolver();

        headsetPreference = (ListPreference) findPreference(HEADSET_KEY);
        int headsetValue = Settings.System.getInt(resolver, HEADSET_KEY, 0);
        headsetPreference.setValue(Integer.toString(headsetValue));
        headsetPreference.setSummary(headsetPreference.getEntry());
        headsetPreference.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        int value = Integer.parseInt((String) newValue);
	headsetPreference.setSummary(headsetPreference.getEntries()[value]);
	Settings.System.putInt(resolver, HEADSET_KEY, value);
        return true;
    }

}
