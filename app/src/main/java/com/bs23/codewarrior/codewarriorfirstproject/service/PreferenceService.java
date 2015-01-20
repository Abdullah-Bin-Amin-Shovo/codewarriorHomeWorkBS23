package com.bs23.codewarrior.codewarriorfirstproject.service;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class PreferenceService {
    public static final String SHARED_PREF_KEY = "com.bs23.codewarrior.codewarriorfirstproject.service" ;
    public static final String ACCESS_TOKEN = "access_token" ;
    @Inject
    private SharedPreferences preferences;

    public String GetPreferenceValue(String key)
    {
        return preferences.getString(key, "");
    }

    public void SetPreferenceValue(String key, String value)
    {
        preferences.edit().putString(key, value).apply();
    }
}
