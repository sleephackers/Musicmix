package com.example.android.musicmix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.musicmix.TopTracks.Track;
import com.example.android.musicmix.TopTracks.TrackResponse;
import com.example.android.musicmix.TrackInfoId.TrackInfoResponse;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackInfo extends AppCompatActivity {
    private final static String API_KEY = "4628d54512e015d8ce17b12be8fa1e70";
    Integer id;
    TextView title;
    TextView artist;
    TextView album;
    TextView genres;
    TextView yearofrelease;
    TextView haslyrics;
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
        haslyrics = (TextView) findViewById(R.id.haslyrics);
        poster = (ImageView) findViewById(R.id.poster);

        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");
        fetchTrackInfo();
    }

    public void fetchTrackInfo() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<TrackInfoResponse> call = apiInterface.getTrackInfo(id, API_KEY);
        call.enqueue(new Callback<TrackInfoResponse>() {
            @Override
            public void onResponse(Call<TrackInfoResponse> call, Response<TrackInfoResponse> response) {
                Track track = response.body().getMessage().getBody().getTrack();
                // Log.e(TrackInfo.class.getSimpleName(),"heloooo"+track.getPrimaryGenres().getMusicGenreList().get(0).getMusicGenre().getMusicGenreName());
                title.setText(track.getTrackName());
                artist.setText(track.getArtistName());
                album.setText(track.getAlbumName());
                if (track.getPrimaryGenres().getMusicGenreList().isEmpty())
                    genres.setText("Genre Unknown");
                else {
                    genres.setText(track.getPrimaryGenres().getMusicGenreList().get(0).getMusicGenre().getMusicGenreName());
                }
                yearofrelease.setText(track.getFirstReleaseDate());
                haslyrics.setText(track.getHasLyrics().toString());
                Picasso.get().load(track.getAlbumCoverart100x100()).resize(600, 600).into(poster);
            }

            @Override
            public void onFailure(Call<TrackInfoResponse> call, Throwable t) {
                Log.e(TrackInfo.class.getSimpleName(), t.toString());
            }
        });
    }
}
