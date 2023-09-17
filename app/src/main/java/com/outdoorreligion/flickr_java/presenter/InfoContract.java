package com.outdoorreligion.flickr_java.presenter;

import com.outdoorreligion.flickr_java.model.info.PhotoInfo;

public interface InfoContract {

    public interface View {
        public void showResults(PhotoInfo info);
    }

    public interface Presenter {
        public void getInfoFromImageId(String id);
    }

    public interface Model {
        interface OnFinishedListener {
            void onFinished(PhotoInfo photo);
        }
        public void callInfoApi(String id, InfoContract.Model.OnFinishedListener onFinishedListener);
    }

}
