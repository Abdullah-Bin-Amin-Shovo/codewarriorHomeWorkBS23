package com.bs23.codewarrior.codewarriorfirstproject.provider;

import com.bs23.codewarrior.codewarriorfirstproject.BuildConfig;
import com.google.inject.Provider;

import retrofit.Endpoint;
import retrofit.Endpoints;

/**
 * Created by Lenovo ThinkPad X230 on 1/19/2015.
 */
public class RestEndpointProvider implements Provider<Endpoint> {
    @Override
    public Endpoint get() {
        return Endpoints.newFixedEndpoint(BuildConfig.API_ENDPOINT);
    }
}
