package com.outdoorreligion.flickr_java.model.info;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("_content")
    private String _content;

    public String getContent() {
        return _content;
    }
}
