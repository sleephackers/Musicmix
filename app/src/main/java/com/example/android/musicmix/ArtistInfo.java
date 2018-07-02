package com.example.android.musicmix;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.musicmix.ArtistInfoId.ArtistInfoResponse;
import com.example.android.musicmix.RetrofitCalls.ApiClient;
import com.example.android.musicmix.RetrofitCalls.ApiInterface;
import com.example.android.musicmix.TopArtists.Artist;
import com.example.android.musicmix.data.ArtistContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistInfo extends AppCompatActivity {
    private final static String API_KEY = "0ce449cc4ebd0c6c62b0cf1903fe0830";
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.addtofavourites:
                saveArtist();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }


    public void fetchArtistInfo() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ArtistInfoResponse> call = apiInterface.getArtistInfo(id, API_KEY);
        call.enqueue(new Callback<ArtistInfoResponse>() {
            @Override
            public void onResponse(Call<ArtistInfoResponse> call, Response<ArtistInfoResponse> response) {
                Artist artist = response.body().getMessage().getBody().getArtist();
                name.setText(artist.getArtistName());
                if (artist.getArtistCountry().isEmpty())
                    country.setText("Country Unknown");
                else
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

    private void saveArtist() {

        String names = name.getText().toString();
        String countrys = country.getText().toString();
        String genress = genres.getText().toString();
        String ratings = rating.getText().toString();

        ContentValues values = new ContentValues();
        values.put(ArtistContract.ArtistEntry.COLUMN_NAME, names);
        values.put(ArtistContract.ArtistEntry.COLUMN_COUNTRY, countrys);
        values.put(ArtistContract.ArtistEntry.COLUMN_GENRE, genress);
        values.put(ArtistContract.ArtistEntry.COLUMN_RATING, ratings);


        Uri newUri = getContentResolver().insert(ArtistContract.ArtistEntry.CONTENT_URI, values);

        if (newUri == null) {
            Toast.makeText(this, "INSERT FAILED",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "INSERT SUCCESSFUL",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
