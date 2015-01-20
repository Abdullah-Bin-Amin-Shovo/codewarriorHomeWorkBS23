package com.bs23.codewarrior.codewarriorfirstproject.util;

import com.bs23.codewarrior.codewarriorfirstproject.service.PreferenceService;

import javax.inject.Inject;

import retrofit.RequestInterceptor;

public class RetrofitRequestInterceptor implements RequestInterceptor {
    @Inject
    private PreferenceService preferenceService;

    @Override
    public void intercept(RequestFacade request) {
        String accessToken = preferenceService.GetPreferenceValue(PreferenceService.ACCESS_TOKEN);
        request.addHeader("Authorization", "Bearer " + accessToken);
    }
}
