package com.example.android.musicmix.Search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.android.musicmix.RetrofitCalls.ApiClient;
import com.example.android.musicmix.RetrofitCalls.ApiInterface;
import com.example.android.musicmix.R;
import com.example.android.musicmix.RecyclerAdapter;
import com.example.android.musicmix.RecyclerTouchListener;
import com.example.android.musicmix.SongArtist.SearchArtist;
import com.example.android.musicmix.TopTracks.TrackList;
import com.example.android.musicmix.TrackInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchDisplay extends AppCompatActivity {
    private final static String API_KEY = "4628d54512e015d8ce17b12be8fa1e70";
    private static final String TAG = SearchDisplay.class.getSimpleName();
    String songName;
    int doit;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<TrackList> trackList;
    private RecyclerAdapter adapter;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        Bundle extras = getIntent().getExtras();
        songName = extras.getString("search");
        doit = extras.getInt("do");
        Log.e(SearchDisplay.class.getSimpleName(), "name and do" + songName + doit);
        if (doit == 1)
            fetchSearchTitle();
        else
            fetchSearchArtist();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(SearchDisplay.this, TrackInfo.class);
                Bundle extras = new Bundle();
                extras.putInt("id", trackList.get(position).getTrack().getTrackId());
                intent.putExtras(extras);
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

    }

    public void fetchSearchTitle() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<SearchArtist> call = apiInterface.getSongTitle(songName, API_KEY);


        call.enqueue(new Callback<SearchArtist>() {
            @Override
            public void onResponse(Call<SearchArtist> call, Response<SearchArtist> response) {
                trackList = response.body().getMessage().getBody().getTrackList();
                adapter = new RecyclerAdapter(trackList, SearchDisplay.this);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<SearchArtist> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    public void fetchSearchArtist() {


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<SearchArtist> call = apiInterface.getSongArtist(songName, API_KEY);


        call.enqueue(new Callback<SearchArtist>() {
            @Override
            public void onResponse(Call<SearchArtist> call, Response<SearchArtist> response) {
                trackList = response.body().getMessage().getBody().getTrackList();
                Log.e(SearchDisplay.class.getSimpleName(), "result" + trackList.get(0).getTrack().getAlbumName());
                adapter = new RecyclerAdapter(trackList, SearchDisplay.this);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<SearchArtist> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
