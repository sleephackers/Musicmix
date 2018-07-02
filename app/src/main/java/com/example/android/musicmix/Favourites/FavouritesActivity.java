package com.example.android.musicmix.Favourites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.musicmix.R;

public class FavouritesActivity extends AppCompatActivity {
    TextView tracks;
    TextView artists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        tracks = (TextView) findViewById(R.id.favouritetracks);
        artists = (TextView) findViewById(R.id.favouriteartists);
        tracks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(FavouritesActivity.this, FavouriteTracks.class);
                startActivity(intent2);
            }
        });
        artists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(FavouritesActivity.this, FavouriteArtists.class);
                startActivity(intent2);
            }
        });
    }
}
