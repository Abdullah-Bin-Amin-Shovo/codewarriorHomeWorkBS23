package com.bs23.codewarrior.codewarriorfirstproject.service;

import android.content.SharedPreferences;

import com.bs23.codewarrior.codewarriorfirstproject.model.LoginResponse;

import javax.inject.Inject;

public class PreferenceService {
    public static final String SHARED_PREF_KEY = "com.bs23.codewarrior.codewarriorfirstproject.service";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String TOKEN_TYPE = "tokenType";
    public static final String IS_SIGNIN = "isSignIn";
    public static final String EMAIL = "email";
    public static final String ID = "user_id";
    public static final String USER_EMAIL = "user_email";

    @Inject
    private SharedPreferences preferences;

    public String GetPreferenceValue(String key)
    {
        return preferences.getString(key, null);
    }
    public Boolean isSignedIn(String key)
    {
        return preferences.getBoolean(key, false);
    }

    public void SetPreferenceValue(String key, String value)
    {
        preferences.edit().putString(key, value).commit();
    }
    
    public void SetPreferenceValue(String key, Boolean value)
    {
        preferences.edit().putBoolean(key, value).commit();
    }


    public void SaveAuthPreferences(LoginResponse response)
    {
        preferences.edit().putString(ACCESS_TOKEN, response.accessToken).apply();
        preferences.edit().putString(TOKEN_TYPE, response.tokenType).apply();
        preferences.edit().putBoolean(IS_SIGNIN,true).apply();
        
    }

    public void ResetAuthPreferences()
    {
        preferences.edit().putString(ACCESS_TOKEN, "").apply();
        preferences.edit().putString(TOKEN_TYPE, "").apply();
        preferences.edit().putBoolean(IS_SIGNIN,false).apply();
    }
    
    
}
