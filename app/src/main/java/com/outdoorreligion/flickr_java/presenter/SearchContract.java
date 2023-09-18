package com.outdoorreligion.flickr_java.presenter;

import com.outdoorreligion.flickr_java.model.search.Photo;

import java.util.List;

public interface SearchContract {

    public interface View {
        public void showResults(List<Photo> photos, boolean lastItem);
    }

    public interface Presenter {
        public void onSearchClicked(String text);
    }

    public interface Model {
        interface OnFinishedListener {
            void onFinished(List<Photo> photos, boolean lastItem);
        }
        public void callSearchApi(String text, SearchContract.Model.OnFinishedListener onFinishedListener);
    }
}
