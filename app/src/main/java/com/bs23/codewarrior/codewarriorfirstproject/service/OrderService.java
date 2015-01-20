package com.bs23.codewarrior.codewarriorfirstproject.service;

import java.util.List;

import retrofit.http.GET;

public interface OrderService {
     @GET("/api/orders")
     List<Object> groupList();
}
