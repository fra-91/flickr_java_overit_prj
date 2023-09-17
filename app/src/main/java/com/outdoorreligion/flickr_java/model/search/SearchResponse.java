package com.outdoorreligion.flickr_java.model.search;

import com.google.gson.annotations.SerializedName;

public class SearchResponse {
    @SerializedName("photos")
    private PhotoResponse photos;

    public PhotoResponse getPhotos() {
        return photos;
    }
}


