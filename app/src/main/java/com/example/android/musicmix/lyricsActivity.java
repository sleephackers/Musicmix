package com.example.android.musicmix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.android.musicmix.Lyrics.LyricsResponse;
import com.example.android.musicmix.RetrofitCalls.ApiClient;
import com.example.android.musicmix.RetrofitCalls.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class lyricsActivity extends AppCompatActivity {
    private final static String API_KEY = "4628d54512e015d8ce17b12be8fa1e70";
    private static final String TAG = lyricsActivity.class.getSimpleName();
    TextView lyrics;
    ApiInterface apiInterface;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);
        lyrics = (TextView) findViewById(R.id.lyricsText);
        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");
        fetchLyrics();
    }

    public void fetchLyrics() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<LyricsResponse> call = apiInterface.getLyrics(id, API_KEY);
        call.enqueue(new Callback<LyricsResponse>() {
            @Override
            public void onResponse(Call<LyricsResponse> call, Response<LyricsResponse> response) {

                lyrics.setText(response.body().getMessage().getBody().getLyrics().getLyricsBody());

            }


            @Override
            public void onFailure(Call<LyricsResponse> call, Throwable t) {
                Log.e(lyricsActivity.class.getSimpleName(), t.toString());
            }
        });
    }
}
