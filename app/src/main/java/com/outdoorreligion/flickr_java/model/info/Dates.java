package com.outdoorreligion.flickr_java.model.info;

import com.google.gson.annotations.SerializedName;

public class Dates {
    @SerializedName("posted")
    private String posted;
    @SerializedName("taken")
    private String taken;
    @SerializedName("lastupdate")
    private String lastupdate;

    public String getPosted() {
        return posted;
    }

    public String getTaken() {
        return taken;
    }

    public String getLastupdate() {
        return lastupdate;
    }
}
