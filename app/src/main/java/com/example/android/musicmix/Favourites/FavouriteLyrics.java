package com.example.android.musicmix.Favourites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.musicmix.R;

public class FavouriteLyrics extends AppCompatActivity {
    TextView lyrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);
        Bundle extras = getIntent().getExtras();
        lyrics = (TextView) findViewById(R.id.lyricsText);
        lyrics.setText(extras.getString("lyrics"));
    }
}
