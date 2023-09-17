package com.outdoorreligion.flickr_java.model.info;

import com.google.gson.annotations.SerializedName;

public class Owner {
    @SerializedName("user")
    private String user;
    @SerializedName("realname")
    private String realname;
    @SerializedName("location")
    private String location;

    public String getUser() {
        return user;
    }

    public String getRealname() {
        return realname;
    }

    public String getLocation() {
        return location;
    }
}
