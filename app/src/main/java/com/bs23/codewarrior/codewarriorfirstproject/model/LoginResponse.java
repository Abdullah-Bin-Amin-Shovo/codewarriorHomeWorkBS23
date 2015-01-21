package com.bs23.codewarrior.codewarriorfirstproject.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("access_token")
    public String accessToken;
    @SerializedName("expires_in")
    public Long expiresIn;
    @SerializedName("token_type")
    public String tokenType;
    
    
}
