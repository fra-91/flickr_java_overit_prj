package com.outdoorreligion.flickr_java.model.info;

import com.google.gson.annotations.SerializedName;

public class InfoResponse {
    @SerializedName("photo")
    private PhotoInfo photo;

    public PhotoInfo getPhotoInfo() {
        return photo;
    }
}