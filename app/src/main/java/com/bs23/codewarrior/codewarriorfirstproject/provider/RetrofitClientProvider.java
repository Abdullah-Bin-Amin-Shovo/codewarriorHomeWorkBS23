package com.bs23.codewarrior.codewarriorfirstproject.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.squareup.okhttp.OkHttpClient;

import retrofit.client.Client;
import retrofit.client.OkClient;

public class RetrofitClientProvider implements Provider<Client> {
    @Inject
    OkHttpClient okHttpClient;

    @Override
    public Client get() {
        return  new OkClient(okHttpClient);
    }
}
