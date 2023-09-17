package com.outdoorreligion.flickr_java.presenter;

import com.outdoorreligion.flickr_java.model.search.Photo;

import java.util.List;

public class SearchPresenter implements SearchContract.Presenter, SearchContract.Model.OnFinishedListener {

    private SearchContract.View mView;
    private SearchContract.Model model;

    public SearchPresenter(SearchContract.View mView, SearchContract.Model model) {
        this.mView = mView;
        this.model = model;
    }

    @Override
    public void onSearchClicked(String text) {
        model.callSearchApi(text, this);
    }

    @Override
    public void onFinished(List<Photo> photos) {
        mView.showResults(photos);
    }
}
