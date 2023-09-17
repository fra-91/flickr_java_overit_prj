package com.outdoorreligion.flickr_java.model.info;

import com.google.gson.annotations.SerializedName;

public class Url {
    @SerializedName("type")
    private String type;

    @SerializedName("_content")
    private String _content;

    public String getType() {
        return type;
    }

    public String getContent() {
        return _content;
    }
}
