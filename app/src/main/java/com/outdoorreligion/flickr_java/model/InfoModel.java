package com.outdoorreligion.flickr_java.model;

import android.util.Log;

import com.outdoorreligion.flickr_java.api.FlickrApi;
import com.outdoorreligion.flickr_java.api.RetrofitClient;
import com.outdoorreligion.flickr_java.model.info.InfoResponse;
import com.outdoorreligion.flickr_java.presenter.InfoContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InfoModel implements InfoContract.Model  {

    private FlickrApi infoApi;

    public void callInfoApi(String id, InfoContract.Model.OnFinishedListener onFinishedListener) {
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        infoApi = retrofit.create(FlickrApi.class);

        Call<InfoResponse> call = infoApi.getInfo(
                FlickrApi.GETINFO_METHOD,
                FlickrApi.API_KEY,
                FlickrApi.JSON_FORMAT,
                1,
                id
        );

        call.enqueue(new Callback<InfoResponse>() {

            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {
                Log.d("InfoModel", "info responded");
                if (response.isSuccessful()) {
                    Log.d("InfoModel", "info successful");
                    InfoResponse infoResponse = response.body();
                    if(infoResponse != null)
                        onFinishedListener.onFinished(infoResponse.getPhotoInfo());
                } else {
                }
            }

            @Override
            public void onFailure(Call<InfoResponse> call, Throwable t) {
                Log.d("InfoRepository", "Error: "+t.getMessage());
            }
        });
    }
}
