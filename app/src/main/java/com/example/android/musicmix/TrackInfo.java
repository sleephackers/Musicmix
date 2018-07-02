package com.example.android.musicmix;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.musicmix.Lyrics.LyricsResponse;
import com.example.android.musicmix.RetrofitCalls.ApiClient;
import com.example.android.musicmix.RetrofitCalls.ApiInterface;
import com.example.android.musicmix.TopTracks.Track;
import com.example.android.musicmix.TrackInfoId.TrackInfoResponse;
import com.example.android.musicmix.data.TrackContract;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackInfo extends AppCompatActivity {
    private final static String API_KEY = "0ce449cc4ebd0c6c62b0cf1903fe0830";
    Integer id;
    TextView title;
    TextView artist;
    TextView album;
    TextView genres;
    Integer hasLyrics;
    TextView yearofrelease;
    Button lyrics;
    String lyricsText;
    ImageView poster;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_info);
        title = (TextView) findViewById(R.id.title);
        artist = (TextView) findViewById(R.id.artist);
        album = (TextView) findViewById(R.id.album);
        genres = (TextView) findViewById(R.id.genres);
        yearofrelease = (TextView) findViewById(R.id.yearofrelease);
        lyrics = (Button) findViewById(R.id.lyrics);
        poster = (ImageView) findViewById(R.id.poster);

        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");
        fetchTrackInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.addtofavourites:
                if (hasLyrics == 0) {
                    lyricsText = "NO LYRICS AVAILABLE";
                    saveTrack();
                } else
                    lyricsLoad();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }


    public void fetchTrackInfo() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<TrackInfoResponse> call = apiInterface.getTrackInfo(id, API_KEY);
        call.enqueue(new Callback<TrackInfoResponse>() {
            @Override
            public void onResponse(Call<TrackInfoResponse> call, Response<TrackInfoResponse> response) {
                final Track track = response.body().getMessage().getBody().getTrack();
                // Log.e(TrackInfo.class.getSimpleName(),"heloooo"+track.getPrimaryGenres().getMusicGenreList().get(0).getMusicGenre().getMusicGenreName());
                title.setText(track.getTrackName());
                artist.setText(track.getArtistName());
                album.setText(track.getAlbumName());
                if (track.getPrimaryGenres().getMusicGenreList().isEmpty())
                    genres.setText("Genre Unknown");
                else {
                    genres.setText(track.getPrimaryGenres().getMusicGenreList().get(0).getMusicGenre().getMusicGenreName());
                }
                hasLyrics = track.getHasLyrics();
                StringBuilder stringBuilder = new StringBuilder(track.getFirstReleaseDate());
                yearofrelease.setText(stringBuilder.substring(0, 10));

                Picasso.get().load(track.getAlbumCoverart100x100()).resize(600, 600).into(poster);
                lyrics.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(TrackInfo.this, lyricsActivity.class);
                        Bundle extras = new Bundle();
                        extras.putInt("id", id);
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                });
            }


            @Override
            public void onFailure(Call<TrackInfoResponse> call, Throwable t) {
                Log.e(TrackInfo.class.getSimpleName(), t.toString());
            }
        });
    }

    public void lyricsLoad() {
        ApiInterface lyricapiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<LyricsResponse> callme = lyricapiInterface.getLyrics(id, API_KEY);

        callme.enqueue(new Callback<LyricsResponse>() {
            @Override
            public void onResponse(Call<LyricsResponse> call, Response<LyricsResponse> response) {

                lyricsText = response.body().getMessage().getBody().getLyrics().getLyricsBody();
                saveTrack();


            }


            @Override
            public void onFailure(Call<LyricsResponse> call, Throwable t) {
                Log.e(TrackInfo.class.getSimpleName(), t.toString());
            }
        });
    }

    private void saveTrack() {

        String titles = title.getText().toString();
        String artists = artist.getText().toString();
        String albums = album.getText().toString();
        String genress = genres.getText().toString();
        String yearofreleases = yearofrelease.getText().toString();
        String lyricss = lyricsText;

        ContentValues values = new ContentValues();
        values.put(TrackContract.TrackEntry.COLUMN_TITLE, titles);
        values.put(TrackContract.TrackEntry.COLUMN_ARTIST, artists);
        values.put(TrackContract.TrackEntry.COLUMN_ALBUM, albums);
        values.put(TrackContract.TrackEntry.COLUMN_GENRE, genress);
        values.put(TrackContract.TrackEntry.COLUMN_YEAROFRELEASE, yearofreleases);
        values.put(TrackContract.TrackEntry.COLUMN_LYRICS, lyricss);


        Uri newUri = getContentResolver().insert(TrackContract.TrackEntry.CONTENT_URI, values);

        if (newUri == null) {
            Toast.makeText(this, "INSERT FAILED",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "INSERT SUCCESSFUL",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
