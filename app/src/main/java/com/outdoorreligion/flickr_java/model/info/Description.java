package com.outdoorreligion.flickr_java.model.info;

import com.google.gson.annotations.SerializedName;

public class Description {
    @SerializedName("_content")
    private String _content;

    public String getContent() {
        return _content;
    }
}
