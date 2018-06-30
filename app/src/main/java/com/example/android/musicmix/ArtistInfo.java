package com.example.android.musicmix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.musicmix.ArtistInfoId.ArtistInfoResponse;
import com.example.android.musicmix.TopArtists.Artist;
import com.example.android.musicmix.TopTracks.Track;
import com.example.android.musicmix.TopTracks.TrackResponse;
import com.example.android.musicmix.TrackInfoId.TrackInfoResponse;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistInfo extends AppCompatActivity {
    private final static String API_KEY = "4628d54512e015d8ce17b12be8fa1e70";
    int id;
    TextView name;
    TextView country;
    TextView genres;
    TextView rating;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_info);
        name = (TextView) findViewById(R.id.name);
        country = (TextView) findViewById(R.id.country);
        genres = (TextView) findViewById(R.id.genre);
        rating = (TextView) findViewById(R.id.rating);

        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");
        fetchArtistInfo();
    }

    public void fetchArtistInfo() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ArtistInfoResponse> call = apiInterface.getArtistInfo(id, API_KEY);
        call.enqueue(new Callback<ArtistInfoResponse>() {
            @Override
            public void onResponse(Call<ArtistInfoResponse> call, Response<ArtistInfoResponse> response) {
                Artist artist = response.body().getMessage().getBody().getArtist();
                name.setText(artist.getArtistName());
                country.setText(artist.getArtistCountry());
                if (artist.getPrimaryGenres().getMusicGenreList().isEmpty())
                    genres.setText("Genre Unknown");
                else
                    genres.setText(artist.getPrimaryGenres().getMusicGenreList().get(0).getMusicGenre().getMusicGenreName());
                rating.setText(artist.getArtistRating().toString());

            }

            @Override
            public void onFailure(Call<ArtistInfoResponse> call, Throwable t) {
                Log.e(ArtistInfo.class.getSimpleName(), t.toString());
            }
        });
    }
}
