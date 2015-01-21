package com.bs23.codewarrior.codewarriorfirstproject.service;

import com.bs23.codewarrior.codewarriorfirstproject.test.BuildConfig;

import junit.framework.TestCase;

import retrofit.MockRestAdapter;
import retrofit.RestAdapter;

public class AuthServiceTest extends TestCase {

    RestAdapter restAdapter;
    MockRestAdapter mockRestAdapter;

    public void setUp() throws Exception {
        super.setUp();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BuildConfig.API_ENDPOINT)
                .build();
        mockRestAdapter = MockRestAdapter.from(restAdapter);
    }

    public void tearDown() throws Exception {

    }

    public void testRegisterUser() throws Exception {

    }

    public void testGetAuthToken() throws Exception {

    }

    public void testGetProfile() throws Exception {

    }

    public void testUpdateProfile() throws Exception {

    }
}