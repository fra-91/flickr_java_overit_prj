package com.outdoorreligion.flickr_java.model;

import android.util.Log;

import com.outdoorreligion.flickr_java.api.RetrofitClient;
import com.outdoorreligion.flickr_java.api.FlickrApi;
import com.outdoorreligion.flickr_java.model.info.InfoResponse;
import com.outdoorreligion.flickr_java.model.info.PhotoInfo;
import com.outdoorreligion.flickr_java.model.search.Photo;
import com.outdoorreligion.flickr_java.model.search.SearchResponse;
import com.outdoorreligion.flickr_java.presenter.SearchContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchModel implements SearchContract.Model {
    private FlickrApi flickrApi;
    private List<Photo> photos = new ArrayList<>();

    @Override
    public void callSearchApi(String text, OnFinishedListener onFinishedListener) {
       Log.d("SearchModel", "Calling searchApi");
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        flickrApi = retrofit.create(FlickrApi.class);

        Call<SearchResponse> call = flickrApi.searchPhotos(
                FlickrApi.SEARCH_METHOD,
                FlickrApi.API_KEY,
                FlickrApi.JSON_FORMAT,
                1,
                text
        );

        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                Log.d("SearchModel", "searchApi responded");
                if (response.isSuccessful()) {
                    SearchResponse apiResponse = response.body();
                    if(apiResponse != null) {
                        photos = apiResponse.getPhotos().getPhotos();
                        Log.d("SearchModel", "searchApi succesful");
                        //onFinishedListener.onFinished(photos);
                        callInfoApi(onFinishedListener);
                    }
                    // Ora puoi gestire la lista di foto ottenuta dalla risposta
                } else {
                    // Gestire errori qui
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.d("SearchModel", "fail "+t.getMessage());
                // Gestire errori di rete qui
            }
        });
    }

    private void callInfoApi(OnFinishedListener onFinishedListener) {
        for (Photo p: photos) {
            Call<InfoResponse> infoCall = flickrApi.getInfo(
                    FlickrApi.GETINFO_METHOD,
                    FlickrApi.API_KEY,
                    FlickrApi.JSON_FORMAT,
                    1,
                    p.getId()
            );

            infoCall.enqueue(new Callback<InfoResponse>() {
                @Override
                public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {
                    Log.d("SearchModel", "searchApi responded");
                    if (response.isSuccessful()) {
                        InfoResponse apiResponse = response.body();
                        if(apiResponse != null) {
                            PhotoInfo photoInfo = apiResponse.getPhotoInfo();
                            p.setPhotoInfo(photoInfo);
                            onFinishedListener.onFinished(photos);
                            Log.d("SearchModel", "searchApi succesful");
                        }
                        // Ora puoi gestire la lista di foto ottenuta dalla risposta
                    } else {
                        // Gestire errori qui
                    }
                }

                @Override
                public void onFailure(Call<InfoResponse> call, Throwable t) {
                    Log.d("SearchModel", "fail "+t.getMessage());
                    // Gestire errori di rete qui
                }
            });
        }
    }
}
