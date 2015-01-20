package com.bs23.codewarrior.codewarriorfirstproject.module;

import com.bs23.codewarrior.codewarriorfirstproject.provider.AuthServiceProvider;
import com.bs23.codewarrior.codewarriorfirstproject.provider.OkHttpClientProvider;
import com.bs23.codewarrior.codewarriorfirstproject.provider.RestAdapterProvider;
import com.bs23.codewarrior.codewarriorfirstproject.provider.RestEndpointProvider;
import com.bs23.codewarrior.codewarriorfirstproject.provider.RetrofitClientProvider;
import com.bs23.codewarrior.codewarriorfirstproject.service.AuthService;
import com.bs23.codewarrior.codewarriorfirstproject.service.PreferenceService;
import com.bs23.codewarrior.codewarriorfirstproject.util.RetrofitRequestInterceptor;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Endpoint;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Client;
import roboguice.inject.SharedPreferencesName;

public class ApiClientModule implements Module {
    @Override
    public void configure(Binder binder) {
        binder.bind(Endpoint.class).toProvider(RestEndpointProvider.class).in(Singleton.class);
        binder.bind(OkHttpClient.class).toProvider(OkHttpClientProvider.class).in(Singleton.class);
        binder.bind(Client.class).toProvider(RetrofitClientProvider.class).in(Singleton.class);
        binder.bind(RequestInterceptor.class).to(RetrofitRequestInterceptor.class);
        binder.bind(RestAdapter.class).toProvider(RestAdapterProvider.class).in(Singleton.class);
        binder.bindConstant().annotatedWith(SharedPreferencesName.class).to(PreferenceService.SHARED_PREF_KEY);

        //services
        binder.bind(AuthService.class).toProvider(AuthServiceProvider.class).in(Singleton.class);
    }
}
