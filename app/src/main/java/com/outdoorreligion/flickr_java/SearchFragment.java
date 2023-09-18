package com.outdoorreligion.flickr_java;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.outdoorreligion.flickr_java.model.search.Photo;
import com.outdoorreligion.flickr_java.model.SearchModel;
import com.outdoorreligion.flickr_java.presenter.SearchContract;
import com.outdoorreligion.flickr_java.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchContract.View {

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }
    public static String FRAGMENT_TAG = "SearchFragment";
    private static SearchContract.Presenter mSearchPresenter;

    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    private List<String> dataList;
    private ProgressDialog progressDialog;
    private String textToSearch = "";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSearchPresenter = new SearchPresenter(this, new SearchModel());

        TextInputLayout searchInputLayout = view.findViewById(R.id.search_input_layout);
        TextInputEditText editText = view.findViewById(R.id.search_input_text);
        searchInputLayout.setEndIconOnClickListener(v -> {
            editText.setText("");
            textToSearch = "";
        });
        searchInputLayout.setStartIconOnClickListener(v -> {
            if(editText.getText() != null) {
                textToSearch = editText.getText().toString();
                if(!textToSearch.isEmpty() && !textToSearch.isEmpty())
                    loadData(textToSearch);
            }
            else
                Log.d("SearchFragment", "text is null");
        });

        recyclerView = view.findViewById(R.id.search_rv);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void loadData(String text) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Caricamento in corso...");
        progressDialog.setCancelable(false);

        progressDialog.show();

        mSearchPresenter.onSearchClicked(text);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void showResults(List<Photo> photos, boolean lastItem) {
        Log.d("SearchFragment", "RESPONSE AVAILABLE");
        if(getActivity() != null) {
            if(lastItem)
                progressDialog.dismiss();
            adapter = new SearchAdapter(photos, getActivity().getSupportFragmentManager(), getContext());
            recyclerView.setAdapter(adapter);
        }
    }
}