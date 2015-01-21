package com.bs23.codewarrior.codewarriorfirstproject.provider;

import com.bs23.codewarrior.codewarriorfirstproject.service.AuthService;
import com.google.inject.Inject;
import com.google.inject.Provider;

import retrofit.RestAdapter;

public class AuthServiceProvider implements Provider<AuthService> {
    @Inject
    RestAdapter restAdapter;

    @Override
    public AuthService get() {
        return restAdapter.create(AuthService.class);
    }
}
