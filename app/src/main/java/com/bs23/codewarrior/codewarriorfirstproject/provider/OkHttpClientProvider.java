package com.bs23.codewarrior.codewarriorfirstproject.provider;

import android.app.Application;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.io.IOException;


/**
 * Created by Lenovo ThinkPad X230 on 1/19/2015.
 */
public class OkHttpClientProvider implements Provider<OkHttpClient> {
    static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB
    @Inject
    Application app;

    @Override
    public OkHttpClient get() {
        OkHttpClient client = new OkHttpClient();
        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(app.getCacheDir(), "http");
        Cache cache = null;
        try {
            cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.setCache(cache);
        return client;
    }
}
