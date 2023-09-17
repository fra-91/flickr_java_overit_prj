package com.outdoorreligion.flickr_java;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.outdoorreligion.flickr_java.model.search.Photo;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<Photo> dataList;
    private FragmentManager fm;
    private Context context;

    public SearchAdapter(List<Photo> dataList, FragmentManager fragmentManager, Context context) {
        this.dataList = dataList;
        this.fm = fragmentManager;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Photo item = dataList.get(position);
        if(item != null
                && item.getPhotoInfo() != null
                && item.getPhotoInfo().getSecret() != null
                && item.getPhotoInfo().getServer() != null
                && item.getPhotoInfo().getId() != null
        ) {

            String imageUrl = String.format("https://live.staticflickr.com/%s/%s_%s_w.jpg",
                    item.getPhotoInfo().getServer(),
                    item.getPhotoInfo().getId(),
                    item.getPhotoInfo().getSecret()
            );

            ImageView imageView = holder.imageView;
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.mipmap.ic_launcher_round);

            Glide.with(context)
                    .load(imageUrl)
                    .apply(options)
                    .into(imageView);

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InfoFragment newFragment = InfoFragment.newInstance(item);

                    fm.beginTransaction()
                            .add(R.id.main_container, newFragment)
                            .addToBackStack(null)
                            .commit();
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder){
        super.onViewAttachedToWindow(holder);

        if(holder.imageView.getVisibility() == View.INVISIBLE || holder.imageView.getVisibility() == View.GONE)
            holder.imageView.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
        }
    }
}