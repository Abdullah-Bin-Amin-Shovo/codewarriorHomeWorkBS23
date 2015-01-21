package com.bs23.codewarrior.codewarriorfirstproject.provider;

import com.google.gson.Gson;
import com.google.inject.Inject;

import javax.inject.Provider;

import retrofit.Endpoint;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.converter.GsonConverter;


/**
 * Created by Lenovo ThinkPad X230 on 1/19/2015.
 */
public class RestAdapterProvider implements Provider<RestAdapter> {
    @Inject Client client;
    @Inject Endpoint endpoint;
    @Inject
    RequestInterceptor requestInterceptor;

    @Override
    public RestAdapter get() {
        return new RestAdapter.Builder() //
        .setClient(client) //
        .setRequestInterceptor(requestInterceptor)
        .setEndpoint(endpoint) //
        .setConverter(new GsonConverter(new Gson()))
        .build();
    }
}
