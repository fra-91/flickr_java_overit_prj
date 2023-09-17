package com.outdoorreligion.flickr_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //apre SearchFragment

        getSupportFragmentManager().
                beginTransaction().
                add(R.id.main_container,
                        SearchFragment.newInstance()).
                addToBackStack(SearchFragment.FRAGMENT_TAG).
                commit();
    }

    public void openDetail() {
        getSupportFragmentManager().
                beginTransaction().
                add(R.id.main_container,
                        SearchFragment.newInstance()).
                addToBackStack(InfoFragment.FRAGMENT_TAG).
                commit();
    }
}