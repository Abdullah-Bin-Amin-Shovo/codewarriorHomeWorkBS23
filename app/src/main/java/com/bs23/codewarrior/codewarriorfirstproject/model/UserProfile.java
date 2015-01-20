package com.bs23.codewarrior.codewarriorfirstproject.model;

import com.google.gson.annotations.SerializedName;

public class UserProfile {
    @SerializedName("Id")
    public int id;

    @SerializedName("FirstName")
    public String firstName;

    @SerializedName("LastName")
    public String lastName;

    @SerializedName("ProfilePic")
    public  String profilePic;

    @SerializedName("Address")
    public String address;

}
