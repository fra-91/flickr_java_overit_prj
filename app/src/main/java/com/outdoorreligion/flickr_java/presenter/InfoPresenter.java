package com.outdoorreligion.flickr_java.presenter;

import com.outdoorreligion.flickr_java.model.info.PhotoInfo;

public class InfoPresenter implements InfoContract.Presenter, InfoContract.Model.OnFinishedListener {
    private InfoContract.View mView;
    private InfoContract.Model model;

    public InfoPresenter(InfoContract.View mView, InfoContract.Model model) {
        this.mView = mView;
        this.model = model;
    }

    @Override
    public void getInfoFromImageId(String id) {
        model.callInfoApi(id, this);
    }

    @Override
    public void onFinished(PhotoInfo info) {
        mView.showResults(info);
    }
}
