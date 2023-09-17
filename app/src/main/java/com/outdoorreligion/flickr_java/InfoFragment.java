package com.outdoorreligion.flickr_java;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;
import com.outdoorreligion.flickr_java.model.InfoModel;
import com.outdoorreligion.flickr_java.model.info.PhotoInfo;
import com.outdoorreligion.flickr_java.model.search.Photo;
import com.outdoorreligion.flickr_java.presenter.InfoContract;
import com.outdoorreligion.flickr_java.presenter.InfoPresenter;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InfoFragment extends Fragment implements InfoContract.View {
    public static final String FRAGMENT_TAG = "InfoFragment";
    private static final String IMAGE_ID = "IMAGE_ID";
    private static final String IMAGE_SERVER = "IMAGE_SERVER";
    private static final String IMAGE_SECRET = "IMAGE_SECRET";
    private static final String IMAGE_LOCATION = "IMAGE_LOCATION";
    private static final String IMAGE_DATE = "IMAGE_DATE";
    private static final String IMAGE_TITLE = "IMAGE_TITLE";
    private static final String IMAGE_OWNER = "IMAGE_OWNER";

    private String mImageId = "";
    private String mImageServer = "";
    private String mImageSecret = "";
    private String mImageLocation = "";
    private String mImageDate = "";
    private String mImageTitle = "";
    private String mImageRealName = "";

    private static InfoContract.Presenter mInfoPresenter;

    public static InfoFragment newInstance(Photo p) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        if(p != null) {
            if(p.getId() != null)
                args.putString(IMAGE_ID, p.getId());
            if(p.getPhotoInfo() != null) {
                if (p.getPhotoInfo().getServer() != null)
                    args.putString(IMAGE_SERVER, p.getPhotoInfo().getServer());
                if (p.getPhotoInfo().getSecret() != null)
                    args.putString(IMAGE_SECRET, p.getPhotoInfo().getSecret());
                if (p.getPhotoInfo().getDates() != null && p.getPhotoInfo().getDates().getTaken() != null)
                    args.putString(IMAGE_DATE, p.getPhotoInfo().getDates().getTaken());
                if (p.getPhotoInfo().getTitle().getContent() != null)
                    args.putString(IMAGE_TITLE, p.getPhotoInfo().getTitle().getContent());
                if (p.getPhotoInfo().getOwner().getRealname() != null)
                    args.putString(IMAGE_OWNER, p.getPhotoInfo().getOwner().getRealname());
                if (p.getPhotoInfo().getLocation() != null
                        && p.getPhotoInfo().getLocation().getCountry() != null
                        && p.getPhotoInfo().getLocation().getCountry().getContent() != null)
                    args.putString(IMAGE_LOCATION, p.getPhotoInfo().getLocation().getCountry().getContent());
            }
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();
        if(args != null) {
            if(args.containsKey(IMAGE_ID))
                mImageId = args.getString(IMAGE_ID);
            if(args.containsKey(IMAGE_SERVER))
                mImageServer = args.getString(IMAGE_SERVER);
            if(args.containsKey(IMAGE_SECRET))
                mImageSecret = args.getString(IMAGE_SECRET);
            if(args.containsKey(IMAGE_LOCATION))
                mImageLocation = args.getString(IMAGE_LOCATION);
            if(args.containsKey(IMAGE_DATE))
                mImageDate = args.getString(IMAGE_DATE);
            if(args.containsKey(IMAGE_TITLE))
                mImageTitle = args.getString(IMAGE_TITLE);
            if(args.containsKey(IMAGE_OWNER))
                mImageRealName = args.getString(IMAGE_OWNER);
        }

        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //mInfoPresenter = new InfoPresenter(this, new InfoModel());
        //mInfoPresenter.getInfoFromImageId(mImageId);

        FrameLayout fl = view.findViewById(R.id.detail_fl);
        fl.setBackgroundColor(getResources().getColor(R.color.white));

        String imageUrl = String.format("https://live.staticflickr.com/%s/%s_%s_w.jpg",
                mImageServer,
                mImageId,
                mImageSecret
        );

        ImageView imageView = view.findViewById(R.id.detailImageView);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(this)
                .load(imageUrl)
                .apply(options)
                .into(imageView);

        TextView nameView = view.findViewById(R.id.owner);
        if(mImageRealName.isBlank() || mImageRealName.isEmpty())
            nameView.setVisibility(View.GONE);
        else
            nameView.setText("Autore: "+mImageRealName);

        TextView dateView = view.findViewById(R.id.date);
        if(mImageDate.isBlank() || mImageDate.isEmpty())
            dateView.setVisibility(View.GONE);
        else
            dateView.setText("Data caricamento: "+mImageDate);

        TextView locationView = view.findViewById(R.id.location);
        if(mImageLocation.isBlank() || mImageLocation.isEmpty())
            locationView.setVisibility(View.GONE);
        else
            locationView.setText("Posizione: "+mImageLocation);

        TextView titleView = view.findViewById(R.id.title);
        if(mImageTitle.isBlank() || mImageTitle.isEmpty())
            titleView.setVisibility(View.GONE);
        else
            titleView.setText("Titolo: "+mImageTitle);

        MaterialButton back = view.findViewById(R.id.backButton);
        back.setOnClickListener(v -> {
            if(getActivity() != null)
                getActivity().getSupportFragmentManager().popBackStackImmediate();
        });
    }

    @Override
    public void showResults(PhotoInfo info) {
        Log.d("InfoFragment", "RESPONSE AVAILABLE");
    }
}
