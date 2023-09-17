package com.outdoorreligion.flickr_java.api;

import com.outdoorreligion.flickr_java.model.info.InfoResponse;
import com.outdoorreligion.flickr_java.model.search.SearchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrApi {

    String API_KEY = "896288e29e81860115e084b9797cc6b2";
    String SEARCH_METHOD = "flickr.photos.search";
    String GETINFO_METHOD = "flickr.photos.getInfo";
    String JSON_FORMAT = "json";

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&text=dogs&api_key=896288e29e81860115e084b9797cc6b2")
    Call<SearchResponse> searchEasy();


    @GET("/rest/")
    Call getPhotos(@Query("method") String method, @Query("api_key") String apiKey, @Query("text") String text, Callback<SearchResponse> data);

    @GET("services/rest/")
    Call<InfoResponse> getInfo(
            @Query("method") String method,
            @Query("api_key") String apiKey,
            @Query("format") String format,
            @Query("nojsoncallback") int noJsonCallback,
            @Query("photo_id") String parameter
    );


    @GET("services/rest/")
    Call<SearchResponse> searchPhotos(
            @Query("method") String method,
            @Query("api_key") String apiKey,
            @Query("format") String format,
            @Query("nojsoncallback") int noJsonCallback,
            @Query("text") String text
    );
}
