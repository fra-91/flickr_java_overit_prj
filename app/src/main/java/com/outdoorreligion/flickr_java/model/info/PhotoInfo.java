package com.outdoorreligion.flickr_java.model.info;

import com.google.gson.annotations.SerializedName;

public class PhotoInfo {
    @SerializedName("id")
    private String id;
    @SerializedName("views")
    private String views;
    @SerializedName("secret")
    private String secret;
    @SerializedName("server")
    private String server;
    @SerializedName("farm")
    private String farm;
    @SerializedName("originalsecret")
    private String originalsecret;
    @SerializedName("originalformat")
    private String originalformat;
    @SerializedName("title")
    private Title title;
    @SerializedName("description")
    private Description description;
    @SerializedName("dates")
    private Dates dates;
    @SerializedName("location")
    private Location location;
    @SerializedName("urls")
    private Urls urls;
    @SerializedName("dateuploaded")
    private String dateuploaded;
    @SerializedName("owner")
    private Owner owner;

    public String getServer() {
        return server;
    }

    public String getId() {
        return id;
    }

    public String getViews() {
        return views;
    }

    public String getSecret() {
        return secret;
    }

    public String getFarm() {
        return farm;
    }

    public String getOriginalsecret() {
        return originalsecret;
    }

    public String getOriginalformat() {
        return originalformat;
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public Dates getDates() {
        return dates;
    }

    public Location getLocation() {
        return location;
    }

    public Urls getUrls() {
        return urls;
    }

    public String getDateuploaded() {
        return dateuploaded;
    }

    public Owner getOwner() {
        return owner;
    }
}
