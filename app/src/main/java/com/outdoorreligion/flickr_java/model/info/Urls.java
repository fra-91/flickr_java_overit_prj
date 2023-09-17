package com.outdoorreligion.flickr_java.model.info;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Urls {
    @SerializedName("url")
    private ArrayList<Url> url;

    public ArrayList<Url> getUrl() {
        return url;
    }
}
