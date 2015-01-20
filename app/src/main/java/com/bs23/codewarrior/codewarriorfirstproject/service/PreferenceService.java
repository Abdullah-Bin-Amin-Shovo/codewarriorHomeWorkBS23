package com.bs23.codewarrior.codewarriorfirstproject.service;

import android.content.SharedPreferences;

import com.bs23.codewarrior.codewarriorfirstproject.model.LoginResponse;

import javax.inject.Inject;

public class PreferenceService {
    public static final String SHARED_PREF_KEY = "com.bs23.codewarrior.codewarriorfirstproject.service";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String TOKEN_TYPE = "token_type";
    public static final String USER_EMAIL = "user_email";

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

    public void SaveAuthPreferences(LoginResponse response)
    {
        preferences.edit().putString(ACCESS_TOKEN, response.access_token);
        preferences.edit().putString(TOKEN_TYPE, response.token_type);
    }
}
