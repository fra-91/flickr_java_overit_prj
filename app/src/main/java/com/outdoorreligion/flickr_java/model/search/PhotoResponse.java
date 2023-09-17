package com.outdoorreligion.flickr_java.model.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotoResponse {
    @SerializedName("photo")
    private List<Photo> photos;

    public List<Photo> getPhotos() {
        return photos;
    }
}
